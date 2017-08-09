package main.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import ria.com.base.TestBase;
import ru.yandex.qatools.allure.annotations.Step;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PageBase extends TestBase {

    public WebDriver driver;


    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void waiter(By by, int seconds) {
        try {
            WebElement dynamicElement = (new WebDriverWait(driver, seconds)).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            Assert.assertEquals(true, false, "Element does not exist yet");

        }
    }

    public void waiter(WebElement element) {
        waiter(element, 60);
    }


    public void waiter(WebElement element, int seconds) {
        try {
            WebElement dynamicElement = (new WebDriverWait(driver, seconds)).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
        }
        catch(StaleElementReferenceException ex)
        {
            waiter(element, seconds);
        }
    }

    public void waitForText(WebElement element, String text, String nameCheck, String errorText) {
        waitForText(element, text, errorText, nameCheck, 10);
    }

//    public void waitForText(String element, String text, String nameCheck, String errorText) {
//        waitForText(element, text, errorText, nameCheck, 10);
//    }


    @Step("{2}")
    public void waitForText(WebElement element, String text, String nameCheck, String errorText, int seconds) {
        try {
            boolean result = new WebDriverWait(driver, seconds).until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            Assert.assertEquals(true, false, errorText);

        }
    }

//    public void waitForCountElement(WebElement element, String text, String nameCheck, String errorText, int seconds) {
//        int expSize = 10;
//        assertThat();
//    }

    public void waiterForClick(WebElement element) {
        waiterForClick(element, 10);
    }

    public void waiterForClick(WebElement element, int seconds) {
        try {
             WebElement dynamicElement = (new WebDriverWait(driver, seconds)).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.assertEquals(true, false, "Element can not click");

        }
    }

//    public void waiterNumberOfElements(WebElement element, int seconds) {
//        WebDriverWait wait=new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//th[text()='Id']"), 0));
//
//    }

    public void waiterForNoClick(WebElement element, int seconds) {
        try {
            WebElement dynamicElement = (new WebDriverWait(driver, seconds)).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.assertEquals(true, false, "Element is present");

        }
    }

    public void waiterForDisappears(WebElement element, int seconds){
        try{
            Boolean dynamicElement = (new WebDriverWait(driver, seconds)).until(ExpectedConditions.invisibilityOfElementLocated((By) element));
        }catch (Exception e){

        }
    }

    public void slide(WebElement element, int x, int y) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(element).moveByOffset(x, y).release();
        actions.perform();
    }

    public static void assertLinkNotPresent(WebElement element, String text) throws Exception {
        List<WebElement> bob = (List<WebElement>) element;
        if (bob.isEmpty() == false) {
            throw new Exception(text + " (Element is present)");
        }
    }

    @Step("{0}")
    public void stepLog(String message) {
    }

    @Step(" {0}")
    public static void checkEquals(String nameCheck, String actual, WebElement expected, String mess) {
        Assert.assertEquals(actual, expected, mess);
    }

    @Step(" {0}")
    public static void checkEquals(String nameCheck, String actual, String expected, String mess) {
        Assert.assertEquals(actual, expected, mess);
    }

    @Step(" {0}")
    public static void checkEqualsSoftAssert(String nameCheck, String actual, String expected, String mess) {
        SoftAssert s_assert = new SoftAssert();
        Assert.assertEquals(actual, expected, mess);
        s_assert.assertAll();
    }

//    @Step(" {0}")
//    public static void checkEquals(String nameCheck, String actual, boolean expected, String mess) {
//        Assert.assertEquals(actual, expected, mess);
//    }

    @Step(" {0}")
    public void checkEquals(String nameCheck, boolean actual, boolean expected, String mess) {
        Assert.assertEquals(actual, expected, mess);
    }
    @Step(" {0}")
    public void checkNotEquals(String nameCheck, boolean actual, boolean expected, String mess) {
        Assert.assertNotEquals(actual, expected, mess);
    }

    @Step(" {0}")
    public void checkEquals(String nameCheck, String actual, boolean expected, String mess) {
        Assert.assertEquals(actual, expected, mess);
    }

    @Step(" {0}")
    public void checkEquals(String nameCheck, int actual, int expected, String mess) {
        Assert.assertEquals(actual, expected, mess);
    }

    public boolean isElementPresent(By by) {

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(by);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (list.size() == 0) {
            return false;
        } else {
            return list.get(0).isDisplayed();
        }

    }

    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void scroll(){

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
    }
}
