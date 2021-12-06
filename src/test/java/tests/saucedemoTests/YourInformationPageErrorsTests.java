package tests.saucedemoTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.saucedemoPageObjects.CheckOutInformationPage;
import tests.saucedemoPageObjects.ProductsPage;
import tests.saucedemoPageObjects.YourCartPage;
import tests.utils.Configuration;


public class YourInformationPageErrorsTests extends BaseTest {
    private final long twoSeconds = 2000;

    @Test
    public void tc01_fillOnlyFirstName() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_ONESIE);
        paymentProcess();
        CheckOutInformationPage checkoutPage = new CheckOutInformationPage(driver);
        checkoutPage.doCheckout(Configuration.FIRST_NAME, "", "");
        sleep(twoSeconds);
        Assert.assertEquals(checkoutPage.getErrorMsg(), Configuration.LAST_NAME_ERROR);
        logout();
    }

    @Test
    public void tc02_fillOnlyLastName() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_FLEECE_JACKET);
        paymentProcess();
        CheckOutInformationPage checkoutPage = new CheckOutInformationPage(driver);
        checkoutPage.doCheckout("", Configuration.LAST_NAME, "");
        sleep(twoSeconds);
        Assert.assertEquals(checkoutPage.getErrorMsg(), Configuration.FIRST_NAME_ERROR);
        logout();
    }

    @Test
    public void tc03_emptyPostalCode() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BACKPACK);
        paymentProcess();
        CheckOutInformationPage checkoutPage = new CheckOutInformationPage(driver);
        checkoutPage.doCheckout(Configuration.FIRST_NAME, Configuration.LAST_NAME, "");
        sleep(twoSeconds);
        Assert.assertEquals(checkoutPage.getErrorMsg(), Configuration.POSTAL_CODE_ERROR);
        logout();
    }


    public void addToCartFromProductsPage(String productName) {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.chooseProductAndAddToCart(productName);
        sleep(twoSeconds);
    }

    public void paymentProcess() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openCart();
        sleep(twoSeconds);

        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.checkout();
        sleep(twoSeconds);

        CheckOutInformationPage checkOutInformationPage = new CheckOutInformationPage(driver);
        Assert.assertTrue(checkOutInformationPage.isYourInformationPage());
    }
}
