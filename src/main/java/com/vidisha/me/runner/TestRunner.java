package com.vidisha.me.runner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/main/java/com/vidisha/me/feature",
		glue = "com.vidisha.me.strpDefinitions",
		monochrome = true,
		tags = "@Invalid",//"@Valid or not @Invalid",//"@Valid and @Invalid"
		plugin = {"pretty","json:target/cucumber-html-reports", 
				"html:target/cucumber-html-reports",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class TestRunner  {
    

}
