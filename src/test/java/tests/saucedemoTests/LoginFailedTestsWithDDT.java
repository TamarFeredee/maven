package tests.saucedemoTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.saucedemoPageObjects.LoginPage;
import tests.utils.Configuration;

public class LoginFailedTestsWithDDT extends BaseTest {
    /*
     * In order to use DDT (Data Driven Testing) you should:
     * 1. create method to read from with @DataProvider annotation (in this example it called getData())
     * 2. add dataProvider="getData" parameter to the @Test
     */
    @Test(dataProvider = "getData", description = "use incorrect login information")
    public void tc01_loginWrongUserAndPass(String user, String password) {
        //login failed
        LoginPage loginPage = new LoginPage(driver);
        //Using the variables we get from the @DataProvider method
        loginPage.doLogin(user, password);
        sleep(1000);

        //Should check that we get the right message
        String expected = Configuration.WRONG_USER_AND_PASSWORD_ERROR;
        String actual = loginPage.getErrorMsg();
        Assert.assertEquals(actual, expected);
        driver.navigate().refresh();
    }

    /*
     * This is example of a method that return 2 dimensional object (like a table)
     * Using the @DataProvider the method above will get the parameters for each iteration.
     */
    @DataProvider
    public Object[][] getData() {
        Object[][] myData;
        myData = new Object[][]{
                {"use$r1", "123"},
                {"tam", "123"},
                {"yonit", "1#444"},
                {"tamara", "123456878"},
        };
        return myData;
    }
}
