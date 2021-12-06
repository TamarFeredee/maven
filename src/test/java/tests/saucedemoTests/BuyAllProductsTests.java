package tests.saucedemoTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.saucedemoPageObjects.*;
import tests.utils.Configuration;


public class BuyAllProductsTests extends BaseTest {
    private final long twoSeconds = 2000;

    @Test
    public void tc01_buyAllProductsFromProductsPage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BACKPACK);
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        addToCartFromProductsPage(Configuration.SAUCE_LABS_ONESIE);
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BIKE_LIGHT);
        addToCartFromProductsPage(Configuration.SAUCE_LABS_FLEECE_JACKET);
        addToCartFromProductsPage(Configuration.TEST_ALLTHETHINGS_T_SHIRT_RED);
        paymentProcess();
        logout();
    }

    @Test
    public void tc02_buyAllProductsFromProductPage() {
        login();
        addToCartFromProductPage(Configuration.TEST_ALLTHETHINGS_T_SHIRT_RED);
        addToCartFromProductPage(Configuration.SAUCE_LABS_FLEECE_JACKET);
        addToCartFromProductPage(Configuration.SAUCE_LABS_BIKE_LIGHT);
        addToCartFromProductPage(Configuration.SAUCE_LABS_ONESIE);
        addToCartFromProductPage(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        addToCartFromProductPage(Configuration.SAUCE_LABS_BACKPACK);
        paymentProcess();
        logout();
    }


    public void addToCartFromProductsPage(String productName) {
        ProductsPage productsPage = new ProductsPage(driver);
        sleep(twoSeconds);
        productsPage.chooseProductAndAddToCart(productName);
        sleep(twoSeconds);
        Assert.assertTrue(productsPage.isProductsPage());
    }

    public void addToCartFromProductPage(String productName) {
        ProductsPage productsPage = new ProductsPage(driver);
        sleep(twoSeconds);
        productsPage.chooseProductAndClickIt(productName);
        sleep(twoSeconds);
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        productPage.backToProducts();
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        sleep(twoSeconds);
    }

    public void paymentProcess() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openCart();
        sleep(twoSeconds);

        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.checkout();
        sleep(twoSeconds);

        CheckOutInformationPage checkoutPage = new CheckOutInformationPage(driver);
        checkoutPage.doCheckout(Configuration.FIRST_NAME, Configuration.LAST_NAME, Configuration.POSTAL_CODE);
        sleep(twoSeconds);

        CheckOutOverviewPage checkoutOverviewPage = new CheckOutOverviewPage(driver);
        checkoutOverviewPage.finish();
        sleep(twoSeconds);

        CheckOutCompletePage checkOutCompletePage = new CheckOutCompletePage(driver);
        checkOutCompletePage.printFinishMsg();
        String expected = Configuration.COMPLETE_MESSAGE;
        String actual = checkOutCompletePage.printFinishMsg();
        Assert.assertEquals(actual, expected);
    }

}
