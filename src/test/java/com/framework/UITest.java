package com.framework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class UITest {

    @Test
    void userIsBusy(){
        // Arrange
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\14197\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");

        WebDriver driver = new ChromeDriver(options);

        // this replaces driver.manage().timeout().implicitlyWait().. as it is deprecated.
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5));

        String user = "arekkusu6";
        driver.get("https://github.com/" + user);

        // Act

        /*   userStatus grabs an element that only exists if a busy status is set. If it does appear
            it will return an empty string. otherwise it will fail and return that it was unable to locate the
            specified element */
        String userStatus;
        userStatus = driver.findElement(By.className("user-status-message-wrapper")).getText();
        // Assert

        // if the user is busy, expect a pass. otherwise it will fail
        Assertions.assertEquals("", userStatus);

        driver.close();
    }

    @Test
    void repoLinkGoesToCorrectRepo() {
        // Arrange
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\14197\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");

        WebDriver driver = new ChromeDriver(options);

        // this replaces driver.manage().timeout().implicitlyWait().. as it is deprecated.
        Wait<WebDriver> wait= new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5));

        String user = "arekkusu6";
        driver.get("https://github.com/" + user);

        // Act
        String repo = "BankApp";
        WebElement repoLink = driver.findElement(By.linkText(repo));

        repoLink.click();

        // Assert

        String url;

        try {
            Thread.sleep(800);
            url = driver.getCurrentUrl();
            Assertions.assertEquals("https://github.com/arekkusu6/" + repo, url);
        } catch (InterruptedException e) {
            Assertions.fail();
        }

        driver.close();
    }
}
