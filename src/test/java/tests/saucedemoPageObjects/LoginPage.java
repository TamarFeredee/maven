package tests.saucedemoPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "#user-name")
    private WebElement userField;
    @FindBy(css = "#password")
    private WebElement passwordField;
    @FindBy(css = "#login-button")
    private WebElement loginBtn;
    @FindBy(css = "[data-test='error']")
    private WebElement errorMsg;
    @FindBy(css = "#login_credentials h4")
    private WebElement loginPageIndication;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void doLogin(String user, String password) {
        fillText(userField, user);
        fillText(passwordField, password);
        click(loginBtn);
    }

    //Validations
    public String getErrorMsg() {
        return getText(errorMsg);
    }

    public boolean isLoginPageDisplayed() {
        wait(userField);
        return userField.isDisplayed();
    }
}
