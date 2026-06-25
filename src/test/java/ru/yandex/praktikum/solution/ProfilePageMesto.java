package ru.yandex.praktikum.solution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePageMesto {

    private final WebDriver driver;
    private final By activity = By.id("owner-description");
    private final By saveButton = By.xpath(".//form[@name='edit']/button[text()='Сохранить']");

    public ProfilePageMesto(WebDriver driver) {
        this.driver = driver;
    }

    public void setActivity(String value) {
        WebElement field = driver.findElement(activity);

        if (!field.isEnabled()) {
            throw new AssertionError("Activity field is disabled");
        }

        field.clear();
        field.sendKeys(value);
    }

    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);

        if (!button.isEnabled()) {
            throw new AssertionError("Save button is disabled");
        }

        button.click();
    }
}
