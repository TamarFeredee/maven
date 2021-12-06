package tests.saucedemoTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import tests.saucedemoPageObjects.LoginPage;
import tests.saucedemoPageObjects.Menu;
import tests.saucedemoPageObjects.ProductsPage;
import tests.utils.Configuration;
import tests.utils.Utils;

import java.io.File;
import java.io.IOException;


public class BaseTest {
    WebDriver driver;
    private final long twoSeconds = 2000;

    @BeforeClass
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.get(Utils.readProperty("url"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    /* This method will run after each test,
     * it will take screenshot only for tests that failed
     */
    @AfterMethod
    public void failedTest(ITestResult result) {
        //check if the test failed
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("./ScreenShots/" + result.getName() + ".jpg"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //result.getName() method will give you current test case name.
            //./ScreenShots/ tell you that, in your current directory, create folder ScreenShots. dot represents current directory
        }
    }

    public void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(Configuration.USER, Configuration.PASSWORD);
        sleep(twoSeconds);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
    }

    public void logout() {
        Menu menu = new Menu(driver);
        menu.openMenu();
        menu.isMenuOpened();
        sleep(twoSeconds);
        menu.clickLogoutBtn();
        sleep(twoSeconds);
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
        driver.navigate().refresh();
    }
}
