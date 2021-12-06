package tests.saucedemoTests;

import org.testng.annotations.Test;
import tests.saucedemoPageObjects.ProductPage;
import tests.saucedemoPageObjects.ProductsPage;
import tests.saucedemoPageObjects.YourCartPage;
import tests.utils.Configuration;


public class RemoveTests extends BaseTest {
    private final long twoSeconds = 2000;

    @Test
    public void tc01_removeFromProductPage() {
        login();
        addToCartFromProductPage(Configuration.SAUCE_LABS_FLEECE_JACKET);
        ProductPage productPage = new ProductPage(driver);
        productPage.removeProduct();
        sleep(twoSeconds);
        logout();
    }

    @Test
    public void tc02_removeFromProductsPage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_ONESIE);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.removeProduct();
        sleep(twoSeconds);
        logout();
    }

    @Test
    public void tc03_removeFromYourCartPage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openCart();
        sleep(twoSeconds);
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.removeProduct();
        sleep(twoSeconds);
        logout();
    }

    public void addToCartFromProductPage(String productName) {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.chooseProductAndClickIt(productName);
        sleep(twoSeconds);
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        sleep(twoSeconds);
    }

    public void addToCartFromProductsPage(String productName) {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.chooseProductAndAddToCart(productName);
        sleep(twoSeconds);
    }
}
