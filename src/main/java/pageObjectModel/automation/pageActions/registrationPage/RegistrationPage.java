package pageObjectModel.automation.pageActions.registrationPage;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageObjectModel.automation.testBase.TestBase;

public class RegistrationPage extends TestBase {

	WebDriver driver;
	ExtentTest test;
	By email = By.xpath("//input[@id='email_create']");
	By userName = By.id("customer_lastname");

	By password = By.cssSelector("#passwd");

	@FindBy(xpath = "//*[@id='SubmitCreate']")
	WebElement registor;

	@FindBy(className = "login")
	WebElement login;

	@FindBy(xpath = "//input[@id='id_gender1'][@name='id_gender']")
	WebElement selectGender;

	@FindBy(xpath = "//*[@id='days']")
	WebElement selectDays;

	@FindBy(xpath = "//*[@id='months']")
	WebElement selectMonth;

	@FindBy(xpath = "//*[@id='years']")
	WebElement selectYear;

	@FindBy(xpath = "//*[@id='firstname']")
	WebElement firstName;

	@FindBy(xpath = "//*[@id='lastname']")
	WebElement lastName;

	@FindBy(xpath = "//*[@id='address1']")
	WebElement address;

	@FindBy(xpath = "//*[@id='city']")
	WebElement city;

	@FindBy(xpath = "//*[@id='postcode']")
	WebElement postcode;

	@FindBy(xpath = "//*[@id='phone_mobile']")
	WebElement phone_mobile;

	@FindBy(xpath = "//*[@id='alias']")
	WebElement alias;

	@FindBy(xpath = "//*[@id='submitAccount']")
	WebElement submitAccount;

	@FindBy(xpath = "//*[@id='id_state']")
	WebElement state;

	@FindBy(xpath = "//p[contains(text(),'Welcome to your account. Here you can manage all of your personal information and orders.')]")
	WebElement accountCreationMessage;

	@FindBy(xpath = "//*[@id='years']/option")
	WebElement yearOption;

	@FindBy(xpath = "//*[@id='months']/option")
	WebElement monthOption;

	@FindBy(xpath = "//*[@id='days']/option")
	WebElement dayOption;

	public RegistrationPage(WebDriver driver, ExtentTest test) {
		// This initElements method will create all WebElements
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(this.driver, this);
	}

	public void setEmail(String emailAddress) {
		try {
			driver.findElement(email).sendKeys(emailAddress);
			test.log(LogStatus.PASS, "email address is set");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "email address is set", e);
		}
	}

	public void setUserName(String user) {
		try {
			driver.findElement(userName).sendKeys(user);
			test.log(LogStatus.PASS, "user name is set");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "user name is set", e);
		}

	}

	public void setPassword(String password) {
		try {
			driver.findElement(this.password).sendKeys(password);
			test.log(LogStatus.PASS, "password is set");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "password is set", e);
		}
	}

	public void clickOnLogin() {
		try {
			login.click();
			test.log(LogStatus.PASS, "clicked on login");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "clicked on login", e);
		}
	}

	public void clickOnRegistor() {
		try {
			registor.click();
			test.log(LogStatus.PASS, "clicked on Registration");
			test.log(LogStatus.PASS,test.addScreenCapture(captureScreen()));
		} catch (Exception e) {
			test.addScreenCapture(captureScreen());
			test.log(LogStatus.FAIL, "clicked on Registration");
		}
	}

	public void selectMailGender() {
		try {
			selectGender.click();
			test.log(LogStatus.PASS, "selected Mail Gender");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "selected Mail Gender");
		}
	}

	public void selectDays(String selectDay) {
		try {
			Thread.sleep(2000);
			selectDays.click();
			test.log(LogStatus.PASS, "clicked on Select Day drop down");
			Thread.sleep(2000);
			List<WebElement> days = driver.findElements(By.xpath("//*[@id='days']/option"));
			Iterator<WebElement> itr = days.iterator();
			while (itr.hasNext()) {
				WebElement c = itr.next();
				String text = c.getText().trim().toString();
				if (text.equals(selectDay)) {
					System.out.println(selectDays);
					c.click();
					break;
				}
			}
			test.log(LogStatus.PASS, "selected Days");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "selected Days did not match");
		}

	}

	public void selectMonth(String selectMonth) {
		try {
			this.selectMonth.click();
			test.log(LogStatus.PASS, "clicked on Select Month drop down");
			Thread.sleep(1000);
			List<WebElement> days = driver.findElements(By.xpath("//*[@id='months']/option"));
			Iterator<WebElement> itr = days.iterator();
			while (itr.hasNext()) {
				WebElement click = itr.next();
				String text = click.getText().trim();
				if (text.equals(selectMonth)) {
					click.click();
					break;
				}
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "clicked on Select Month drop down");
		}
	}

	public void selectYear(String selectYear) {
		try {
			this.selectYear.click();
			Thread.sleep(2000);
			List<WebElement> days = driver.findElements(By.xpath("//*[@id='years']/option"));
			Iterator<WebElement> itr = days.iterator();
			while (itr.hasNext()) {
				WebElement click = itr.next();
				String text = click.getText().trim();
				if (text.equals(selectYear)) {
					click.click();
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setFirstName(String firstName) {
		try {
			this.firstName.sendKeys(firstName);
			test.log(LogStatus.PASS, "Set first Name is pass");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Set first Name is pass", e);
		}
	}

	public void setLastName(String lastName) {
		try {
			this.lastName.sendKeys(lastName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setAddress(String address) {
		try {
			this.address.sendKeys(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectState() {
		try {
			new Select(state).getOptions().get(6).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void register(String emailAddress, String userName, String passowrd, String selectDay, String selectMonth,
			String selectYear, String firstName, String lastName, String address) throws InterruptedException {
		try {
			clickOnLogin();
			setEmail(emailAddress);
			clickOnRegistor();
			selectMailGender();
			driver.findElement(By.xpath("//form[@id='account-creation_form']/div[1]/div[2]/input"))
					.sendKeys("testName");
			setUserName(userName);
			setPassword(passowrd);
			selectDays(selectDay);
			selectMonth(selectMonth);
			selectYear(selectYear);
			setFirstName(firstName);
			setLastName(lastName);
			setAddress(address);
			city.sendKeys("CA");
			selectState();
			postcode.sendKeys("90001");
			phone_mobile.sendKeys("9999999999");
			alias.sendKeys("Test Address");
			submitAccount.click();

			String text = accountCreationMessage.getText();

			Assert.assertEquals(text,
					"Welcome123 to your account. Here you can manage all of your personal information and orders.");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Registration script failed",e);
		}
		catch(AssertionError e){
			test.log(LogStatus.FAIL, "Registration script failed",e);
		}

	}

}