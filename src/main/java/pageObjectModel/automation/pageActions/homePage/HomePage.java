package pageObjectModel.automation.pageActions.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import pageObjectModel.automation.testBase.TestBase;

public class HomePage extends TestBase{
	
	public static final String  WOMEN = "Women";
	public static final String  DRESSES = "Dresses";
	public static final String  TSHIRTS = "T-shirts";

	public void navigateTo(String data){
		Reporter.log("navigating to "+data);
		String locator = "//a[contains(text(),'"+data+"')]";
		try {
			driver.findElement(By.xpath(locator)).click();
			test.log(LogStatus.PASS, "navigation to "+data);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "navigation to "+data);
		}
	}      
	
	public void mouseOver(String data){
	    Actions action = new Actions(driver);
	    // Mouse over on Women
	    action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'"+data+"')]"))).build().perform();
	}

}
