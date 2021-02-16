package com.qa.linkedin.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class SerchResultsPage extends BasePageWeb {

	Logger log = Logger.getLogger(LinkedInLogInPage.class);

	public SerchResultsPage() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='app-aware-link'][contains(.,'See all post results')]")
	private WebElement seeallPostResults;

	@FindBy(xpath = "//*[@class='pb2 t-black--light t-14']")
	private WebElement serchResultsCount;

	@FindBy(xpath = "(//span[contains(@class,'global-nav__primary-link-text')])[1]")
	private WebElement HomeTab;
	
	
	String searchResultsPageTitle = "| Search | LinkedIn";

	public void verifySearchResultPageTitle() {
		log.debug("Verify the searchresult page title");
		wait.until(ExpectedConditions.titleContains(searchResultsPageTitle));
		Assert.assertTrue(driver.getPageSource().contains(searchResultsPageTitle));
	}

	public void clickOnSeeAllPostResults() throws Exception {
		log.debug("Click on seeallPostResults");
		click(seeallPostResults);

	}

	public  long getSearchResultsCount() {
		log.debug("Get the search results page text");
		
		
		try {
			if(isPresentElement(seeallPostResults)) {
				seeallPostResults.click();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOf(serchResultsCount));
		String txt = serchResultsCount.getText();
		System.out.println("Serch Results text is: "+txt);
		String[] str=txt.split(" ");
		log.debug("Extract value from index: "+str[0]);
		String finalResultsCount=str[0].replace(",","").trim();
		log.debug("Convert into text to value");
		long count=Long.parseLong(finalResultsCount);
		return count;
	}
	public void clickOnHomeTab() throws InterruptedException {
		log.debug("Click on home tab");
		click(HomeTab);
		
	}

}
