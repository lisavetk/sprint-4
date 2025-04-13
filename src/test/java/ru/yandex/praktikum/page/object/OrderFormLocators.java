package ru.yandex.praktikum.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.EnvConf;

import java.time.Duration;

public class OrderFormLocators {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public OrderFormLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConf.EXPLICIT_WAIT));
    }
    //поле Имя
    private static final By fieldName = By.cssSelector("[placeholder='* Имя']");
    //поле Фамилия
    private static final By fieldSurname = By.cssSelector("[placeholder='* Фамилия']");
    //поле Адрес
    private static final By fieldAddress = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    //поле Станция метро
    private static final By fieldMetro = By.className("select-search__input");
    //выбранное метро из списка
    private static final By userMetro = By.xpath(".//button[@tabindex='-1' and @value='4']");
    //поле Телефон
    private static final By fieldPhone = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    private static final By fieldNext = By.className("Button_Middle__1CSJM");
    //поле Дата
    private static final By fieldDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //выбранная Дата
    private static final By userDate = By.xpath("//div[@aria-label='Choose среда, 23-е апреля 2025 г.']");
    //поле Срок аренды
    private static final By fieldRentalPeriod = By.className("Dropdown-control");
    //выбранный срок аренды
    private static final By userRentalPeriod = By.xpath(".//div[text()='сутки']");
    //поле Цвет самоката
    private static final By checkboxColor = By.xpath("//label[contains(., 'чёрный жемчуг')]//input");
    //поле Комментарий
    private static final By fieldСommentForCourier = By.cssSelector("[placeholder='Комментарий для курьера']");
    //кнопка Заказать
    private static final  By buttonOderToEnd = By.xpath(".//button[@class='Button_Button__ra12g" +
            " Button_Middle__1CSJM' and text()='Заказать']");
    //Кнопка Да в диалоговом окне "Хотите оформить заказ"?
    private static final By buttonYes = By.xpath(".//button[text()='Да']");
    //Текст об успешно созданном заказе
    private static final By textDoneOrder = By.className("Order_ModalHeader__3FDaJ");

    public void enterFirstPartOfFormOrder(String userName, String userSurname, String userAddress, String userPhone) {
        driver.findElement(fieldName).sendKeys(userName);

        driver.findElement(fieldSurname).sendKeys(userSurname);

        driver.findElement(fieldAddress).sendKeys(userAddress);

        driver.findElement(fieldMetro).click();
        driver.findElement(userMetro).click();

        driver.findElement(fieldPhone).sendKeys(userPhone);

        driver.findElement(fieldNext).click();
    }

    public void enterSecondPartOfFormOrder(String userComment) {
        driver.findElement(fieldDate).click();
        driver.findElement(userDate).click();

        driver.findElement(fieldRentalPeriod).click();
        driver.findElement(userRentalPeriod).click();

        driver.findElement(checkboxColor).click();

        driver.findElement(fieldСommentForCourier).sendKeys(userComment);

        driver.findElement(buttonOderToEnd).click();
    }

    public void confimrOrderCreation() {
        driver.findElement(buttonYes).click();
    }

    public String getTextDoneOrder() {
        return driver.findElement(textDoneOrder).getText().split("\n")[0];
    }

}
