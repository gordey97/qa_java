package ru.yandex.praktikum.solution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageMesto {

    private final WebDriver driver;
    private final By profileTitle = By.className("profile__title");
    private final By editProfileButton = By.className("profile__edit-button");
    private final By activity = By.className("profile__description");

    public HomePageMesto(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadProfileData() {
        new WebDriverWait(driver, 10).until(driver ->
                driver.findElement(profileTitle).getText() != null
                        && !driver.findElement(profileTitle).getText().isEmpty()
        );
    }

    public void clickEditProfileButton() {
        driver.findElement(editProfileButton).click();
    }

    public void waitForChangedActivity(String expectedActivity) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElementLocated(activity, expectedActivity));
    }

    public String getActivityText() {
        return driver.findElement(activity).getText();
    }
}
