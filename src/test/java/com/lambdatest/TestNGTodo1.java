package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTodo1 {

    private RemoteWebDriver driver;

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "adityapawar180" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "6wud0mTK0TXmhC7F76FfTNByrGdUNLIIRtZ5eG8Oww7xYOkPYl" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "MacOS Catalina");
        caps.setCapability("browserName", "Safari");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };

        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }


    @Test
    public void Scanario_1() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground");

        driver.findElement(By.xpath("//a[normalize-space()='Simple Form Demo']")).click();
        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("simple-form-demo")) {
            System.out.println("URL validation passed for Scenario 1");
        } else {
            System.out.println("URL validation failed for Scenario 1");
        }

        // 4. Create a variable for a string value E.g: “Welcome to LambdaTest”.

        String message = "Welcome to LambdaTest";
        //5. Use this variable to enter values in the “Enter Message” text box.
        driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(message);

        driver.findElement(By.xpath("//button[@id='showInput']")).click();
        String displayedMessage = driver.findElement(By.xpath("//p[@id='message']")).getText();
        if (displayedMessage.equals(message)) {
            System.out.println("Message validation passed for Scenario 1");
        } else {
            System.out.println("Message validation failed for Scenario 1");
        }

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}