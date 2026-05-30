package com.sourav.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.sourav.automation.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    @Parameters("cucumber.filter.tags")
    public void setTags(@Optional("") String tags) {
        if (tags != null && !tags.trim().isEmpty()) {
            System.setProperty("cucumber.filter.tags", tags);
        }
    }
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}