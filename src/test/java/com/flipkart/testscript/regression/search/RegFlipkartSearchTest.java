package com.flipkart.testscript.regression.search;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.pages.*;
import com.flipkart.utils.BaseTest;
import com.flipkart.utils.CSVUtil;
import com.flipkart.utils.GetScreenShot;
import com.flipkart.utils.Logs;

public class RegFlipkartSearchTest extends BaseTest {
	
	HomePage homePage;
	SearchResultPage searchResultPage;
	private static final String CSV_FILE_PATH  = "./result.csv"; 
	int maxPrice = 40000;
  	String storage = "GB";
  	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		homePage = new HomePage(driver);
	}
	
	@Test(priority=1)
	public void searchItem() throws IOException{
		try {
			homePage.clickCloseButton();
			searchResultPage = homePage.searchProductOrBrand("iPhones");
			searchResultPage.clickSortPriceLowToHighLink();
			Map<String, String> map = searchResultPage.getItemDetails(maxPrice, storage);
			if (!map.isEmpty()) {
				CSVUtil.writeDataLineByLine(CSV_FILE_PATH, map);
			} else {
				GetScreenShot.capture(driver, "Failed to get the list of searched iPhones");
			}
		} catch (IOException e) {
			GetScreenShot.capture(driver, "Failed to get the list of searched iPhones");
			e.printStackTrace();
		}
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
