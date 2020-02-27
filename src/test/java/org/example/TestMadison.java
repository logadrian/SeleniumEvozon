package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

public class TestMadison {

    @Test
    public void testMadisonHomepage() {
       System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://qa2.dev.evozon.com");

        System.out.println(driver.getTitle());

        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.className("logo")).click();

        driver.navigate().to("http://qa2.dev.evozon.com/men.html");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

       driver.quit();
    }

    @Test
    public void testMadisonAccount() {
        System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com");

        driver.findElement(By.cssSelector(".skip-link.skip-account")).click();
        driver.quit();
    }

    @Test
    public void testMadisonLanguages() {
        System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com");

//        List list = driver.findElements(By.cssSelector("#select-language > option"));
//        System.out.println(list.size());

       WebElement languageDropDown = driver.findElement(By.cssSelector("#select-language"));
       languageDropDown.click();

       Select selectDropDown = new Select(languageDropDown);
       selectDropDown.selectByIndex(1);

       driver.quit();
    }

    @Test
    public void testMadisonSearch(){
        System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com");

        driver.findElement(By.id("search")).clear();

        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("woman");
        searchBox.submit();

        driver.quit();

    }

    @Test
    public void testMadisonNewProductList() {
        System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com");

        List<WebElement> list = driver.findElements(By.cssSelector(".item.last"));
        System.out.println(list.size());

        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
        }



        driver.quit();

    }

    @Test
    public void testMadisonNavigation() {
        System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com");

        List<WebElement> list = driver.findElements(By.cssSelector(".level0.parent"));

       for(int i = 0; i <list.size(); i++) {
           System.out.println(list.get(i).getText());
        }

        WebElement saleCategory = driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-5.parent"));
        saleCategory.click();
    }

}
