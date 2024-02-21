package com.lambdatest;

import java.awt.*;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTodo3 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ?  "adityapawar180"  : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "6wud0mTK0TXmhC7F76FfTNByrGdUNLIIRtZ5eG8Oww7xYOkPYl" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "MacOS Catalina");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Tag", "Moderate" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }

    @Test
    public void Scanario_3() throws InterruptedException, AWTException {
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground");
        driver.findElement(By.linkText("Input Form Submit")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300);");


        WebElement SubmitBtn = driver.findElement(By.xpath("//button[normalize-space()='Submit']"));
        SubmitBtn.click();
        Thread.sleep(5000);

        String errorMessage = driver.findElement(By.xpath("//input[@id='name']")).getAttribute("validationMessage");
        System.out.println("errorMessage :"+ errorMessage);
        Assert.assertEquals("Please fill out this field.",errorMessage);
        Robot robot = new Robot();

        driver.findElement(By.name("name")).sendKeys("Aditya Pawar" + Keys.ENTER);
        driver.findElement(By.xpath("//input[@id='inputEmail4']")).sendKeys("Aditya@mailinator.com" + Keys.ENTER);
        driver.findElement(By.xpath("//input[@id='inputPassword4']")).sendKeys("Aditya@123");
        driver.findElement(By.name("company")).sendKeys("XYZ");
        driver.findElement(By.name("website")).sendKeys("www.google.com");


        WebElement countryDropdown = driver.findElement(By.name("country"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countryDropdown);
        countryDropdown.sendKeys("United States");
        Thread.sleep(5000);

        driver.findElement(By.name("city")).sendKeys("Nagpur");

        driver.findElement(By.name("address_line1")).sendKeys("Chatrapati");

        driver.findElement(By.name("address_line2")).sendKeys("Wardha road");

        driver.findElement(By.id("inputState")).sendKeys("Maharashtra");

        driver.findElement(By.name("zip")).sendKeys("440015");

        SubmitBtn.click();

        WebElement successMessage = driver.findElement(By.xpath("//p[@class='success-msg hidden']"));
        assert successMessage.getText().equals("Thanks for contacting us, we will get back to you shortly.");


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}