package com.framework.unitests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OverviewTab extends TestClass {
    String user = "arekkusu6";

    @BeforeEach
    void overviewTabSetup(){
        driver.get(BASE_URL + user);
    }

    @AfterEach
    void localCleanup(){
        // future cleanup
    }

    @Test
     void userIsBusy(){
        /* userStatus grabs an element that only exists if a busy status is set. */
        String userStatus;
        userStatus = driver.findElement(By.className("user-status-message-wrapper")).getText();
        // userStatus returns empty string if busy status exists
        assertEquals("", userStatus);
    }

    @Test
    void repoLinkGoesToCorrectRepo() {
        String repo = "BankApp";
        WebElement repoLink = driver.findElement(By.linkText(repo));

        repoLink.click();

        String url;
        try {
            Thread.sleep(800);
            url = driver.getCurrentUrl();
            assertEquals("https://github.com/arekkusu6/" + repo, url);
        } catch (InterruptedException e) {
            fail();
        }
    }
}
