package com.example.lab2SebastianC;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class ithsSteps {

    WebDriver driver;
    WebDriverWait wait;
    private int coursesCountAll;
    private int coursesCountGbg;
    private boolean visibleObject;

    // Before each scenario
    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    // Background
    @Given("the user visits the website")
    public void the_user_visits_the_website() {
        driver.get("https://www.iths.se/");
        driver.manage().window().maximize();
    }

    @And("the user allows all cookies")
    public void the_user_allows_all_cookies () {
        WebElement cookieConsentAllowAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieConsentAllowAll.click();
    }

    // Feature 1: iths.feature
    // Scenario 1
    @Then("the title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle());
    }

    // Scenario 2
    @And("the user clicks on the link Studentportal")
    public void the_user_clicks_on_the_link() {
        driver.findElement(By.id("nav-studentportaliclassfarfasigninalti")).click();
    }
    @Then("the Url should be {string}")
    public void the_url_should_be(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl);
    }

    //Scenario 3
    @Given("the user visits the page {string}")
    public void the_user_visits_the_page(String linkText) {
        driver.get(linkText);
        WebElement cookieConsentAllowAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieConsentAllowAll.click();
    }
    @When("the default filtration is active")
    public void the_default_filtration_is_active() {
        WebElement allaUtbildningarButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'btn current-term') and contains(text(), 'Alla utbildningar')]")));
        allaUtbildningarButton.isDisplayed();
    }
    @Then("the count of courses in the default filtration should be {int}")
    public void the_count_of_courses_in_the_default_filtration_should_be(int coursesCountWantedAll) {
        List<WebElement> courses = driver.findElements(By.cssSelector(".course-card__content-title"));
        int coursesCountAll = courses.size();
        this.coursesCountAll = coursesCountAll;
        assertEquals(coursesCountWantedAll, coursesCountAll);
    }
    @When("the user applies the Göteborg filtration")
    public void the_user_applies_the_goteborg_filtration() {
        driver.findElement(By.cssSelector("#course-filter-bar > div > div.types > a:nth-child(4)")).click();
        WebElement gbgButtonIsActive = driver.findElement(By.xpath("//a[contains(@class, 'btn current-term btn--filter') and contains(text(), 'Göteborg')]"));
        gbgButtonIsActive.isDisplayed();
    }
    @Then("the count of courses in the Göteborg filtration should be {int}")
    public void the_count_of_courses_in_the_goteborg_filtration_should_be(int coursesCountWantedGbg) {
        List<WebElement> courses = driver.findElements(By.cssSelector(".course-card__content-title"));
        int coursesCountGbg = courses.size();
        this.coursesCountGbg = coursesCountGbg;
        assertEquals(coursesCountWantedGbg, coursesCountGbg);
    }
    @Then("the difference in course count between the default and Göteborg filtrations should be {int}")
    public void the_difference_in_course_count_between_the_default_and_goteborg_filtrations_should_be(Integer difference) {
        int calculatedDifference = this.coursesCountAll - this.coursesCountGbg;
        assertEquals(difference, calculatedDifference);
    }

    // Scenario 4
    @Given("the user goes to page {string}")
    public void the_user_goes_to_page(String linkText) {
        driver.get(linkText);
        WebElement cookieConsentAllowAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieConsentAllowAll.click();
    }
    @When("the user clicks on the play button")
    public void the_user_clicks_on_the_play_button() {
        WebElement playButton = driver.findElement(By.cssSelector("#page > div.page-item__hero.relative > div.page-item__hero-content > a > i"));
        playButton.click();
    }
    @When("the user clicks on showOnYouTubeButton in embedded video")
    public void the_user_clicks_on_show_on_you_tube_button_in_embedded_video() {
        WebElement videoPlayer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.featherlight.featherlight-iframe > div > iframe")));
        videoPlayer.click();
        WebElement iframe = driver.findElement(By.cssSelector("body > div.featherlight.featherlight-iframe > div > iframe"));
        driver.switchTo().frame(iframe);
        WebElement showOnYouTubeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-right-controls > a")));
        showOnYouTubeButton.click();
        driver.switchTo().defaultContent();
    }
    @Then("the link to the content on YouTube should be {string}")
    public void the_link_to_the_content_on_you_tube_should_be(String expectedTitle) throws InterruptedException {
        String originalTab = driver.getWindowHandle();
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        Thread.sleep(5000);
        WebElement actualTitle = driver.findElement(By.cssSelector("#title > h1 > yt-formatted-string"));
        assertEquals(expectedTitle, actualTitle.getText());
    }

    // Scenario 5
    @Given("the user goes to the page {string}")
    public void the_user_goes_to_the_page(String linkText) {
        driver.get(linkText);
        WebElement cookieConsentAllowAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieConsentAllowAll.click();
    }
    @When("the user expands the accordion menu")
    public void the_user_expands_the_accordion_menu() {
        WebElement expandButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page\"]/div[2]/div/div/div[2]/div[8]/div[1]/div[2]/i[1]")));
        expandButton.click();
    }
    @Then("the object x is visible")
    public void the_object_x_is_visible() {
        WebElement visibleObject = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#page > div.page__container.container > div > div > div.accordion > div:nth-child(8) > div.accordion__answer.answer-toggle > div > div > div:nth-child(1) > div > div > div > div.quote-block-small__column-right > blockquote > span")));
        this.visibleObject = visibleObject.isEnabled();
        assertTrue(this.visibleObject);
    }

    // Scenario 6
    @And("the user collapses the accordion menu")
    public void the_user_collapses_the_accordion_menu() {
        WebElement collapseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page\"]/div[2]/div/div/div[2]/div[8]/div[1]/div[2]/i[2]")));
        collapseButton.click();
    }
    @Then("the object x is not visible")
    public void the_object_x_is_not_visible() {
        assertFalse(this.visibleObject);
    }

    // Scenario 7
    @Given("user is on main page")
    public void user_is_on_main_page() {
        driver.get("https://www.iths.se/");
        System.out.println(driver.getCurrentUrl());
        WebElement cookieConsentAllowAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieConsentAllowAll.click();
    }
    @When("hover over item")
    public void hover_over_item() throws InterruptedException {
        Thread.sleep(5000);
        WebElement headerItem = driver.findElement(By.xpath("//*[@id='nav-frstuderande']"));
        Actions action = new Actions(driver);
        action.moveToElement(headerItem).perform();
    }
    @And("selects nested item")
    public void selects_nested_item() throws InterruptedException {
        Thread.sleep(5000);
        WebElement menuItem = driver.findElement(By.xpath("//*[@id=\"nav-preparandkurser\"]"));
        Actions action = new Actions(driver);
        action.moveToElement(menuItem).click().perform();
    }
    @Then("new page should be {string}")
    public void new_page_should_be(String expectedTitle) throws InterruptedException {
        Thread.sleep(5000);
        assertEquals(expectedTitle, driver.getTitle());
    }
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
