package ru.yandex.praktikum.solution;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assume;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

public class EditActivityTest {

    private WebDriver driver;

    @Test
    public void checkActivity() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.get("https://qa-mesto.praktikum-services.ru/");

        String email = getCredential("MESTO_EMAIL", "mesto.email");
        String password = getCredential("MESTO_PASSWORD", "mesto.password");
        String expectedActivity = "Тестировщик";

        LoginPageMesto loginPage = new LoginPageMesto(driver);
        loginPage.login(email, password);

        HomePageMesto homePage = new HomePageMesto(driver);
        homePage.waitForLoadProfileData();
        homePage.clickEditProfileButton();

        ProfilePageMesto profilePage = new ProfilePageMesto(driver);
        profilePage.setActivity(expectedActivity);
        profilePage.clickSaveButton();

        homePage.waitForChangedActivity(expectedActivity);
        assertEquals(expectedActivity, homePage.getActivityText());
    }

    private String getCredential(String envName, String propertyName) {
        String value = System.getenv(envName);

        if (value == null || value.isEmpty()) {
            value = System.getProperty(propertyName);
        }

        Assume.assumeTrue(value != null && !value.isEmpty());
        return value;
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
