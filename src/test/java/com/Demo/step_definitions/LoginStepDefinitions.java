package com.Demo.step_definitions;

import com.Demo.pages.HomePage;
import com.Demo.pages.RegisterPage;
import com.Demo.pages.LoginPage;
import com.Demo.utilities.ConfigReader;
import com.Demo.utilities.ReusableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginStepDefinitions {

    LoginPage loginPage=new LoginPage();
    HomePage homePage=new HomePage();
    RegisterPage registerPage=new RegisterPage();

    @Given("Kullanici carrefoursa.com anasayfasina gider.")
    public void kullanici_carrefoursa_com_anasayfasina_gider() {
    }
    @Given("Anasayfadaki Giris Yap butonuna tiklar.")
    public void anasayfadaki_giris_yap_butonuna_tiklar() {
       loginPage.loginButton.click();
    }

    @Given("Cep Telefonu Numaraniz bolumune gecerli bir numara girer")
    public void cep_telefonu_numaraniz_bolumune_gecerli_bir_numara_girer() {

        loginPage.loginPhoneNumberBox.sendKeys(ConfigReader.getProperty("phone_number"));
    }
    @Given("Giris Yapin butonuna tiklar")
    public void giris_yapin_butonuna_tiklar() {
        loginPage.loginButton2.click();
    }
    @Given("SMS Onay Kodu bolumune gecerli bir Otp kodu girer")
    public void sms_onay_kodu_bolumune_gecerli_bir_otp_kodu_girer() {
        loginPage.otpCodeTextBox.sendKeys(ConfigReader.getProperty("otp_code"));
    }
    @When("Tamam butonuna tiklar")
    public void tamam_butonuna_tiklar() {
        loginPage.otpConfirmButton.click();

        ReusableMethods.waitFor(1);
    }
    @Then("Basarili bir giris yaptigini control eder")
    public void basariliBirGirisYaptiginiControlEder() {
       //loginPage.welcome.isDisplayed();
        loginPage.verifySuccessLogin();

    }
    @Given("Cep Telefonu Numaraniz bolumune gecersiz bir numara girer")
    public void cep_telefonu_numaraniz_bolumune_gecersiz_bir_numara_girer() {
        loginPage.loginPhoneNumberBox.sendKeys(ConfigReader.getProperty("invalid_phone_number"));
    }
    @Given("Gecersiz numara girildiginde cikan hata mesaji gorulur")
    public void Gecersiz_numara_girildiginde_cikan_hata_mesaji_gorulur(){
       // loginPage.gecersizNumaraHataMesaji.isDisplayed();
        loginPage.verifyUnSuccessLogin();
    }
    @Given("SMS Onay Kodu bolumune gecersiz bir Otp kodu girer")
    public void SMS_Onay_Kodu_bolumune_gecersiz_bir_Otp_kodu_girer() {
        loginPage.otpCodeTextBox.sendKeys(ConfigReader.getProperty("invalid_otp_code"));
    }
    @Given("Gecersiz SMS onay hata mesaji gorulur")
    public void Gecersiz_SMS_onay_hata_mesaji_gorulur(){

        loginPage.HataliOtpMesaji.isDisplayed();
    }

    @Given("SMS Onay Kodu bolumune eksik bir Otp kodu girer")
    public void eksikOtp() {
        loginPage.otpCodeTextBox.sendKeys(ConfigReader.getProperty("missing_otp_code"));
    }

    @Given("Kullanıcı cikis yap buttonuna tiklar")
    public void cikisYap_tiklanir(){
        loginPage.logoutIcon.click();
    }

    @Given("Kullanıcı giris yapamiyorum buttonuna tiklar")
    public void girisYapamiyorum(){
        loginPage.notLoginButton.click();
    }

    @Given("Dogrulama kodu icin bir email adresi girilir")
    public void dogrulamEmail(){
        loginPage.notLoginEmail.sendKeys(ConfigReader.getProperty("unregistered_email"));
    }

    @Given("Dogrulama baglantisi gonder buttonu tiklanir")
    public void dogrulamButtonuTiklanir(){
        loginPage.verificationLink.click();
    }

    @Given("Ekranda dogrulama mesaji gonderildi mesajı gorulur")
    public void dogrulamaMesajiGorulur(){
        //loginPage.confirmationMessage.isDisplayed();
        loginPage.verifyUnSuccessLogin2();
    }

    @Given("Giris yap sayfasi kapatılır")
    public void girisYapKapatalir(){
        loginPage.loginClose.click();
    }

    @Given("Numaranin bloke oldugu gorulur")
    public void blokeMesajiGorulur(){
        loginPage.errorMsgText.isDisplayed();
    }



    @Given("Kullanıcı cikis yaptigini gorur")
    public void cikis_yaptigini_gorur(){
        String logoutMessage = loginPage.verifyLogoutMsg.getText();
        System.out.println("logoutMessage = " + logoutMessage);
        loginPage.verifyLogoutMsg.isDisplayed();
    }

    @And("SMS Onay Kodu bolumune bir Otp kodu girer{string}")
    public void smsOnayKoduBolumuneBirOtpKoduGirer(String otpCode) {

        if (otpCode.equals("empty")) {
            otpCode = "";
        }
        loginPage.otpCodeTextBox.sendKeys(otpCode);

        if(otpCode.equals("wrongCode")){
            otpCode="123458";
        }
        loginPage.otpCodeTextBox.sendKeys(otpCode);
    }

    @And("Cep Numaraniz bolumune bir numara girer{string}")
    public void cepNumaranizBolumuneBirNumaraGirer(String phoneNumber) {
        if (phoneNumber.equals("empty")) {
            phoneNumber = "";
        }
        loginPage.loginPhoneNumberBox.sendKeys(phoneNumber);

//        if (phoneNumber.equals("wrongNumber")) {
//            phoneNumber = "8000005";
//        }
//        registerPage.registerPhoneNumberBox.sendKeys(phoneNumber);
    }
    }



