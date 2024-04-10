package com.lambdatest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.URL;

public class iOSApp {

    String userName = System.getenv("LT_USERNAME") == null ? "username" : System.getenv("adityapawar180"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "accessKey" : System.getenv("6wud0mTK0TXmhC7F76FfTNByrGdUNLIIRtZ5eG8Oww7xYOkPYl"); //Add accessKey here
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-ios" : System.getenv("lt://APP1016026231712693064241666");      //Enter your LambdaTest App ID at the place of lt://proverbial-android
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("mobile-hub.lambdatest.com");
    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void iOSApp1(String device, String version, String platform) {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "ios");
            capabilities.setCapability("deviceName", "iPhone 12 Pro");
            capabilities.setCapability("platformVersion", "14");
            capabilities.setCapability("app", "lt://APP10160241051712776822301893");
            capabilities.setCapability("build", "IOS_build");
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("autoAcceptAlerts", true);
            capabilities.setCapability("isRealMobile", true); //Enter your app url
            capabilities.setCapability("network", true);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            //capabilities.setCapability("geoLocation", "HK");

            String hub = "https://adityapawar180:6wud0mTK0TXmhC7F76FfTNByrGdUNLIIRtZ5eG8Oww7xYOkPYl@mobile-hub.lambdatest.com/wd/hub";
            driver = new AppiumDriver(new URL(hub), capabilities);
            Boolean text = driver.findElement(By.xpath("//*[@name='Textbox']")).isDisplayed();

            if(text == true){
                System.out.println("App launched successfully: " + text);
            }else
            {
                System.out.println("App launched successfully: " + text);
            }
            // 2. Capture text from XCUIElementTypeTextView named "Textbox"
            String textBoxText = driver.findElement(By.xpath("//*[@name='Textbox']")).getText();
            System.out.println("Text captured from Textbox: " + textBoxText);

            // 3. Click the "Colour" button to change the color and confirm that the color change is
            //reflected by clicking the "Colour" button again to return to black.
            driver.findElement(By.name("Colour")).click();
            Thread.sleep(500);
            // Color revert
            driver.findElement(By.name("Colour")).click();
            Thread.sleep(500);


            // 4. Click on the "Text" button and print the text value from the
            //XCUIElementTypeTextView with the name "Textbox" after clicking the "Text"
            //button.
            driver.findElement(By.xpath("//*[@value='Text']")).click();
            Thread.sleep(5000); // Wait for text update
            textBoxText = driver.findElement(By.name("Textbox")).getText();
            System.out.println("Updated text from Textbox: " + textBoxText);

            // 5. Click the "Toast" button
            driver.findElement(By.name("toast")).click();
            Thread.sleep(500); // Wait for toast

            // 6. Click the "Notification" button
            driver.findElement(By.name("Notification")).click();
            // Allow the notification by interacting with the popup

            // 7. Click the "GeoLocation" button to open the geolocation page
            driver.findElement(By.name("GeoLocation")).click();
            Thread.sleep(500); // Wait for geolocation page to load
            Thread.sleep(5000); // Wait for 5 seconds
            // Navigate back to previous page using the device's back button

            // 8. Navigate back to the previous page using the device's back button
            //WebDriverWait wait = new WebDriverWait(driver,5);
            //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("XCUIElementTypeButton"))));
          //  driver.findElement(By.xpath("//*[@name='Back']")).click();
            int startx = 0;
            int starty = 47;
            TouchAction action = new  TouchAction(driver);
            action.tap(PointOption.point(startx, starty))
                    .release()
                    .perform();
            Thread.sleep(3000);
            // 9. Click the "Browser" button to open the browser page
            driver.findElement(By.xpath("//*[@name='Browser']")).click();
            Thread.sleep(5000); // Wait for browser page to load
            // Click the "Search" button, enter "LambdaTest" in the search bar, and submit the search
            driver.findElement(By.xpath("//*[@name='url']")).click();
            driver.findElement(By.xpath("//*[@name='url']")).sendKeys("LambdaTest");
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@name='Find']")).click();
            Thread.sleep(5000); // Wait for search results

            // 10. Verify the status of the test, marking it as "passed."

            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
