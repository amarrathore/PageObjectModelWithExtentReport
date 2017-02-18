package pageObjectModel.automation.pageActions.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pageObjectModel.automation.testBase.TestBase;

public class HomePage extends TestBase{

	public void navigateTo(String data){
		Reporter.log("navigating to "+data);
		String locator = "//a[contains(text(),'"+data+"')]";
		driver.findElement(By.xpath(locator)).click();
	}

}
