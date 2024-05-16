package com.example.lab2SebastianC;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class quizSteps {

    WebDriver driver;
    WebDriverWait wait;

    // Before each scenario
    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("the user lands on the page {string}")
    public void the_user_lands_on_the_page(String url) {
        driver.get(url);
    }
    @And("the user accepts all cookies")
    public void the_user_accepts_all_cookies () {
        WebElement cookieConsentAllowAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieConsentAllowAll.click();
    }
    @Then("the user should end up in page {string}")
    public void the_user_should_end_up_in_page(String expectedTitle) {
        String actualTitle= driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    // Scenario 9
    @Given("the user is still on the page {string}")
    public void the_user_is_still_on_the_page(String expectedUrl) {
        driver.get("https://www.iths.se/vilken-utbildning-passar-mig/");
        driver.manage().window().maximize();
        System.out.println(expectedUrl);
    }
    @When("the user selects option '1' on step '1'")
    public void the_user_selects_option_on_step_1() {
        WebElement cookieConsentAllowAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookieConsentAllowAll.click();
        WebElement option_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#qp_main22346628 > div.qp_ao > div > div:nth-child(1) > div > span")));
        option_1.click();
    }
    @And("the user selects option '3' on step '2'")
    public void the_user_selects_option_on_step_2() throws InterruptedException {
        WebElement option_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#qp_main22346629 > div.qp_ao > div > div:nth-child(3) > div > span")));
        Thread.sleep(1000);
        option_1.click();
    }
    @And("the user selects option '2' on step '3'")
    public void the_user_selects_option_on_step_3() throws InterruptedException {
        WebElement option_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#qp_main22346630 > div.qp_ao > div > div:nth-child(2) > div > span")));
        Thread.sleep(1000);
        option_1.click();
    }


    @And("the user selects option '2' on step '4'")
    public void the_user_selects_option_on_step_4() throws InterruptedException {
        WebElement option_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#qp_main22346631 > div.qp_ao > div > div:nth-child(2) > div > span")));
        Thread.sleep(1000);
        option_1.click();
    }

    @And("the user selects option '1' on step '5'")
    public void the_user_selects_option_on_step_5() throws InterruptedException {
        WebElement option_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#qp_main22346632 > div.qp_ao > div > div:nth-child(1) > div > span")));
        Thread.sleep(1000);
        option_1.click();
    }
    @And("the user selects option '3' on step '6'")
    public void the_user_selects_option_on_step_6() throws InterruptedException {
        WebElement option_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#qp_main22346633 > div.qp_ao > div > div:nth-child(3) > div > span")));
        Thread.sleep(1000);
        option_1.click();
    }
    @And("the user selects option '4' on step '7'")
    public void the_user_selects_option_on_step_7() throws InterruptedException {
        WebElement option_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#qp_main22346635 > div.qp_ao > div > div:nth-child(4) > div > span")));
        Thread.sleep(1000);
        option_1.click();
    }
    @Then("the user should end up with box {string}")
    public void the_user_should_end_up_with_box(String expectedResult) {
        WebElement actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#qp_main22346634 > div.qp_q > div")));
        assertEquals(expectedResult, actualResult.getText());
    }
    @And("the user enters {string} in the email field")
    public void the_user_enters_string_under(String mail) {
        WebElement emailField = driver.findElement(By.name("qp_v22346634"));
        emailField.sendKeys(mail);
        emailField.sendKeys(Keys.ENTER);
    }
    @Then("the user page should return dialog box with error message")
    public void the_user_page_should_return_dialog_box_with_error_message() {
        WebElement dialogBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("qp-win-body")));
        assertTrue(dialogBox.isDisplayed());
    }
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
