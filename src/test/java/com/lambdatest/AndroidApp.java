package com.lambdatest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class AndroidApp {

    String userName = System.getenv("LT_USERNAME") == null ? "username" : System.getenv("adityapawar180"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "accessKey" : System.getenv("6wud0mTK0TXmhC7F76FfTNByrGdUNLIIRtZ5eG8Oww7xYOkPYl"); //Add accessKey here
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-android" : System.getenv("lt://APP1016061471712775866443742");      //Enter your LambdaTest App ID at the place of lt://proverbial-android
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("mobile-hub.lambdatest.com/wd/hub");

    AppiumDriver driver;

    @Test
    @Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability("deviceName", "Galaxy S20");
            capabilities.setCapability("platformVersion", "11");
            capabilities.setCapability("app", "lt://APP10160161211712776311769271");
            capabilities.setCapability("build", "Android_build");
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("autoAcceptAlerts", true);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("network", true);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);

            //capabilities.setCapability("geoLocation", "HK");
//@mobile-hub.lambdatest.com/wd/hub
            String hub = "https://adityapawar180:6wud0mTK0TXmhC7F76FfTNByrGdUNLIIRtZ5eG8Oww7xYOkPYl@mobile-hub.lambdatest.com/wd/hub";
            driver = new AppiumDriver(new URL(hub), capabilities);
            Thread.sleep(3000);
            // 1. Validate if the app is launched
            System.out.println("App launched successfully: " + driver.isAppInstalled("com.lambdatest.proverbial"));
            //APP_PACKAGE_NAME
            String expectedText = "Hello! Welcome to lambdatest Sample App called Proverbial";
            AndroidElement textView = (AndroidElement) driver.findElement(By.id("com.lambdatest.proverbial:id/Textbox"));
            String actualText = textView.getText();
            if (actualText.equals(expectedText)) {
                System.out.println("App launched successfully.");
            } else {
                System.out.println("App launch failed.");
            }
            // 2. Print welcome message
            System.out.println("Welcome message: " + driver.findElement(By.id("com.lambdatest.proverbial:id/Textbox")).getText());

            // 3. Click the "Color" button
            driver.findElement(By.id("com.lambdatest.proverbial:id/color")).click();
            Thread.sleep(500);

            // 4. Click the "Color" button again to revert the color
            driver.findElement(By.id("com.lambdatest.proverbial:id/color")).click();
            Thread.sleep(500);

            // 5. Click the "Text" button
            driver.findElement(By.id("com.lambdatest.proverbial:id/Text")).click();
            Thread.sleep(500); // Wait for text update
            System.out.println("Updated text: " + driver.findElement(By.id("com.lambdatest.proverbial:id/Textbox")).getText());

            // 6. Click the "Toast" button
            driver.findElement(By.id("com.lambdatest.proverbial:id/toast")).click();
            Thread.sleep(500);

            // 7. Click the "Notification" button
            driver.findElement(By.id("com.lambdatest.proverbial:id/notification")).click();
            // Wait for notification

            // 8. Click the "GeoLocation" button
            driver.findElement(By.id("com.lambdatest.proverbial:id/geoLocation")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Home\"]")).click();
            // 9. Click the "Browser" button
            driver.findElement(By.id("com.lambdatest.proverbial:id/webview")).click();
            Thread.sleep(500);

            // Enter URL
            driver.findElement(By.className("android.widget.EditText")).sendKeys("www.lambdatest.com");
            driver.findElement(By.id("com.lambdatest.proverbial:id/find")).click();
            Thread.sleep(5000); // Wait for page load
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
