package frameworkDesign.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworkDesign.Utils.UtilsClass;

public class FindCenterPage extends UtilsClass {

	WebDriver driver;

	public FindCenterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//nav[@class='nav-shared txt-nav-hierarchy nav-top js-nav-shared js-nav-top']//li[@class='nav-item displayed-desktop']//a[@class='btn-nav btn btn-large btn-hollow color-nileblue global_header_findcenter track_cta_click'][normalize-space()='Find a Center']")
	private WebElement findACenter;

	@FindBy(id = "addressInput")
	private WebElement mapAddressInput;

	@FindBy(css = "span.resultsNumber")
	private WebElement mapResultsNumber;

	@FindBy(css = "span.centerResult__address")
	private WebElement centerResultAddress;

	@FindBy(css = "div.mapTooltip")
	private WebElement mapTooltipTitle;

	@FindBy(css = "span.mapTooltip__headline")
	private WebElement mapTooltip__headline;

	@FindBy(css = "div.mapTooltip__address")
	private WebElement mapTooltip__address;

	@FindAll({ @FindBy(css = "h3[class='centerResult__name']") })
	private List<WebElement> centerResult;

	@FindAll({ @FindBy(xpath = "//div[contains(@class,'track_center_select')]"), })
	private List<WebElement> resultsTitles;

	public void clickOnfindACenter() {
		findACenter.click();
	}

	public String openMapLocationAndEnterLocation(String place) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + place + "';", mapAddressInput);

		// mapAddressInput.sendKeys(place);
		mapAddressInput.sendKeys(Keys.SPACE);
		js.executeScript("var event = new Event('input', { bubbles: true });", place);
		Thread.sleep(1000);
		mapAddressInput.sendKeys(Keys.ARROW_DOWN);
		mapAddressInput.sendKeys(Keys.ENTER);
		waitForVisibilityOfWebElement(15, mapResultsNumber);

		return mapResultsNumber.getText();
	}

	public int getsearchTitles() {
		return resultsTitles.size();
	}

	public String getFirstMapResultName() {
		waitForVisibilityOfWebElement(10, centerResult.get(0));
		return centerResult.get(0).getText();
	}

	public String getFirstMapResultAddress() {
		waitForVisibilityOfWebElement(10, centerResultAddress);
		return centerResultAddress.getText();
	}

	public void clickOnFirstCenterResults() {
		waitForVisibilityOfWebElement(20, resultsTitles.get(0));
		resultsTitles.get(0).click();
		waitForVisibilityOfWebElement(20, mapTooltipTitle);
	}

	public String getMapToolHeadline() {
		waitForVisibilityOfWebElement(10, mapTooltip__headline);
		return mapTooltip__headline.getText();

	}

	public String getMapToolAddress() {
		return mapTooltip__address.getText();

	}
}
