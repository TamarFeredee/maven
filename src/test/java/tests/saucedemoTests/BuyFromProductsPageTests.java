package tests.saucedemoTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.saucedemoPageObjects.*;
import tests.utils.Configuration;


public class BuyFromProductsPageTests extends BaseTest {
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
        productsPage.chooseProductAndAddToCart(Configuration.SAUCE_LABS_ONESIE);
        sleep(twoSeconds);

        productsPage = new ProductsPage(driver);
        productsPage.chooseProductAndAddToCart(Configuration.SAUCE_LABS_FLEECE_JACKET);
        sleep(twoSeconds);
    }

    @Test
    public void tc03_paymentProcess() {

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

