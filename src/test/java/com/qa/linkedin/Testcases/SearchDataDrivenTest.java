package com.qa.linkedin.Testcases;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedInHomepage;
import com.qa.linkedin.pages.LinkedInLogInPage;
import com.qa.linkedin.pages.SerchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase {

	private Logger log = Logger.getLogger(SearchDataDrivenTest.class);
	private String path = System.getProperty("user.dir")
			+ "\\src\\test\\java\\com\\qa\\linkedin\\data\\SearchData.xlsx";

	LinkedInHomepage lhomepage;
	LinkedInLogInPage lloginpage;
	SerchResultsPage srPage;

	@BeforeClass
	public void beforeClass() throws Exception {

		log.debug("Pages initilisation");
		lhomepage = new LinkedInHomepage();
		lloginpage = new LinkedInLogInPage();
		srPage = new SerchResultsPage();
		
		log.debug("Call the all methods");
		
	   lhomepage.verifyLinkedInHomePageTitle();
		lhomepage.verifyLinkedinLogo();
		lhomepage.clickOnsignInLink();
		
		lhomepage.verifyLinkedinSigninPageTitle();
		//lhomepage.verifysignInHeaderText();
		lloginpage = lhomepage.logInProcess(readPropertyValue("username"), readPropertyValue("password"));
		//lhomepage.clickOnSignInButton();
		lloginpage.verifyProfileRailCard();
	}

	@AfterClass
	public void afterClass() throws Exception {
		
		log.debug("Log out ");
		lloginpage.clickOnSignOutLink();
		
	}

	@Test(dataProvider = "getData")
	public void searchDataDrivenTest(String keyword) throws Exception {
		srPage = lloginpage.searchEditBox(keyword);
		srPage.verifySearchResultPageTitle();
		long cnt = srPage.getSearchResultsCount();
		log.debug("Search results count : "+keyword +"is :"+ cnt);
		log.debug("navigate to home tab");
		srPage.clickOnHomeTab();
		log.debug("****************One interation is done*******************");

	}

	@DataProvider
	public Object[][] getData() throws InvalidFormatException, IOException {
		Object[][] data = new ExcelUtils().getTestData(path, "Sheet1");		
		return data;
	}

}
