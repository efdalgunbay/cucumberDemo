package com.Demo.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","json:target/json-report/cucumber.json"},
        glue = "com/Demo/step_definitions",
        //FOR SMOKE
        //features = "src/test/resources/features/features_smoke",
        //FOR REGRESSION
        //features = "src/test/resources/features/features_regression",
        //FOR MINI REGRESSION
        features = "src/test/resources/features/features_mini_regression",
        //"json:target/cucumber.json",
        publish = true,
        tags = "@Login",
        dryRun =false
        //"rerun:target/rerun.txt"


)
public class CukesRunner {

}