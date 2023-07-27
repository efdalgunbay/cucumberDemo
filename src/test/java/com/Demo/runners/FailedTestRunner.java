package com.Demo.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber1.json"},
        features = "@target/rerun.txt",
        glue = "com/Demo/step_definitions"
)
public class FailedTestRunner {
}
