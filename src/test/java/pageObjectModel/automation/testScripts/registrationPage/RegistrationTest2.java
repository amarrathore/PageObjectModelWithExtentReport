package pageObjectModel.automation.testScripts.registrationPage;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.math.BigDecimal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import pageObjectModel.automation.listner.Listner;
import pageObjectModel.automation.pageActions.registrationPage.RegistrationPage;
import pageObjectModel.automation.testBase.TestBase;

public class RegistrationTest2 extends TestBase {
	RegistrationPage reg;
	ExtentTest test;
	int i = 0;

	/*
	 * String userName = "automationTest"; String passowrd = "password"; String
	 * selectDay = "12"; String selectMonth = "January"; String selectYear =
	 * "2017"; String firstName = "firstName"; String lastName = "lastName";
	 * String address = "address";
	 */
	@DataProvider(name = "TestRegistor")
	public Object[][] provideData() {
		Object[][] testdata = getData("Login.xlsx", "Registor");
		return testdata;
	}

	@BeforeClass
	public void setUp() throws IOException {
		// init();

	}

	@Test(priority = 0, enabled = true, description = "Test Registration with valid data", dataProvider = "TestRegistor")
	public void testRegistration(String userName, String passowrd, String selectDay, String selectMonth,
			String selectYear, String customerfirstName, String customerLastName, String firstName, String lastName,
			String address, String verify, String runmode) throws InterruptedException, IOException {
		String emailAddress = "email" + System.currentTimeMillis() + "@gmail.com";
		init();
		i++;
		BigDecimal bigdecimal = new BigDecimal(selectDay);
		BigDecimal bigdecimal1 = new BigDecimal(selectYear);

		String sDay = String.valueOf(bigdecimal.longValue());
		System.out.println(sDay);
		String year = String.valueOf(bigdecimal1.longValue());

		if (runmode.equalsIgnoreCase("n")) {
			throw new SkipException("user has marred run mode n for this record");
		}
		test = extent.startTest("Registration Test", "Test Registration with valid data");
		reg = new RegistrationPage(driver, test);

		// userName = userName+System.currentTimeMillis();
		String message = reg.register(emailAddress, userName, passowrd, sDay, selectMonth, year,
				customerfirstName, customerLastName, firstName, lastName, address);
		try {
			reg.verifyRegistorMessage(verify, message);
			test.log(LogStatus.PASS, "registration is successfule");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Registration script failed", e);
			test.log(LogStatus.FAIL, test.addScreenCapture(captureScreen("registerFailed" + i)));
			Assert.assertTrue(false, "Registration failed");
			driver.quit();
		}

		catch (AssertionError e) {
			if (e.getLocalizedMessage().contains("Welcome123")) {
				test.log(LogStatus.PASS, "Registration script failed", e);

				driver.quit();
			} else {
				Assert.assertTrue(false, "Registration failed");
				test.log(LogStatus.PASS, test.addScreenCapture(captureScreen("registerPASSED" + i)));
			}

		}
		driver.quit();
	}

	@AfterClass()
	public void endTest() {
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
}
