package pageObjectModel.automation.testScripts.registrationPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import java.io.IOException;

import org.testng.annotations.BeforeClass;

import pageObjectModel.automation.listner.Listner;
import pageObjectModel.automation.pageActions.registrationPage.RegistrationPage;
import pageObjectModel.automation.testBase.TestBase;

public class RegistrationTest2 extends TestBase {
	RegistrationPage reg;
	Listner lis;
	 ExtentTest test;
	String emailAddress = "email" + System.currentTimeMillis() + "@gmail.com";
	String userName = "automationTest";
	String passowrd = "password";
	String selectDay = "12";
	String selectMonth = "January";
	String selectYear = "2017";
	String firstName = "firstName";
	String lastName = "lastName";
	String address = "address";


	@BeforeClass
	public void setUp() throws IOException {
		init();

	}

	@Test(priority = 0, enabled = true, description = "Test Registration with valid data")
	public void testRegistration() throws InterruptedException {
		test = extent.startTest("Registration Test", "Test Registration with valid data");
		System.out.println("driver"+driver);
		reg = new RegistrationPage(driver,test);
		reg.register(emailAddress, userName, passowrd, selectDay, selectMonth, selectYear, firstName, lastName, address);
	}


	@AfterClass()
	public void endTest() {
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
}
