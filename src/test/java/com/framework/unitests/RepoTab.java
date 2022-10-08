package com.framework.unitests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepoTab extends TestClass {

    @Test
    void amountOfReposAccurate(){
        driver.get("https://github.com/" + "arekkusu6/" + "?tab=repositories");
        List<WebElement> repos = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));
        assertEquals(5, (repos).size());
    }
}
