import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class ResultsPage {
    public static WebDriver driver;

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[contains(@class, '_3NaXx _33ZFz _2m5MZ')]")
    private static List<WebElement> pricesOfProducts;

    @FindBy(xpath = "//a[contains(@class, '_2f75n _24Q6d cia-cs')]")
    private static List<WebElement> namesOfProducts;

    @FindBy(xpath = "//button[contains(@class, '_23p69 _3D8AA')]")
    private static WebElement sortByPriceBtn;

   @Step("4. Создание списка найденных товаров с ценниками")
   @Attachment(value = "Список товаров", type = "text/plain")
    public static ArrayList<String> printResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//a[contains(@class, '_2f75n _24Q6d cia-cs')])[1]")));
       Allure.addAttachment("Скриншот найденных товаров", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        ((JavascriptExecutor)driver).executeScript("scroll(0,800)");
        ArrayList<String> listOfProductsAndPrices = new ArrayList<>();
        for (int i = 0; i < namesOfProducts.size(); i++)
        listOfProductsAndPrices.add(namesOfProducts.get(i).getText()+" - "+pricesOfProducts.get(i).getText());
        System.out.println("Список найденных товаров с ценниками:");
        return listOfProductsAndPrices;
    }

   @Step("5. Проверка присутствия искомого продукта в полученном списке")
   @Attachment(value = "Результат проверки присутствия продукта в списке", type = "text/plain")
    public static String verificationOfResults() {
        String productName=ConfProperties.getProperty("productname");
        Assert.assertTrue(driver.getPageSource().contains(productName),"В результатах поиска нет требуемого продукта");
        return "Искомый продукт присутствует в результатах поиска";
    }

   @Step("6. Применение сортировки по цене к списку товаров")
    public static void sortByPrice() {
        ((JavascriptExecutor)driver).executeScript("scroll(0,-300)");
        sortByPriceBtn.click();
        ((JavascriptExecutor)driver).executeScript("scroll(0,200)");
    }

  @Step("7. Нажатие на первый результат в выдаче после сортировки по цене")
    public static void clickOnFirstResult() {
      try {
          Thread.sleep(3*1000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      Allure.addAttachment("Скриншот товаров, отсортированных по цене", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("(//a[contains(@class, '_2f75n _24Q6d cia-cs')])[1]")));
    }
}
