import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.ByteArrayInputStream;
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

  @Step("8. Вывод названия магазина в консоль")
  @Attachment(value = "Магазин", type = "text/plain")
    public static String customerName() {
      Allure.addAttachment("Карточка первого товара в списке", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        return "Магазин: "+customerSiteAndName.getText();
    }

  @Step("8. Вывод цены товара в консоль")
  @Attachment(value = "Цена", type = "text/plain")
    public static String productPrice() {
        return "Цена: "+price.getText();
    }
}
