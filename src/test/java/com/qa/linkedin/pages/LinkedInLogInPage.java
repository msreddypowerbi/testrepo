package com.qa.linkedin.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class LinkedInLogInPage extends BasePageWeb {

	Logger log = Logger.getLogger(LinkedInLogInPage.class);

	public LinkedInLogInPage() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[contains(@class,'profile-rail-card')]")
	private WebElement profileRailCard;

	@FindBy(xpath = "//*[@class='global-nav__me-photo ghost-person ember-view']")
	private WebElement profileimagecard;

	@FindBy(xpath = "//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement signOutLink;

	@FindBy(xpath = "//input[@type='text']")
	private WebElement searchEditBox;

	@FindBy(xpath = "//a[@class='app-aware-link'][contains(.,'See all post results')]")
	private WebElement seeallPostResults;

	public void verifyProfileRailCard() {
		log.debug("verifyProfileRailCard");
		wait.until(ExpectedConditions.visibilityOf(profileRailCard));
		Assert.assertTrue(profileRailCard.isDisplayed(), "profileRailCard is not available");
	}

	public void clickOnSignOutLink() throws Exception {
		log.debug("Click on SignOutLink");
		click(profileimagecard);
		click(signOutLink);

	}

	public SerchResultsPage searchEditBox(String keyword) throws Exception {

		log.debug("Enter the keyword here");
		sendKey(searchEditBox, keyword);
		Thread.sleep(2000);
		log.debug("press the enter key");
		searchEditBox.sendKeys(Keys.ENTER);
		return new SerchResultsPage();

	}

}
