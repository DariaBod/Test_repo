import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    public WebDriver driver;

    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * определение локатора поля ввода товара
     */
    @FindBy(xpath = "//*[@id=\"header-search\"]")
    private static WebElement searchField;
    /**
     * определение локатора кнопки входа в аккаунт
     */
    @FindBy(xpath = "/html/body/div[4]/header/noindex/div/div/div[2]/div[2]/div/div/form/div[1]/button/span")
    private static WebElement findButton;

    /**
     * метод для ввода названия товара
     */
    public static void inputNameOfProduct(String productName) {
        searchField.sendKeys(productName);
    }


    /**
     * метод для осуществления нажатия кнопки поиска
     */
    public static void clickFindBtn() {
        findButton.click();
    }
}
