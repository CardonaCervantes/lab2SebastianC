package com.example.lab2SebastianC;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class intresseanmälanSteps {

    WebDriver driver;

    // Before each scenario
    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
    }

    //Feature 2: instresseanmälan.feature
    @Given("the user is on the page {string}")
    public void the_user_is_on_the_page(String linkText) {
        driver.get(linkText);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookeConsentAllowAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        cookeConsentAllowAll.click();
    }
    @When("the user selects Agil testautomatiserare - YH-kurs under Intresse för utbildning")
    public void the_user_selects_under() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkBoxCourse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#choice_2_7_3")));
        checkBoxCourse.click();
    }
    @When("the user enters {string} under Namn-field")
    public void the_user_enters_under_field(String userName) {
        WebElement nameField = driver.findElement(By.name("input_1"));
        nameField.sendKeys(userName);
    }
    @When("the user clicks on Send")
    public void the_user_clicks_on() {
        WebElement sendButton = driver.findElement(By.id("gform_submit_button_2"));
        sendButton.click();
    }
    @Then("error message {string} should appear and the Namn-field is colored red")
    public void error_message_should_appear(String aProblem) {
        WebElement errorMessage = driver.findElement(By.cssSelector("#gform_2_validation_container > h2"));
        String actualResult = errorMessage.getText();
        WebElement redBox = driver.findElement(By.cssSelector("#field_2_9"));
        boolean redBoxDisplayed= redBox.isDisplayed();
        assertAll(
                () -> assertEquals(aProblem, actualResult),
                () -> assertTrue(redBoxDisplayed)
        );
    }
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
