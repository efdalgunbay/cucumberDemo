package com.Demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HomePage extends BasePage{



    @FindBy(css = "img[src='https://reimg-carrefour.mncdn.com/staticimage/carrefoursacom-logo.svg']")
    public WebElement homePageButton;
    @FindBy(css = "#js-site-search-input")
    public WebElement searchField;
    @FindBy(xpath = "//button[@class='btn btn-link js-search-validate']")
    public WebElement searchButton;
    @FindBy(css = ".my-cart-text.d-sm-none")
    public WebElement myBasket;
    @FindBy(css = ".main-cat-link")
    public WebElement category;
    @FindBy(className = "d-block")
    public WebElement myAccount;
    @FindBy(css = "li[class='yCmsComponent'] a[title='Kurumsal']")
    public WebElement corporateLink;
    @FindBy(partialLinkText = "En Yakın CarrefourSA")
    public WebElement locationsLink;
    @FindBy(xpath = "//div[@class='call-center head-row pull-right']")
    public WebElement callCenterLink;
    @FindBy(id = "selectStoreCanvasAlternativeText")
    public WebElement selectionRegionLink;
    @FindBy(xpath = "(//a[@class='btn btn-default btn-block closeColorBox'])[6]")
    public WebElement informationPop_up;
    @FindBy(xpath = "(//i[@class='icon-call-center'])[2]")
    public WebElement youlisteningbutton;


    @FindBy(className = "yCmsComponent")
    public static List<WebElement> list_banners;

    @FindBy(className = "ui-menu-item")
    public List<WebElement> searhboxProductlist;


    public void checkLinks(){
        try{
            System.out.println("Anasayfadaki linkleri kontrol ediyor");
            //List<WebElement> list_banners= Driver.getDriver().findElements(By.className("yCmsComponent"));
            System.out.println("list_banners.size() = " + list_banners.size());
            String urlString = "";

            int counter=0;
            for (int i=0;i<list_banners.size();i++){
                try {
                    urlString = list_banners.get(i).findElement(By.tagName("a")).getAttribute("href");
                    System.out.println(urlString);
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    System.out.println(connection.getResponseMessage());

                    if (connection.getResponseMessage().equals("OK")){
                       counter= counter+1;
                        System.out.println("successful links = " + counter);
                        System.out.println("list_banners.size() = " + list_banners.size());
                        System.out.println("unsuccessful links = " + (list_banners.size() - counter));
                    }
                                    }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();

        }


    }

}



