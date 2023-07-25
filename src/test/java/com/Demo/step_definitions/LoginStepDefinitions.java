package com.Demo.step_definitions;

import com.Demo.pages.RegisterPage;
import com.Demo.pages.LoginPage;
import io.cucumber.java.en.Given;
import com.Demo.utilities.ReusableMethods;


public class LoginStepDefinitions {


    LoginPage loginPage=new LoginPage();
    RegisterPage registerPage=new RegisterPage();


    @Given("Email Button Click")
    public void anasayfadaki_giris_yap_butonuna_tiklar() {
       loginPage.emailButton.click();
       ReusableMethods.waitFor(3);

    }

    }



