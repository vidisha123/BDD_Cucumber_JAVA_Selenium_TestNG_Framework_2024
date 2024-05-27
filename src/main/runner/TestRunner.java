package main.runner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/main/feature",
		glue = {"main.stepDefinitions", "main.hooks"},
		monochrome = true,
		tags = "@Regression",//"@Valid or not @Invalid",//"@Valid and @Invalid"
		plugin = {"pretty","json:target/cucumber-html-reports", 
				"html:target/cucumber-html-reports",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class TestRunner  {
    

}
