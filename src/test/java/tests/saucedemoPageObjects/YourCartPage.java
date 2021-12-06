package tests.saucedemoPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourCartPage extends Menu {
    @FindBy(css = ".btn.btn_action.btn_medium.checkout_button")
    private WebElement checkoutBtn;
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    private WebElement removeBtn;
    @FindBy(css = "#continue-shopping")
    private WebElement continueShoppingBtn;
    @FindBy(css = ".title")
    private WebElement yourCartPageIndication;

    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    public void checkout() {
        click(checkoutBtn);
    }

    public void removeProduct() {
        click(removeBtn);
    }

    public void clickContinueShoppingBtn() {
        click(continueShoppingBtn);
    }

    //Validations
    public boolean isYourCartPage() {
        return getText(yourCartPageIndication).equalsIgnoreCase("YOUR CART");
    }
}
