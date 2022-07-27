import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        //вводим название продукта
        SearchPage.inputNameOfProduct(ConfProperties.getProperty("productname"));
        //нажимаем кнопку "найти"
        SearchPage.clickFindBtn();
        ResultsPage.printResults();
        ResultsPage.verificationOfResults();
        ResultsPage.sortByPrice();
        ResultsPage.clickOnFirstResult();
        ProductPage.switchToNewTab();
        ProductPage.customerName();
        ProductPage.productPrice();
    }

    @AfterClass
    public static void tearDown() {
       // driver.quit();
        }

}
