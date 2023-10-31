package com.Demo.step_definitions;


import com.Demo.utilities.ConfigReader;
import com.Demo.utilities.Driver;
import com.Demo.utilities.ReusableMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.Demo.utilities.slackMassage;
import java.util.concurrent.TimeUnit;


public class Hooks {
slackMassage slack=new slackMassage();
    @Before
    public void setUp(){
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Driver.getDriver().get(ConfigReader.getProperty("url_live"));
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){

            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }
        try {
            slack.sendStatus();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Driver.close();

    }

}