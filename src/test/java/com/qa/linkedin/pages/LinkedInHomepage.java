package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedInHomepage extends BasePageWeb {

	Logger log=Logger.getLogger(LinkedInHomepage.class);
	
	public  LinkedInHomepage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="a.nav__logo-link")
	private WebElement linkedInLogo;
	
	@FindBy(css="a.nav__button-secondary")
	private WebElement signinLink;
	
	
	@FindBy(xpath="//*[@id=\"organic-div\"]/div[1]/h1")
	private WebElement signinheaderText;
	
	@FindBy(id="username")
	private WebElement emailEditBox;
	
	@FindBy(id="password")
	private WebElement passwordEditBox;
	
	@FindBy(xpath = "//button[contains(@class,'btn__primary--large from__button--floating')]")
	private WebElement signinbutton;
	
	
	String signinnPageTitle="LinkedIn Login, Sign in | LinkedIn";
	
	String homepageTitle="LinkedIn: Log In or Sign Up";
	
	public void verifyLinkedInHomePageTitle() throws InterruptedException {
		log.debug("Verify linked in home page title");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.titleContains(homepageTitle));
		Assert.assertEquals(driver.getTitle(),homepageTitle);
	}
	public void verifyLinkedinLogo() {
		log.debug("verify the Linked in logo");
		wait.until(ExpectedConditions.visibilityOf(linkedInLogo));
		Assert.assertTrue(linkedInLogo.isDisplayed(), "linked in logo not available");
	}
	
	public void clickOnsignInLink() throws Exception {
		log.debug("Click on sign in link");
		click(signinLink);
		
	}
	public void verifyLinkedinSigninPageTitle() {
		log.debug("Verify linked in sign in page title");
		wait.until(ExpectedConditions.titleContains(signinnPageTitle));
		Assert.assertEquals(driver.getTitle(),signinnPageTitle);
	}
	public void verifysignInHeaderText() {
		log.debug("Verify linked in sign in headeText");
		wait.until(ExpectedConditions.visibilityOf(signinheaderText));
		Assert.assertEquals(signinheaderText.isDisplayed(),"signin headertext is not avialble");
	}
	public void clickOnSignInButton() throws Exception {
		
		log.debug("Click on the signinbutton");
		click(signinbutton);
	}
	public LinkedInLogInPage logInProcess(String username,String password) throws Exception {
		
		log.debug("Start the sign in process");
		sendKey(emailEditBox,username);
		sendKey(passwordEditBox,password);
		clickOnSignInButton();
		return new LinkedInLogInPage();
	}
	
}
