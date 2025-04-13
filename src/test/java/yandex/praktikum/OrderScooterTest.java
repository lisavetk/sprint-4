package yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandex.praktikum.pageObject.HomePageLocators;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static yandex.praktikum.pageObject.OrderFormLocators.*;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    private WebDriver driver;
    private final String userName;
    private final String userSurname;
    private final String userAddress;
    private final String userPhone;
    private final String userComment;
    private final int chooseButton;

    public OrderScooterTest(String userName, String userSurname, String userAddress, String userPhone, String userComment, int chooseButton) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userComment = userComment;
        this.chooseButton = chooseButton;
    }

    @Parameterized.Parameters
    public static Object[][] getUser() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Свободы", "89001234567", "комментарий", 0},
                {"Петр", "Петров", "ул. Вишни", "89004567321", "не звоните", 1},
        };
    }

    @Before
    public void createDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConf.IMPLICIT_WAIT));
    }

    @Test
    public void doAnOrderForScooterTest() {
        openHomePage();
        HomePageLocators locators = new HomePageLocators();

        clickOnButtonOrder(locators);

        enterFirstPartOfFormOrder();

        enterSecondPartOfFormOrder();

        confimrOrderCreation();

        checkTextInOrderDoneWindow();
    }

    private void checkTextInOrderDoneWindow() {
        String expected = "Заказ оформлен";
        String actual = driver.findElement(textDoneOrder).getText().split("\n")[0];
        assertEquals("Не отображается ожидаемый текст", expected, actual);
    }

    private void confimrOrderCreation() {
        driver.findElement(buttonYes).click();
    }

    private void enterSecondPartOfFormOrder() {
        driver.findElement(fieldDate).click();
        driver.findElement(userDate).click();

        driver.findElement(fieldRentalPeriod).click();
        driver.findElement(userRentalPeriod).click();

        driver.findElement(checkboxColor).click();

        driver.findElement(fieldСommentForCourier).sendKeys(userComment);

        driver.findElement(buttonOderToEnd).click();
    }

    private void enterFirstPartOfFormOrder() {
        driver.findElement(fieldName).sendKeys(userName);

        driver.findElement(fieldSurname).sendKeys(userSurname);

        driver.findElement(fieldAddress).sendKeys(userAddress);

        driver.findElement(fieldMetro).click();
        driver.findElement(userMetro).click();

        driver.findElement(fieldPhone).sendKeys(userPhone);

        driver.findElement(fieldNext).click();
    }

    private void clickOnButtonOrder(HomePageLocators locators) {
        WebElement ButtonOrder =  driver.findElement(locators.getButtonOrder(chooseButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", ButtonOrder);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConf.EXPLICIT_WAIT)).until(ExpectedConditions
                .visibilityOfElementLocated(locators.getButtonOrder(chooseButton)));
        ButtonOrder.click();
    }

    public void openHomePage() {
        driver.get(EnvConf.BASE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
