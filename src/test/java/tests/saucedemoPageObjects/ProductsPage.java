package tests.saucedemoPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends Menu {

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> list;
    @FindBy(css = ".title")
    private WebElement pageTitle;
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    private WebElement removeBtn;


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void chooseProductAndClickIt(String name) {
        for (WebElement el : list) {
            if (el.getText().equalsIgnoreCase(name)) {
                click(el);
                break;
            }
        }

    }

    public void chooseProductAndAddToCart(String name) {
        String addToCartBtnId = "add-to-cart-" + name.replace(" ", "-");
        WebElement addToCartBtn = driver.findElement(By.id(addToCartBtnId.toLowerCase()));
        click(addToCartBtn);
    }

    public boolean isProductsPage() {
        return getText(pageTitle).equalsIgnoreCase("Products");
    }

    public void removeProduct() {
        click(removeBtn);
    }
}
