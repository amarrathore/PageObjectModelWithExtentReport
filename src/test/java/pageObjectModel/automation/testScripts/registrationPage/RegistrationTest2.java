package pageObjectModel.automation.testScripts.registrationPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

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
		init();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		

	}

	@Test(priority = 0, enabled = true, description = "Test Registration with valid data", dataProvider = "TestRegistor")
	public void testRegistration(String passowrd, String selectDay, String selectMonth, String selectYear,
			String customerfirstName, String customerLastName, String firstName, String lastName, String address,
			String verify, String runmode) throws InterruptedException, IOException {
		String emailAddress = "email" + System.currentTimeMillis() + "@gmail.com";
		
		if (runmode.equalsIgnoreCase("n")) {
			throw new SkipException("user has marred run mode n for this record");
		}
		
		test = extent.startTest("Registration Test", "Test Registration with valid data");
		reg = new RegistrationPage(driver, test);
		
		reg.clickOnLogout();
		i++;
		BigDecimal bigdecimal = new BigDecimal(selectDay);
		BigDecimal bigdecimal1 = new BigDecimal(selectYear);

		String sDay = String.valueOf(bigdecimal.longValue());
		System.out.println(sDay);
		String year = String.valueOf(bigdecimal1.longValue());

		
		String message = reg.register(emailAddress, passowrd, sDay, selectMonth, year, customerfirstName,
				customerLastName, firstName, lastName, address);
		
		
		System.out.println("message----" + message);
		try {
			reg.verifyRegistorMessage(verify, message);
			test.log(LogStatus.PASS, "registration is successfule");
			extent.endTest(test);
			extent.flush();
			updateResult(i, "Registration With Valid Data", "Pass", "Registration Test");
			Assert.assertTrue(true, "Registration passed");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Registration script failed", e);
			extent.endTest(test);
			extent.flush();
			updateResult(i, "Registration With InValid Assertion", "Fail", "Registration Test");
			Assert.assertTrue(false, "Registration passed");
		}
	}

	@AfterClass()
	public void endTest() {
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
}
