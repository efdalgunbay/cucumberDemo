package com.Demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(className = "js-register-popup")
    public WebElement registerButton;

    @FindBy(css = ".form-control[id='email']")
    public WebElement registerEmail;

    @FindBy(css = "#register_phoneNumber")
    public WebElement registerPhoneNumberBox;

    @FindBy(css = ".generalAgreement[id='generalAgreement']")
    public WebElement generalCheckbox;

    @FindBy(css = ".generalAgreement[id='kvkkAgreement']")
    public WebElement kvkkAgreementCheckbox;

    @FindBy(css = ".generalAgreement[id='aydinlatmaMetni']")
    public WebElement clarificationtextCheckbox;

    @FindBy(id = "registerBtn")
    public WebElement signUpButton;

    @FindBy(css = ".confirmationCode")
    public WebElement otpRegisterTextBox;


    @FindBy(css = ".form-control[id='cityCode']")
    public WebElement cityCode;

    @FindBy(css = ".form-control[id='townCode']")
    public WebElement townCode;

    @FindBy(css = ".form-control[id='districtCode']")
    public WebElement districtCode;

    @FindBy(id = "selectStoreSubmit")
    public WebElement contiueButton;

    @FindBy(xpath = "//*[contains(text(),'İstanbul')]")
    public WebElement cityselectcode;

    @FindBy(xpath = "//*[contains(text(),'Arnavutköy')]")
    public WebElement townselectcode;

    @FindBy(xpath = "//*[contains(text(),'Adnan Menderes Mh.')]")
    public WebElement districtselectcode;

    @FindBy(css = ".confirmationCode[name='confirmationCode']")
    public WebElement registerotp;

    @FindBy(xpath = "(//button[@id='confirmOTPBtn'])[2]")
    public WebElement registerOtpConfirmButton;


    }




