import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class LoginPageMesto {

    private final WebDriver driver;
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By signInButton = By.className("auth-form__button");

    public LoginPageMesto(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(emailField).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickSignInButton();
    }
}

class HomePageMesto {

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
}

class ProfilePageMesto {

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

public class Praktikum {

    private WebDriver driver;

    @Test
    public void checkActivity() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.get("https://qa-mesto.praktikum-services.ru/");

        LoginPageMesto loginPage = new LoginPageMesto(driver);
        loginPage.login("ТВОЙ_EMAIL", "ТВОЙ_ПАРОЛЬ");

        HomePageMesto homePage = new HomePageMesto(driver);
        homePage.waitForLoadProfileData();
        homePage.clickEditProfileButton();

        ProfilePageMesto profilePage = new ProfilePageMesto(driver);
        String expectedActivity = "Тестировщик";

        profilePage.setActivity(expectedActivity);
        profilePage.clickSaveButton();
        homePage.waitForChangedActivity(expectedActivity);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
