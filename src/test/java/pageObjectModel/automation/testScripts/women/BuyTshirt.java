package pageObjectModel.automation.testScripts.women;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BuyTshirt {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();


@BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://automationpractice.com/index.php";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitled() throws Exception {
    driver.get(baseUrl);
    Actions action = new Actions(driver);
    // Mouse over on Women
    action.moveToElement(driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/a"))).build().perform();
    // select T shirts
    driver.findElement(By.linkText("T-shirts")).click();
    // select small radio button
    driver.findElement(By.id("layered_id_attribute_group_1")).click();
    // mouse over on image
    action.moveToElement(driver.findElement(By.xpath(".//*[@id='center_column']/ul/li/div/div[1]/div/a[1]/img"))).build().perform();
    //  click add to cart
    driver.findElement(By.xpath("//div[@id='center_column']/ul/li/div/div[2]/div[2]/a/span")).click();
    // click on proceed to checkout
    driver.findElement(By.xpath("//div[@id='layer_cart']/div/div[2]/div[4]/a/span")).click();
    // click on proceed to checkout
    driver.findElement(By.xpath("//div[@id='center_column']/p[2]/a/span")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test902@gmail.com");
    driver.findElement(By.id("passwd")).clear();
    driver.findElement(By.id("passwd")).sendKeys("password");
    driver.findElement(By.id("SubmitLogin")).click();
    
    driver.findElement(By.name("processAddress")).click();
    driver.findElement(By.name("processCarrier")).click();
    driver.findElement(By.cssSelector("a.fancybox-item.fancybox-close")).click();
    driver.findElement(By.id("cgv")).click();
    driver.findElement(By.name("processCarrier")).click();
    driver.findElement(By.cssSelector("a.bankwire > span")).click();
    driver.findElement(By.linkText("Sign in")).click();
  }


@AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      AssertJUnit.fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

