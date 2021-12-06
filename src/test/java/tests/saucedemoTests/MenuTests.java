package tests.saucedemoTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.saucedemoPageObjects.Menu;
import tests.saucedemoPageObjects.ProductsPage;


public class MenuTests extends BaseTest {
    private final long fourSeconds = 4000;

    @Test
    public void tc01_openAboutPage() {
        login();
        Menu menu = new Menu(driver);
        menu.openMenu();
        menu.isMenuOpened();
        sleep(fourSeconds);
        menu.clickAboutBtn();
        sleep(fourSeconds);
        driver.navigate().back();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        sleep(fourSeconds);
        logout();
    }

    @Test
    public void tc02_logout() {
        login();
        logout();
    }

    @Test
    public void tc03_clickAllItems() {
        login();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        Menu menu = new Menu(driver);
        menu.openMenu();
        menu.isMenuOpened();
        sleep(fourSeconds);
        menu.clickAllItemsBtn();
        menu.closMenu();
        sleep(fourSeconds);
        Assert.assertTrue(productsPage.isProductsPage());
        sleep(fourSeconds);
        logout();
    }

    @Test
    public void tc04_openAndCloseMenu() {
        login();
        Menu menu = new Menu(driver);
        menu.openMenu();
        menu.isMenuOpened();
        sleep(fourSeconds);
        menu.closMenu();
        sleep(fourSeconds);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        logout();
    }
}
