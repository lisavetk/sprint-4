package ru.yandex.praktikum.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.EnvConf;

import java.time.Duration;

public class HomePageLocators {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConf.EXPLICIT_WAIT));
    }
    public void getWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConf.IMPLICIT_WAIT));
    }

   /* //локатор вопроса из списка
    public By getQuestion(int index) {
        return By.id("accordion__heading-"+ index);
    }

    //локатор ответа
    public By getAnswer(int index) {
        return By.id("accordion__panel-"+ index);
    }*/

    public void openHomePage() {
        driver.get(EnvConf.BASE_URL);
    }

    public void clickOnQuestion(int index) {
        WebElement question = driver.findElement(By.id("accordion__heading-"+ index));
        scrollToElement(question);
        question.click();
    }

    public String getAnswerText(int index) {
        By answerLocator = By.id("accordion__panel-" + index);
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return driver.findElement(answerLocator).getText();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        /*WebElement element =  driver.findElement(path);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);*/
    }


    private By buttonOrder;
    private By getButtonOrder(int chooseButton) {
        if (chooseButton == 0) {
            //кнопка "Заказать" в шапке
            buttonOrder = By.cssSelector(".Header_Nav__AGCXC > .Button_Button__ra12g");
           // return By.cssSelector(".Header_Nav__AGCXC > .Button_Button__ra12g");
        } else {
            //кнопка "Заказать" на странице
            buttonOrder = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
            //return By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
        }
        return buttonOrder;
    }

    public void clickOnButtonOrder(int chooseButton) {
        WebElement ButtonOrder =  driver.findElement(getButtonOrder(chooseButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", ButtonOrder);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConf.EXPLICIT_WAIT)).until(ExpectedConditions
                .visibilityOfElementLocated(getButtonOrder(chooseButton)));
        ButtonOrder.click();
    }





}
