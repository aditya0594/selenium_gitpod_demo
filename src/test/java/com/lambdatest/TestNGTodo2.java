package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTodo2 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "adityapawar180" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "6wud0mTK0TXmhC7F76FfTNByrGdUNLIIRtZ5eG8Oww7xYOkPYl" : System.getenv("LT_ACCESS_KEY");
        ;
        
        /*
        Steps to run Smart UI project (https://beta-smartui.lambdatest.com/)
        Step - 1 : Change the hub URL to @beta-smartui-hub.lambdatest.com/wd/hub
        Step - 2 : Add "smartUI.project": "<Project Name>" as a capability above
        Step - 3 : Add "((JavascriptExecutor) driver).executeScript("smartui.takeScreenshot");" code wherever you need to take a screenshot
        Note: for additional capabilities navigate to https://www.lambdatest.com/support/docs/test-settings-options/
        */

        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        /*
        Enable Smart UI Project
        caps.setCapability("smartUI.project", "<Project Name>");
        */

        String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }

    @Test
    public void Scanario_2() throws InterruptedException {
        driver.manage().window().maximize();
        //1. Open the https://www.lambdatest.com/selenium-playground page and click “Drag & Drop Sliders” under “Progress Bars & Sliders”
        driver.get("https://www.lambdatest.com/selenium-playground");

        driver.findElement(By.linkText("Drag & Drop Sliders")).click();
        Thread.sleep(1000);



        WebElement slider = driver.findElement(By.xpath("//input[@value='15']"));
        // Simulate dragging the slider to move it
        Actions sliderAction = new Actions(driver);
        sliderAction.clickAndHold(slider).moveByOffset(212, 0).release().perform();

        // Wait for the slider to be adjusted
        Thread.sleep(1000);


        WebElement rangeValue = driver.findElement(By.xpath("//output[@id='rangeSuccess']"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//output[@id='rangeSuccess']")));
        int currentValue = Integer.parseInt(rangeValue.getText());
        System.out.println("current value is : "+ currentValue);
        if (currentValue == 95) {
            System.out.println("Slider validation passed for  Scenario 2");
        } else {
            System.out.println("Slider validation failed for Scenario 2");
        }


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}