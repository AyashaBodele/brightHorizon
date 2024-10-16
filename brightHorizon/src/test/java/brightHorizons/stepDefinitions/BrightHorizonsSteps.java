package brightHorizons.stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import brightHorizons.TestComponents.BaseTest;
import frameworkDesign.pageObjects.FindCenterPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BrightHorizonsSteps extends BaseTest {

	public FindCenterPage findcenterpage;
	public WebElement searchField;
	private String mapResultsNumber;
	private Integer total;
	private String mapToolheadline;
	private String mapToolAddress;
	private String mapResultName;
	private String mapResultAddress;

	@Given("user has landed on BrightHorizon Home Page")
	public void user_has_landed_on_bright_horizon_home_page() throws IOException {
		landingPage = launchApplication();
		landingPage.acceptCookiesAndLocation();

	}

	@When("user click on search \\(top,right corner)")
	public void user_click_on_search_top_right_corner() {

		searchField = landingPage.clickOfSearchIcon();
	}

	@Then("search field is visible on the page")
	public void search_field_is_visible_on_the_page() {
		assertTrue(searchField.isDisplayed());
	}

	@When("user type {string} into search field and click search icon")
	public void user_type_into_search_field_and_click_search_icon(String value) {
		landingPage.typeinSearchFieldAndSearch(value);

	}

	@Then("verify if the first search result is exact match to what user typed into search {string}")
	public void verify_if_the_first_search_result_is_exact_match_to_what_user_typed_into_search(String value) {
		String title = landingPage.getTitleOfFirstSearchResults();
		assertEquals(title, value);
		driver.quit();
	}

	@When("user click on Find a Center option \\(Top header)")
	public void user_click_on_find_a_center_option_top_header() {
		findcenterpage = new FindCenterPage(driver);
		findcenterpage.clickOnfindACenter();

	}

	@Then("verify newly open page contains {string} as part of URL")
	public void verify_newly_open_page_contains_as_part_of_url(String stringURL) {
		assertTrue(driver.getCurrentUrl().contains(stringURL));
	}

	@When("user type {string} into search field and press Enter")
	public void user_type_into_search_field_and_press_enter(String place) throws InterruptedException {
		mapResultsNumber = findcenterpage.openMapLocationAndEnterLocation(place);
		total = findcenterpage.getsearchTitles();
	}

	@Then("verify if a number of found centers is the same as a number of centers displayed")
	public void verify_if_a_number_of_found_centers() {
		assertEquals(total, Integer.parseInt(mapResultsNumber));
	}

	@When("user click on the first center on the list")
	public void user_click_on_the_first_center_on_the_list() {
		findcenterpage.clickOnFirstCenterResults();
		mapResultName = findcenterpage.getFirstMapResultName();
		mapResultAddress = findcenterpage.getFirstMapResultAddress();
		mapToolheadline = findcenterpage.getMapToolHeadline();
		mapToolAddress = findcenterpage.getMapToolAddress();
	}

	@Then("Verify if center name and address are the same \\(on the list and on the popup)")
	public void verify_if_center_name_and_address_are_the_same_on_the_list_and_on_the_popup() {
		assertEquals(mapResultName, mapToolheadline);
		assertEquals(mapResultAddress.trim().replaceAll("\\s+", " "), mapToolAddress.trim().replaceAll("\\s+", " "));
		driver.quit();

	}

}
