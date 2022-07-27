import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;


public class ProductPage {
    public static WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[contains(@class, '_3NaXx _3kWlK')]")
    private static WebElement price;
    @FindBy(xpath = "//a[contains(@class, '_1yBO3 BgRce _2IaHT cia-cs')]")
    private static WebElement customerSiteAndName;

    public static void switchToNewTab() {
        String oldTab = driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
    }
    public static void customerName() {
        System.out.println("Магазин: "+customerSiteAndName.getText());
    }

    public static void productPrice() {
        System.out.println("Цена: "+price.getText());
    }
}
