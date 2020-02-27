package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMadisonJUnit {

    WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com");


    }

    @After
    public void closeBrowser() {

         driver.quit();

    }

    @Test
    public void testMadisonHomepage() {
        String title = driver.getTitle();
        String expectedTitle = "Madison Island";
        Assert.assertTrue(title.contentEquals(expectedTitle));

        String actualUrl = "http://qa2.dev.evozon.com/";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, actualUrl);

        WebElement logo = driver.findElement(By.className("logo"));
        assertTrue(logo.isDisplayed());
        logo.click();

        driver.navigate().to("http://qa2.dev.evozon.com/accessories.html");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
    }

    @Test
    public void testMadisonHeaderAccount() {

        driver.findElement(By.cssSelector(".skip-link.skip-account")).click();

        WebElement dropDown = driver.findElement(By.id("header-account"));
        assertTrue(dropDown.isDisplayed());

        String expectedAccountLabel = "ACCOUNT";
        String accountLabel = driver.findElement(By.className("skip-account")).getText();
        assertEquals(expectedAccountLabel, accountLabel);
    }

    @Test
    public void testMadisonLanguages() {

        List list = driver.findElements(By.cssSelector("#select-language > option"));
        System.out.println(list.size());
        int expectedResult = 3;
        assertEquals(list.size(), expectedResult);

        WebElement languageDropDown = driver.findElement(By.cssSelector("#select-language"));
        languageDropDown.click();
        Select selectDropDown = new Select(languageDropDown);
        selectDropDown.selectByIndex(1);


    }

    @Test
    public void testMadisonSearch() {

        driver.findElement(By.id("search")).clear();

        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("woman");
        searchBox.submit();

        String actualTitle = driver.findElement(By.className("page-title")).getText().toLowerCase();

        String expectedKeyword = "woman";
        assertTrue(actualTitle.contains(expectedKeyword));


    }

    @Test
    public void testMadisonSearchForASpecifiedProduct() {

        driver.findElement(By.id("search")).clear();

        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("pants");
        searchBox.submit();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String actualTitle = driver.findElement(By.className("page-title")).getText().toLowerCase();
        String expectedKeyword = "pants";
        assertTrue(actualTitle.contains(expectedKeyword));

        WebElement sortElement = driver.findElement(By.className("toolbar"));
        assertTrue(sortElement.isDisplayed());

    }

    @Test
    public void testMadisonNewProductList() {

        List<WebElement> list = driver.findElements(By.cssSelector(".item.last"));
        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
        }

    }

    @Test
    public void testMadisonNavigation() {

        List<WebElement> list = driver.findElements(By.cssSelector(".level0.parent"));

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
        }

        WebElement saleCategory = driver.findElement(By.cssSelector("li.level0.nav-5.parent"));
        saleCategory.click();

        assertEquals("http://qa2.dev.evozon.com/sale.html", driver.getCurrentUrl());


    }

    // Method used for filling the mandatory fields in CheckOut process

    public WebElement getInputByTitle(String title) {
        return driver.findElement(By.cssSelector("li.active input[title='" + title + "']"));
    }

    // Method for random lists

    public void random (String css) {
        Random random = new Random();
        List<WebElement> randomList = driver.findElements(By.cssSelector(css));
        WebElement randomElement = randomList.get(random.nextInt(randomList.size()));
        randomElement.click();

    }


    @Test
    public void testCheckout() {


        WebElement category = driver.findElement(By.className("nav-primary"));
        category.click();

        WebElement subCategory = driver.findElement(By.cssSelector("div.col-main > ul > li:nth-child(2)"));
        subCategory.click();

        // Select a random product from subcategory

        List<WebElement> productsList = driver.findElements(By.cssSelector("div.category-products li.item > a"));
        Random rand = new Random();
        WebElement randomProduct = productsList.get(rand.nextInt(productsList.size()));
        randomProduct.click();

        WebElement addToCartButton = driver.findElement(By.className("add-to-cart-buttons"));
        addToCartButton.click();

        // Select a product using search box

        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("pants");
        searchBox.submit();

        // Verify if there are products after making a specific search

        WebElement sortElement = driver.findElement(By.className("toolbar"));
        assertTrue(sortElement.isDisplayed());

        WebElement pantsElement = driver.findElement(By.cssSelector("#product-collection-image-414"));
        pantsElement.click();

        // Select colour and size + add to cart


        WebElement colourPantsElement = driver.findElement(By.id("option17"));
        colourPantsElement.click();

        WebElement sizePantsElement = driver.findElement(By.id("option58"));
        sizePantsElement.click();

        WebElement addPantsToCart = driver.findElement(By.className("add-to-cart-buttons"));
        addPantsToCart.click();

        // Verify if there are products in the cart

        WebElement isDisplayedEmptyCart = driver.findElement(By.cssSelector("#empty_cart_button"));
        assertTrue(isDisplayedEmptyCart.isDisplayed());

        // Proceed to checkout

        driver.findElement(By.cssSelector("#country > option:nth-child(14)")).click();

        driver.findElement(By.cssSelector("#postcode")).sendKeys("123456");
        driver.findElement(By.className("btn-checkout")).click();

        // Go to checkout as Guest

        WebElement continueButton = driver.findElement(By.cssSelector("#onepage-guest-register-button"));
        continueButton.click();

        // Fill the mandatory fields in Checkout process

        getInputByTitle("First Name").sendKeys("Vasi");
        getInputByTitle("Middle Name/Initial").sendKeys("VasiLica");
        getInputByTitle("Last Name").sendKeys("VasiLica");
        getInputByTitle("Email Address").sendKeys("assdfasf@yahoo.com");
        getInputByTitle("Street Address").sendKeys("Muncii");
        getInputByTitle("City").sendKeys("Cluj");

        WebElement state = driver.findElement(By.cssSelector("#billing\\:region_id"));
        state.click();

        getInputByTitle("Zip/Postal Code").sendKeys("123456789");

        getInputByTitle("Telephone").sendKeys("0745123456");

        Select stateCheckout = new Select(driver.findElement(By.cssSelector("#billing\\:country_id")));
        stateCheckout.selectByIndex(5);

        driver.findElement(By.cssSelector("#billing\\:use_for_shipping_no")).click();

        driver.findElement(By.cssSelector("#billing-buttons-container > button")).click();

//        WebElement continueBtn = driver.findElement(By.cssSelector("#billing-buttons-container > button"));
//        continueBtn.click();

        // Wait for continue button to proceed at ShippingInformation section
        // from BillingInformationSection

        boolean shippingSection = driver.findElement(By.id("opc-shipping")).isDisplayed();

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#shipping\\:firstname"))).click();

        if (shippingSection) {

        getInputByTitle("First Name").sendKeys("S");
        getInputByTitle("Last Name").sendKeys("Maya");
        getInputByTitle("Street Address").sendKeys("strada Vietii");
        getInputByTitle("Zip/Postal Code").sendKeys("123456789");
        getInputByTitle("Telephone").sendKeys("0745123456");
        getInputByTitle("City").sendKeys("Cluj");

        Select countryCheckout = new Select(driver.findElement(By.cssSelector("#shipping\\:country_id")));
        countryCheckout.selectByValue("RO");

        Select provinceStateCheckout = new Select(driver.findElement(By.cssSelector("#shipping\\:region_id")));
        provinceStateCheckout.selectByValue("291");

        // continue button from ShippingInformation Section

         WebElement continueButtonShippingSection = driver.findElement(By.cssSelector("#shipping-buttons-container button[title=\"Continue\"]"));
         continueButtonShippingSection.click();

    }

        // Select free shipping option + continue button

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("s_method_freeshipping_freeshipping"))).click();

        driver.findElement(By.cssSelector("#shipping-method-buttons-container .button")).click();

        // Select payment method + continue button

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#payment-buttons-container .button"))).click();

        // Verify if order was placed

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#review-buttons-container .button"))).click();

        String messageAfterOrder = "THANK YOU FOR YOUR PURCHASE!";

        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sub-title"))).click();

        assertEquals(driver.findElement(By.cssSelector(".sub-title")).getText().toUpperCase(), messageAfterOrder.toUpperCase());


        }

    }

