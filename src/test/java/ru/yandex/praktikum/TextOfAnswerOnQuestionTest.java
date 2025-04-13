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

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.TestsConstants.*;

@RunWith(Parameterized.class)
public class TextOfAnswerOnQuestionTest {
    private WebDriver driver;
    private HomePageLocators homePageLocators;

    private final String expectedText;
    private final int indexOfQuestion;

    public TextOfAnswerOnQuestionTest(String expectedText, int indexOfQuestion) {
        this.expectedText = expectedText;
        this.indexOfQuestion = indexOfQuestion;
    }

    @Parameterized.Parameters(name = "{index}: expected={0}, indexOfQuestions={1}")
    public static Object[][] getQuestions() {
        return new Object[][] {
                {ANSWER_PRICE_HOW_PAY, 0},
                {ANSWER_SEVERAL_SCOOTERS, 1},
                {ANSWER_RENTAL_TIME, 2},
                {ANSWER_SCOOTER_TODAY, 3},
                {ANSWER_RETURN_TIME, 4},
                {ANSWER_CHARGING_SCOOTER, 5},
                {ANSWER_CANCEL_ODER, 6},
                {ANSWER_DELIVERY_ACROSS_MKAD, 7},
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
        homePageLocators.getWait();
    }

    @Test
    public void checkTextOfAnswerOnQuestionTest() {
        homePageLocators.openHomePage();
       // homePageLocators.scrollToElement(locators.getQuestion(indexOfQuestion));
        homePageLocators.clickOnQuestion(indexOfQuestion);
        String actualAnswer = homePageLocators.getAnswerText(indexOfQuestion);
        assertEquals("Неверный текст", expectedText, actualAnswer);
       // checkTextOfAnswer(locators);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
