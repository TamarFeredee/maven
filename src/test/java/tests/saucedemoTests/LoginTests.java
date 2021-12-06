package tests.saucedemoTests;


import org.testng.Assert;
import org.testng.annotations.Test;
import tests.saucedemoPageObjects.LoginPage;
import tests.saucedemoPageObjects.ProductsPage;
import tests.utils.Configuration;


public class LoginTests extends BaseTest {
    private final long twoSeconds = 2000;

    @Test
    public void tc01_loginEmptyUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("", Configuration.PASSWORD);
        sleep(twoSeconds);
        String expected = Configuration.WRONG_USER_ERROR;
        String actual = loginPage.getErrorMsg();
        Assert.assertEquals(actual, expected);
        driver.navigate().refresh();
    }

    @Test
    public void tc02_loginEmptyPass() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(Configuration.USER, "");
        sleep(twoSeconds);
        String expected = Configuration.WRONG_PASSWORD_ERROR;
        String actual = loginPage.getErrorMsg();
        Assert.assertEquals(actual, expected);
        driver.navigate().refresh();
    }

    @Test
    public void tc03_loginWrongUserAndPass() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(Configuration.WRONG_USER, Configuration.WRONG_PASSWORD);
        sleep(twoSeconds);
        String expected = Configuration.WRONG_USER_AND_PASSWORD_ERROR;
        String actual = loginPage.getErrorMsg();
        Assert.assertEquals(actual, expected);
        driver.navigate().refresh();
    }

    @Test
    public void tc04_loginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(Configuration.USER, Configuration.PASSWORD);
        sleep(twoSeconds);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        driver.navigate().refresh();
    }
}
