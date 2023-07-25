package com.Demo.pages;

import com.Demo.utilities.ConfigReader;
import com.Demo.utilities.ReusableMethods;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "(//span[@class='MuiButton-label'])[1]")
    public WebElement emailButton;

    @FindBy(xpath = "(//span[@class='MuiButton-label'])[4]")
    public WebElement emailButton2;


}

