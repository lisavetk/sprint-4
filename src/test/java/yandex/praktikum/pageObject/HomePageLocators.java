package yandex.praktikum.pageObject;

import org.openqa.selenium.By;

public class HomePageLocators {

    //локатор вопроса из списка
    public By getQuestion(int index) {
        return By.id("accordion__heading-"+ index);
    }

    //локатор ответа
    public By getAnswer(int index) {
        return By.id("accordion__panel-"+ index);
    }

    public By getButtonOrder(int chooseButton) {
        if (chooseButton == 0) {
            //кнопка "Заказать" в шапке
            return By.cssSelector(".Header_Nav__AGCXC > .Button_Button__ra12g");
        } else {
            //кнопка "Заказать" на странице
            return By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
        }
    }


}
