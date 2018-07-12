package mavenProject02;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class BaseTest extends BasePage {

    // LoadProp loadProp = new LoadProp();
    BrowserSetup browserSetup = new BrowserSetup();

    @BeforeMethod

    public void toOpenBrowser() {
        browserSetup.selectBrowser();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

       // driver.manage().window().maximize();

        driver.get("http://demo.nopcommerce.com/");
    }

    @AfterMethod

    public void screenShot(ITestResult result) {

// Here will compare if test is failing then only it will enter into if condition
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
// Create refernce of TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;

// Call method to capture screenshot
                File source = ts.getScreenshotAs(OutputType.FILE);

// Copy files to specific location here it will save all screenshot in our project home directory and
// result.getName() will return name of test case so that screenshot name will be same
                FileUtils.copyFile(source, new File("src\\test\\Resources\\ScreenShots\\" + result.getName() +System.currentTimeMillis()+ ".png"));

                System.out.println("Screenshot taken");
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }


        }


   }

      //  @AfterMethod
        //public void toCloseBrowser () {
          //  driver.quit();
        //}


    }




