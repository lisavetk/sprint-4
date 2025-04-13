package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.page.object.HomePageLocators;
import ru.yandex.praktikum.page.object.OrderFormLocators;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    private WebDriver driver;
    private HomePageLocators homePageLocators;
    private OrderFormLocators orderFormLocators;

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

    @Parameterized.Parameters(name = "{index}: name={0}, surname={1}, address={2}, phone={3}, comment={4}, button={5}")
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

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConf.IMPLICIT_WAIT));
        homePageLocators = new HomePageLocators(driver);
        orderFormLocators = new OrderFormLocators(driver);
        homePageLocators.getWait();
    }

    @Test
    public void doAnOrderForScooterTest() {
        homePageLocators.openHomePage();
        //HomePageLocators locators = new HomePageLocators(driver);

        homePageLocators.clickOnButtonOrder(chooseButton);

        orderFormLocators.enterFirstPartOfFormOrder(userName, userSurname, userAddress, userPhone);

        orderFormLocators.enterSecondPartOfFormOrder(userComment);

        orderFormLocators.confimrOrderCreation();

        checkTextInOrderDoneWindow();
    }

    private void checkTextInOrderDoneWindow() {
        String expected = "Заказ оформлен";
        String actual = orderFormLocators.getTextDoneOrder();
        assertEquals("Не отображается ожидаемый текст", expected, actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
