package com.complyAdvantage.apiautomationtest.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(glue = {"com.complyAdvantage.apiautomationtest.stepdefs"},
              //  tags = { "@displayPoliticians" },
                features = "src/test/resources",
                plugin = { "pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json" } )
public class RunCucumberTests {

}
