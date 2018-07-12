package mavenProject02;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils extends BasePage {

    // for Elements to be clicked on
    public static void clickonElement(By by)
    {
        driver.findElement(by).click();
    }

    // for Elements where text needs to be entered
    public static void toEnterText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }

    // to clear text and enter text
    public static void clearEnterText(By by, String text)
    {driver.findElement(by).clear();
    driver.findElement(by).sendKeys(text);}

    // To get Actual text from the element
    public static String toGetText(By by)
    {
        return driver.findElement(by).getText();
    }

    // For selecting from Drop-down menu - by Value
    public static void selectByValue(By element, String num)
    {
        new Select(driver.findElement(element)).selectByValue(num);
    }

    // For selecting from Drop-down menu - by Visible Text
    public static void selectByVisibletext(By element, String text)
    {
        new Select(driver.findElement(element)).selectByVisibleText(text);
    }

    // For selecting from Drop-down menu - by Index
    public static void selectByIndex(By element, int num)
    {
        new Select(driver.findElement(element)).selectByIndex(num);
    }

    // For making dynamic email id - using TimeStamp
    public static String timeStamp()
    {
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmSS");
        return format.format(new Date());
    }

    public static void waitForElementToBeInvisible(By by , int time)
    {WebDriverWait wait = new WebDriverWait(driver, time);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void waitForElementToLoad(By by , int time ){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementToBeClickable(By by , int time){
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForTheElementToBeVisible(By by , int time){
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }












}
