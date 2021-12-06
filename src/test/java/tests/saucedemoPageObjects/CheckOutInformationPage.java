package tests.saucedemoPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutInformationPage extends Menu {
    @FindBy(css = "#first-name")
    private WebElement firstNameField;
    @FindBy(css = "#last-name")
    private WebElement lastNameField;
    @FindBy(css = "#postal-code")
    private WebElement postalCodeField;
    @FindBy(css = "#continue")
    private WebElement continueBtn;
    @FindBy(css = "#cancel")
    private WebElement cancelBtn;
    @FindBy(css = ".title")
    private WebElement getTitle;
    @FindBy(css = ".error-message-container.error")
    private WebElement errorMsg;


    public CheckOutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void doCheckout(String firstName, String lastName, String postalCode) {
        fillText(firstNameField, firstName);
        fillText(lastNameField, lastName);
        fillText(postalCodeField, postalCode);
        click(continueBtn);
    }

    public void cancel() {
        click(cancelBtn);
    }

    //Validations
    public boolean isYourInformationPage() {
        if (getText(getTitle).equalsIgnoreCase("CHECKOUT: YOUR INFORMATION")) {
            return true;
        }
        return false;
    }

    public String getErrorMsg() {
        return getText(errorMsg);
    }
}
