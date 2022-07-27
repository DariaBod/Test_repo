import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    public static void printResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//a[contains(@class, '_2f75n _24Q6d cia-cs')])[1]")));
        ((JavascriptExecutor)driver).executeScript("scroll(0,800)");
        ArrayList<String> listOfProductsAndPrices = new ArrayList<>();
        for (int i = 0; i < namesOfProducts.size(); i++)
        listOfProductsAndPrices.add(namesOfProducts.get(i).getText()+" - "+pricesOfProducts.get(i).getText());
        System.out.println("Список найденных товаров с ценниками:");
        for (int i = 0; i < listOfProductsAndPrices.size(); i++)
            System.out.println(listOfProductsAndPrices.get(i));
    }

    public static void verificationOfResults() {
        String productName=ConfProperties.getProperty("productname");
        Assert.assertTrue(driver.getPageSource().contains(productName),"В результатах поиска нет требуемого продукта");
        System.out.println("Искомый продукт присутствует в результатах поиска");
    }

    public static void sortByPrice() {
        ((JavascriptExecutor)driver).executeScript("scroll(0,-300)");
        sortByPriceBtn.click();
        ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
    }
    public static void clickOnFirstResult() {
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("(//a[contains(@class, '_2f75n _24Q6d cia-cs')])[1]")));
    }
}
