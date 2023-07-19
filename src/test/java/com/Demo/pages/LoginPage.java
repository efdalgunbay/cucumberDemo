package com.Demo.pages;

import com.Demo.utilities.ConfigReader;
import com.Demo.utilities.ReusableMethods;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "(//a[@class='js-login-popup'])[2]")
    public WebElement loginButton;

    @FindBy(css = "a[class='csa-logout js-logout col-xs-12 col-sm-6 col-md-3 col-lg-3'] em[class='icon']")
    public WebElement logoutIcon;

    @FindBy(css = ".alert.alert-warning.alert-dismissable")
    public WebElement verifyLogoutMsg;

    @FindBy(id = "login_personal_phoneNumber")
    public WebElement loginPhoneNumberBox;

    @FindBy(css = "#loginBtn")
    public WebElement loginButton2;

    @FindBy(xpath= "//div[@id='confirm-login']//input[@id='confirmationCode']")
    public WebElement otpCodeTextBox;

    @FindBy(xpath = "(//button[@id='confirmOTPBtn'])[1]")
    public WebElement otpConfirmButton;

    @FindBy(xpath = "//span[@class='error-message']")
    public WebElement errorMsgText;

    @FindBy(css = " .login-error-message.error-message")
    public WebElement otpErrorMsgText;


    @FindBy(css = ".welcome-modal__container")
    public WebElement wellcomePop_up;

    @FindBy(css = ".welcome-modal__link")
    public WebElement discoverButton;

    @FindBy(xpath = "//button[@class='close']")
    public WebElement wellcomePop_upClose;
    @FindBy(css = "span[class='error-message']")
    public WebElement gecersizNumaraHataMesaji;
    @FindBy(css = "div[class='login-error-message error-message']")
    public WebElement HataliOtpMesaji;
    @FindBy(className = "d-block")
    public WebElement welcome;
    @FindBy(css = ".forgotten-password[id='validEmail']")
    public WebElement confirmationMessage;

    @FindBy(xpath = "//a[@class='js-password-forgotten']")
    public WebElement notLoginButton;

    @FindBy(css = ".form-control[id='forgottenPwd.email']")
    public WebElement notLoginEmail;

    @FindBy(id = "forgottenPassword")
    public WebElement verificationLink;

    @FindBy(className = "close-icon")
    public WebElement loginClose;



    public void successLogin() {
        loginButton.click();
        loginPhoneNumberBox.sendKeys(ConfigReader.getProperty("phone_number"));
        loginButton2.click();
        otpCodeTextBox.sendKeys(ConfigReader.getProperty("otp_code"));
        otpConfirmButton.click();
        ReusableMethods.waitFor(3);

    }
    public String verifySuccessLogin(){
        String welcomeText = welcome.getText();
        System.out.println("welcomeText = " + welcomeText);
        Assert.assertTrue(welcome.isDisplayed());
        return welcomeText;
    }
    public String verifyUnSuccessLogin(){
        String invalidNumberMsg = gecersizNumaraHataMesaji.getText();
        System.out.println("invalidNumberMsg = " + invalidNumberMsg);
        Assert.assertTrue(gecersizNumaraHataMesaji.isDisplayed());
        return invalidNumberMsg;
    }
    public String verifyUnSuccessLogin1(){
        String errorNumberMsg = errorMsgText.getText();
        System.out.println("errorNumberMsg = " + errorNumberMsg);
        Assert.assertTrue(errorMsgText.isDisplayed());
        return errorNumberMsg;
    }
    public String verifyUnSuccessLogin2(){
        String confirmationMsg = confirmationMessage.getText();
        System.out.println("confirmationMsg = " + confirmationMsg);
        Assert.assertTrue(confirmationMessage.isDisplayed());
        return confirmationMsg;
    }
}

