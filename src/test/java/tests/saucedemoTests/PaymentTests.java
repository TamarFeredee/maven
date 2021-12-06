package tests.saucedemoTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.saucedemoPageObjects.*;
import tests.utils.Configuration;


public class PaymentTests extends BaseTest {
    private final long twoSeconds = 2000;

    @Test
    public void tc01_continueShoppingButton() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BACKPACK);
        openCart();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.clickContinueShoppingBtn();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BIKE_LIGHT);
        openCart();
        paymentProcess();
        logout();
    }

    @Test
    public void tc02_backHomeButton() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        openCart();
        paymentProcess();
        CheckOutCompletePage checkOutCompletePage = new CheckOutCompletePage(driver);
        checkOutCompletePage.backHomeBtn();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        logout();
    }

    @Test
    public void tc03_cancelButtonFromYourInformationPage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_ONESIE);
        openCart();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.checkout();
        sleep(twoSeconds);
        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        checkOutInformationPage.cancel();
        yourCartPage.isYourCartPage();
        logout();
    }

    @Test
    public void tc04_cancelButtonFromOverviewPage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BIKE_LIGHT);
        openCart();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.checkout();
        sleep(twoSeconds);

        CheckOutInformationPage checkoutPage = new CheckOutInformationPage(driver);
        checkoutPage.doCheckout(Configuration.FIRST_NAME, Configuration.LAST_NAME, Configuration.POSTAL_CODE);
        sleep(twoSeconds);

        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage(driver);
        checkOutOverviewPage.cancel();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());

        logout();
    }

    @Test
    public void tc05_clickOnCartButtonFromYIPage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_FLEECE_JACKET);
        openCart();

        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.checkout();
        sleep(twoSeconds);

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        checkOutInformationPage.openCart();
        yourCartPage.isYourCartPage();

        logout();
    }

    @Test
    public void tc06_clickOnCartButtonFromOverviewPage() {
        login();

        addToCartFromProductsPage(Configuration.TEST_ALLTHETHINGS_T_SHIRT_RED);
        openCart();

        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.checkout();
        sleep(twoSeconds);

        CheckOutInformationPage checkoutPage = new CheckOutInformationPage(driver);
        checkoutPage.doCheckout(Configuration.FIRST_NAME, Configuration.LAST_NAME, Configuration.POSTAL_CODE);
        sleep(twoSeconds);

        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage(driver);
        checkOutOverviewPage.openCart();
        yourCartPage.isYourCartPage();

        logout();
    }

    @Test
    public void tc07_clickOnCartButtonFromCompletePage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BACKPACK);
        openCart();

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
        checkOutCompletePage.openCartFromCompletePage();
        yourCartPage.isYourCartPage();
        logout();
    }


    public void addToCartFromProductsPage(String productName) {
        ProductsPage productsPage = new ProductsPage(driver);
        sleep(twoSeconds);
        productsPage.chooseProductAndAddToCart(productName);
        sleep(twoSeconds);
    }

    public void openCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openCart();
        sleep(twoSeconds);
    }

    public void paymentProcess() {
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
