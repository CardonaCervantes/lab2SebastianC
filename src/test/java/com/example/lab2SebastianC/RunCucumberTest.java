package com.example.lab2SebastianC;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/example/lab2SebastianC/resources/features",
        plugin = {"pretty", "json:target/cucumber-report"} )
public class RunCucumberTest {
}
