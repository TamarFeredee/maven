package tests.saucedemoPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutCompletePage extends Menu {
    @FindBy(css = ".complete-header")
    private WebElement titleMsg;
    @FindBy(css = ".complete-text")
    private WebElement descriptionMsg;
    @FindBy(css = "#back-to-products")
    private WebElement backHomeBtn;

    public CheckOutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String printFinishMsg() {
        String msg = getText(titleMsg);
        System.out.println(msg);
        return msg;
    }

    public void backHomeBtn() {
        click(backHomeBtn);
    }
}
