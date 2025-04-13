package yandex.praktikum.pageObject;

import org.openqa.selenium.By;

public class OrderFormLocators {
    //поле Имя
    public static final By fieldName = By.cssSelector("[placeholder='* Имя']");
    //поле Фамилия
    public static final By fieldSurname = By.cssSelector("[placeholder='* Фамилия']");
    //поле Адрес
    public static final By fieldAddress = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    //поле Станция метро
    public static final By fieldMetro = By.className("select-search__input");
    //выбранное метро из списка
    public static final By userMetro = By.xpath(".//button[@tabindex='-1' and @value='4']");
    //поле Телефон
    public static final By fieldPhone = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    public static final By fieldNext = By.className("Button_Middle__1CSJM");
    //поле Дата
    public static final By fieldDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //выбранная Дата
    public static final By userDate = By.xpath("//div[@aria-label='Choose среда, 23-е апреля 2025 г.']");
    //поле Срок аренды
    public static final By fieldRentalPeriod = By.className("Dropdown-control");
    //выбранный срок аренды
    public static final By userRentalPeriod = By.xpath(".//div[text()='сутки']");
    //поле Цвет самоката
    public static final By checkboxColor = By.xpath("//label[contains(., 'чёрный жемчуг')]//input");
    //поле Комментарий
    public static final By fieldСommentForCourier = By.cssSelector("[placeholder='Комментарий для курьера']");
    //кнопка Заказать
    public static final  By buttonOderToEnd = By.xpath(".//button[@class='Button_Button__ra12g" +
            " Button_Middle__1CSJM' and text()='Заказать']");
    //Кнопка Да в диалоговом окне "Хотите оформить заказ"?
    public static final By buttonYes = By.xpath(".//button[text()='Да']");
    //Текст об успешно созданном заказе
    public static final By textDoneOrder = By.className("Order_ModalHeader__3FDaJ");

}
