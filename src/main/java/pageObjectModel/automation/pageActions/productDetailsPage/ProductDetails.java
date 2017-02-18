package pageObjectModel.automation.pageActions.productDetailsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import pageObjectModel.automation.testBase.TestBase;

public class ProductDetails extends TestBase {

	public void selectSmallSize() {
		Reporter.log("selecting small size");
		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_1']")).click();
	}

	public void selectMediumSize() {
		Reporter.log("selecting Medium size");
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).click();
				Reporter.log("checkbox is checked");
			}
		} catch (Exception e) {
			Reporter.log("checkbox was already checked");
		}
	}

	public void selectLSize() {
		Reporter.log("selecting Large size");
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).click();
				Reporter.log("checkbox is checked");
			}
		} catch (Exception e) {
			Reporter.log("checkbox was already checked");
		}
	}

	public int countProducts() {
		Reporter.log("counting products in Product details page");
		int count = driver.findElements(By.xpath(".//*[@id='center_column']/ul/li")).size();
		Reporter.log("total product is:-" + count);
		return count;
	}

	public void selectColor(String color) {
		Reporter.log("selecting " + color + "color");
		driver.findElement(By.xpath("//a[contains(text(),'" + color + "')]/parent::label/preceding-sibling::input"))
				.click();
	}

	public void verifyInformationSections() {
		Reporter.log("checking for information section");
		int count = driver.findElements(By.xpath(".//*[@id='informations_block_left_1']/div/ul/li")).size();
		if (count == 6) {
			Reporter.log("count is matching");
		} else {
			Assert.assertTrue(false, "count is not matching");
		}
	}

	public void selectProduct() {
		Actions obj = new Actions(driver);
		Reporter.log("performning mouse over on first product of page");
		obj.moveToElement(driver.findElements(By.xpath(".//*[@id='center_column']/ul/li")).get(0)).build().perform();
		Reporter.log("clicking on add to basket");
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")).click();
	}

	public boolean verifyProductAddedSuccesfully() {
		Reporter.log("verifying product is added succesfully");
		String text = driver.findElement(By.xpath(".//*[@id='layer_cart']/div[1]/div[1]/h2")).getText();
		try {
			Assert.assertEquals(text, "Product successfully added to your shopping cart");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void proceedTocheckout(){
		driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")).click();
	}

}
