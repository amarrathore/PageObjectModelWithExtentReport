package pageObjectModel.automation.testScripts.registrationPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjectModel.automation.testBase.TestBase;

public class RegistrationTest {
	WebDriver driver;
	
	//testselenium@gmail.com"

	@BeforeClass
	public void setUp(){
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       //test = extent.startTest("Login Test", "This test will verify Registration test");
	  // test.log(LogStatus.PASS, "Basic set up for test is done");
	}

	@Test
	public void testRegistration() throws InterruptedException{
		driver.navigate().to("http://automationpractice.com/index.php");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("testselenium2@gmail.com");
		driver.findElement(By.xpath("//*[@id='SubmitCreate']")).click();
		driver.findElement(By.xpath("//input[@id='id_gender1'][@name='id_gender']")).click();
		driver.findElement(By.xpath("//form[@id='account-creation_form']/div[1]/div[2]/input")).sendKeys("testName");
		driver.findElement(By.id("customer_lastname")).sendKeys("testLastName");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("automation@123");
		driver.findElement(By.xpath("//*[@id='days']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='days']/option[11]")).click();
		driver.findElement(By.xpath("//*[@id='months']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='months']/option[6]")).click();
		driver.findElement(By.xpath("//*[@id='years']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='years']/option[10]")).click();
		driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys("fitsrName");
		driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys("lanme");
		driver.findElement(By.xpath("//*[@id='address1']")).sendKeys("address");
		driver.findElement(By.xpath("//*[@id='city']")).sendKeys("CA");
		
		new Select(driver.findElement(By.xpath("//*[@id='id_state']"))).getOptions().get(6).click();
		//driver.findElement(By.xpath("//*[@id='id_state']")).click();
		//Thread.sleep(1000);
		//driver.findElement(By.xpath("//*[@id='id_state']/option[6]")).click();
		
		driver.findElement(By.xpath("//*[@id='postcode']")).sendKeys("90001");
		driver.findElement(By.xpath("//*[@id='phone_mobile']")).sendKeys("9999999999");
		driver.findElement(By.xpath("//*[@id='alias']")).sendKeys("Test Address");
		driver.findElement(By.xpath("//*[@id='submitAccount']")).click();
		
		WebElement text = driver.findElement(By.xpath("//p[contains(text(),'Welcome to your account. Here you can manage all of your personal information and orders.')]"));
		
		AssertJUnit.assertEquals(text.getText(), "Welcome to your account. Here you can manage all of your personal information and orders.");
		
	}
	
	@AfterClass
	public void endTest(){
		//extent.endTest(test);
		//extent.flush();
	}
}
