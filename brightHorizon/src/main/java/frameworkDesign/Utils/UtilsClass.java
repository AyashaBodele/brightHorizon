package frameworkDesign.Utils;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilsClass {

	WebDriver driver;

	public UtilsClass(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForVisibilityOfWebElement(long sec, WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public void allowLocation(boolean value) {
		// Create ChromeOptions to configure browser settings
		ChromeOptions options = new ChromeOptions();

		// Disable the geolocation popup by setting the profile preferences
		options.addArguments("--disable-geolocation");
		options.setExperimentalOption("prefs", new HashMap<String, Object>() {
			{
				if (value) {
					put("profile.default_content_setting_values.geolocation", 1);
				} else {
					put("profile.default_content_setting_values.geolocation", 2);
				} // 1 to allow, 2 to block
			}
		});

	}

}
