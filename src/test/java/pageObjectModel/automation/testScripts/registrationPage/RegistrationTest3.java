package pageObjectModel.automation.testScripts.registrationPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjectModel.automation.listner.Listner;
import pageObjectModel.automation.pageActions.registrationPage.RegistrationPage;
import pageObjectModel.automation.testBase.TestBase;

public class RegistrationTest3 extends TestBase {

	String emailAddress = "email" + System.currentTimeMillis() + "@gmail.com";
	String userName = "automationTest";
	String passowrd = "password";
	String selectDay = "12";
	String selectMonth = "January";
	String selectYear = "2017";
	String firstName = "firstName";
	String lastName = "lastName";
	String address = "address";
	Listner lis;
	 ExtentTest test;

	@BeforeClass
	public void setUp() throws IOException {
		init();

	}

	@Test(priority = 0, enabled = true, description = "Test Registration with valid data")
	public void testRegistration1() throws InterruptedException {
		test = extent.startTest("Registration1 Test", "Test Registration with valid data");
		RegistrationPage registor = new RegistrationPage(driver,test);
		registor.register(emailAddress, userName, passowrd, selectDay, selectMonth, selectYear, firstName, lastName, address);
	}


	@AfterClass()
	public void endTest() {
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
}
