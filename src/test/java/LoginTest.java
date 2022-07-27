import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static SearchPage searchPage;
    public static ResultsPage resultsPage;
    public static ProductPage productPage;

    public static WebDriver driver;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    @Step("1. Открытие сайта Яндекс Маркет в окне браузера Google Chrome")
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        System.out.println(driver);
        searchPage = new SearchPage(driver);
        resultsPage=new ResultsPage(driver);
        productPage=new ProductPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 20 сек.
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("mainpage"));
    }

    @Test
    public static void loginTest() {
        SearchPage.inputNameOfProduct(ConfProperties.getProperty("productname"));
        SearchPage.clickFindBtn();
        ArrayList<String> list=ResultsPage.printResults();
        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
        System.out.println(ResultsPage.verificationOfResults());
        ResultsPage.sortByPrice();
        ResultsPage.clickOnFirstResult();
        ProductPage.switchToNewTab();
        System.out.println(ProductPage.customerName());
        System.out.println(ProductPage.productPrice());
    }

    @AfterClass
    @Step("9. Закрытие браузера")
    public static void tearDown() {
         driver.quit();
        }

}
