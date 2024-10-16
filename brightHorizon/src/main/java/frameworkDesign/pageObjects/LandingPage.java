package frameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.Utils.UtilsClass;

public class LandingPage extends UtilsClass {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "onetrust-accept-btn-handler")
	private WebElement cookiesAcceptance;

	@FindBy(xpath = "//a[contains(@href,'subnav-search-desktop-top')]/span")
	private WebElement searchIcon;

	@FindBy(xpath = "//nav[@id='subnav-search-desktop-top']//form[@class='js-nav-search-form']")
	public WebElement searchField;

	@FindBy(xpath = "(//input[@id='search-field'])[2]")
	private WebElement searchInputField;

	@FindBy(xpath = "//nav[@id='subnav-search-desktop-top']//button[@type='submit'][normalize-space()='Search']")
	private WebElement searchButton;

	@FindAll({ @FindBy(css = "a.search-result h3"), })
	private List<WebElement> searchResults;

	public void goTo() {
		driver.get("https://www.brighthorizons.com");
	}

	public void acceptCookiesAndLocation() {
		cookiesAcceptance.click();
		allowLocation(true);

	}

	public WebElement clickOfSearchIcon() {
		waitForVisibilityOfWebElement(10, searchIcon);
		searchIcon.click();
		waitForVisibilityOfWebElement(10, searchField);
		return searchField;
	}

	public void typeinSearchFieldAndSearch(String value) {
		searchInputField.sendKeys(value);
		searchButton.click();
	}

	public String getTitleOfFirstSearchResults() {
		return searchResults.get(0).getText();

	}

}
