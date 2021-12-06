package tests.saucedemoTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.saucedemoPageObjects.*;
import tests.utils.Configuration;

public class BuyFromProductPageTests extends BaseTest {
    private final long twoSeconds = 2000;

    @Test
    public void tc01_login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(Configuration.USER, Configuration.PASSWORD);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        sleep(twoSeconds);
    }

    @Test
    public void tc02_addProducts() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.chooseProductAndClickIt(Configuration.SAUCE_LABS_BACKPACK);
        sleep(twoSeconds);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        productPage.backToProducts();
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        sleep(twoSeconds);

        productsPage = new ProductsPage(driver);
        productsPage.chooseProductAndClickIt(Configuration.SAUCE_LABS_BIKE_LIGHT);
        sleep(twoSeconds);

        productPage = new ProductPage(driver);
        productPage.addToCart();
        productPage.backToProducts();
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
    }

    @Test
    public void tc03_payment() {
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
