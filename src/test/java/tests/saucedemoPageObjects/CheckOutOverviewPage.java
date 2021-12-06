package tests.saucedemoPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutOverviewPage extends Menu {
    @FindBy(css = "#finish")
    private WebElement finishBtn;
    @FindBy(css = "#cancel")
    private WebElement cancelBtn;

    public CheckOutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void finish() {
        click(finishBtn);
    }

    public void cancel() {
        click(cancelBtn);
    }
}
