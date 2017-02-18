package pageObjectModel.automation.testBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pageObjectModel.automation.listner.Listner;
import pageObjectModel.automation.utills.Excel_Reader;

public class TestBase {

	public static Properties Repository = new Properties();
	public File f;
	public FileInputStream FI;
	public static WebDriver driver;
	public String startTime;
	public static int indexSI = 1;
	public ExtentReports extent;
	public ExtentTest test;
	public Excel_Reader Data;

	public void init() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		extent = new ExtentReports(
				System.getProperty("user.dir") + "/src/test/java/pageObjectModel/automation/reports/test.html", false);
		loadPropertiesFile();
		selectBrowser(Repository.getProperty("browser"));
		driver.get(Repository.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void loadPropertiesFile() throws IOException {
		f = new File(System.getProperty("user.dir")
				+ "//src//main//java//pageObjectModel//automation//config//config.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
	}

	/**
	 * This method initialize browser object
	 * 
	 * @param browser
	 * @return browser driver
	 */
	public void selectBrowser(String browser) {
		if (browser.equals("firefox") || browser.equals("FIREFOX")) {
			this.driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browser.equals("chrome") || browser.equals("CHROME")) {
			System.out.println("chrome browser");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\com\\actiTime\\BrowserDrivers\\chromedriver.exe");
			this.driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (browser.equals("ie") || browser.equals("IE")) {
			this.driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
	}

	public void expliciteWait(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println(
					"Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
			Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");

		}
	}

	public void clickWhenReady(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();

	}

	public WebElement fluentWait(final WebElement webElement, int timeinsec) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeinsec, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return webElement;
			}
		});

		return element;
	};

	public WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;

	}

	public Object[][] getData(String ExcelName, String testcase) {
		Data = new Excel_Reader(
				System.getProperty("user.dir") + "//src//main//java//pageObjectModel//automation//data//" + ExcelName);
		int rowNum = Data.getRowCount(testcase);
		System.out.println(rowNum);
		int colNum = Data.getColumnCount(testcase);
		Object sampleData[][] = new Object[rowNum - 1][colNum];
		for (int i = 2; i <= rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				sampleData[i - 2][j] = Data.getCellData(testcase, j, i);
			}
		}
		return sampleData;
	}

	public void waitFor(int sec) throws InterruptedException {
		Thread.sleep(sec * 1000);
	}

	public void closeBrowser() {
		// driver = null;
		driver.close();
	}

	public void getScreenShot(String fileName) throws IOException {
		String userDirector = System.getProperty("user.dir");
		String customeLocation = "//src//test//java//pageObjectModel//automation//screenshots//";
		String failureImageFileName = userDirector + customeLocation
				+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + "-" + fileName
				+ ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(failureImageFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.log("<a href=\"" + failureImageFileName + "\"><img src=\"file:///" + failureImageFileName
				+ "\" alt=\"\"" + "height='100' width='100'/> " + "<br />");
		Reporter.setCurrentTestResult(null);
	}

	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat();
		return fm.format(cal.getTime());
	}

	public static void updateResult(int indexSI, String testCaseName, String testCaseStatus, String scriptName)
			throws IOException {

		String startDateTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());

		String userDirector = System.getProperty("user.dir");

		String resultFile = userDirector
				+ "//src//test//java//com//companyname//projectname//report//TestHtmlReport.html";

		File file = new File(resultFile);
		System.out.println(file.exists());

		if (!file.exists()) {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<html>" + "\n");
			bw.write("<head><title>" + "Test execution report" + "</title>" + "\n");
			bw.write("</head>" + "\n");
			bw.write("<body>");
			bw.write("<font face='Tahoma'size='2'>" + "\n");
			bw.write("<u><h1 align='center'>" + "Test execution report" + "</h1></u>" + "\n");
			bw.flush();
			bw.close();
		}
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
		if (indexSI == 1) {
			bw1.write("<table align='center' border='0' width='70%' height='10'>");
			bw1.write("<tr><td width='70%' </td></tr>");
			bw1.write("<table align='center' border='1' width='70%' height='47'>");
			bw1.write("<tr>");
			bw1.write(
					"<td colspan='2' align='center'><b><font color='#000000' face='Tahoma' size='2'>ScriptName :&nbsp;&nbsp;&nbsp;</font><font color='#0000FF'' face='Tahoma' size='2'>"
							+ scriptName + " </font></b></td>");
			bw1.write(
					"<td colspan='1' align='left'><b><font color='#000000' face='Tahoma' size='1'>Start Time :&nbsp;</font></b><font color='#0000FF'' face='Tahoma' size='1'>"
							+ startDateTime + " </font></td>");
			bw1.write("</tr>");
			bw1.write("</tr>");
			bw1.write(
					"<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>S.No</font></b></td>");
			bw1.write(
					"<td  bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Test case ID : Test case Description </font></b></td>");

			bw1.write(
					"<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Result </font></b></td>");
			bw1.write("</tr>");
		}
		bw1.write("<tr>" + "\n");
		bw1.write("<td bgcolor='#FFFFDC'align='Center'><font color='#000000' face='Tahoma' size='2'>" + indexSI
				+ "</font></td>" + "\n");
		bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>"
				+ testCaseName + "</font></b></td>" + "\n");
		if (testCaseStatus == "Pass") {
			bw1.write(
					"<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='Green' face='Tahoma' size='2'>"
							+ testCaseStatus + "</font></b></td>" + "\n");
		} else {
			bw1.write(
					"<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='red' face='Tahoma' size='2'>"
							+ testCaseStatus + "</font></b></td>" + "\n");
		}
		bw1.write("</tr>" + "\n");
		bw1.write("</body>" + "\n");
		bw1.write("</html>");
		bw1.flush();
		bw1.close();

	}

	public String captureScreen() {
		TakesScreenshot oScn = (TakesScreenshot) driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		
		String userDirector = System.getProperty("user.dir");
		String customeLocation = "//src//test//java//pageObjectModel//automation//screenshots//";
		String failureImageFileName = userDirector + customeLocation
				+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + "-"
				+ ".png";
		
		File oDest = new File(failureImageFileName);
		try {
			FileUtils.copyFile(oScnShot, oDest);
		} catch (IOException e) {
		}
		System.out.println(failureImageFileName);
		return failureImageFileName;
	}
}
