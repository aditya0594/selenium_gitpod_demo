package com.lambdatest;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

public class LambaTestMobile {
    DesiredCapabilities caps = new DesiredCapabilities();
    private static AppiumDriver driver;

   @Test
           public void AndroidTest() throws MalformedURLException, InterruptedException {
       // Android Test Scenario
       executeAndroidScenario();
   }

    private static void executeAndroidScenario() throws MalformedURLException, InterruptedException {

        // Hide the keyboard
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Galaxy S20");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "C:\\Users\\Aditya Pawar\\IdeaProjects\\selenium_gitpod_demo\\proverbial_android.apk");
        caps.setCapability("username", "YOUR_LAMBDATEST_USERNAME");
        caps.setCapability("accessKey", "YOUR_LAMBDATEST_ACCESS_KEY");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            // 1. Validate if the app is launched
            System.out.println("App launched successfully: " + driver.isAppInstalled("APP_PACKAGE_NAME"));

            // 2. Print welcome message
            System.out.println("Welcome message: " + driver.findElement(By.id("welcome_message")).getText());

            // 3. Click the "Color" button
            driver.findElement(By.id("color_button")).click();
            Thread.sleep(5000); // Wait for color change

            // 4. Click the "Color" button again to revert the color
            driver.findElement(By.id("color_button")).click();
            Thread.sleep(5000); // Wait for color revert

            // 5. Click the "Text" button
            driver.findElement(By.id("text_button")).click();
            Thread.sleep(5000); // Wait for text update
            System.out.println("Updated text: " + driver.findElement(By.id("updated_text")).getText());

            // 6. Click the "Toast" button
            driver.findElement(By.id("toast_button")).click();
            Thread.sleep(5000); // Wait for toast

            // 7. Click the "Notification" button
            driver.findElement(By.id("notification_button")).click();
            // Wait for notification

            // 8. Click the "GeoLocation" button
            driver.findElement(By.id("geolocation_button")).click();
            Thread.sleep(5000); // Wait for GeoLocation page to load
            // Navigate back to home page

            // 9. Click the "Browser" button
            driver.findElement(By.id("browser_button")).click();
            Thread.sleep(5000); // Wait for browser to load
            // Enter URL and click "Find" button
            driver.findElement(By.id("url_input")).sendKeys("www.lambdatest.com");
            driver.findElement(By.id("find_button")).click();
            Thread.sleep(5000); // Wait for page load

    }

    private static void executeIOSScenario() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("deviceName", "iPhone 12 Pro");
        caps.setCapability("platformVersion", "14.0");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("app", "PATH_TO_YOUR_IOS_APP_IPA_FILE");
        caps.setCapability("username", "YOUR_LAMBDATEST_USERNAME");
        caps.setCapability("accessKey", "YOUR_LAMBDATEST_ACCESS_KEY");

        driver = new IOSDriver<>(new URL("https://hub.lambdatest.com/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Perform test steps for iOS
        // 1. Verify if the app is launched
        try {
            // 1. Verify if the app is successfully launched
            System.out.println("App launched successfully: " + driver.isAppInstalled("APP_BUNDLE_ID"));

            // 2. Capture text from XCUIElementTypeTextView named "Textbox"
            String textBoxText = driver.findElement(By.name("Textbox")).getText();
            System.out.println("Text captured from Textbox: " + textBoxText);

            // 3. Click the "Colour" button to change the color
            driver.findElement(By.name("Colour")).click();
            Thread.sleep(5000); // Wait for color change

            // 4. Click on the "Text" button and print the text value
            driver.findElement(By.name("Text")).click();
            Thread.sleep(5000); // Wait for text update
            textBoxText = driver.findElement(By.name("Textbox")).getText();
            System.out.println("Updated text from Textbox: " + textBoxText);

            // 5. Click the "Toast" button
            driver.findElement(By.name("Toast")).click();
            Thread.sleep(5000); // Wait for toast

            // 6. Click the "Notification" button
            driver.findElement(By.name("Notification")).click();
            // Allow the notification by interacting with the popup

            // 7. Click the "GeoLocation" button to open the geolocation page
            driver.findElement(By.name("GeoLocation")).click();
            Thread.sleep(5000); // Wait for geolocation page to load
            Thread.sleep(5000); // Wait for 5 seconds
            // Navigate back to previous page using the device's back button

            // 8. Navigate back to the previous page using the device's back button

            // 9. Click the "Browser" button to open the browser page
            driver.findElement(By.name("Browser")).click();
            Thread.sleep(5000); // Wait for browser page to load
            // Click the "Search" button, enter "LambdaTest" in the search bar, and submit the search
            driver.findElement(By.name("Search")).click();
            driver.findElement(By.name("Search bar")).sendKeys("LambdaTest");
            driver.findElement(By.name("Submit")).click();
            Thread.sleep(5000); // Wait for search results

            // 10. Verify the status of the test, marking it as "passed."

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

