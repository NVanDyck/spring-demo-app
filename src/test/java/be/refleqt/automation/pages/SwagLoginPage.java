package be.refleqt.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SwagLoginPage {

    private AppiumDriver driver;
    @AndroidFindBy(accessibility = "test-Gebruikersnaam")
    @iOSXCUITFindBy(accessibility = "test-Gebruikersnaam")
    private WebElement usernameFld;
    @AndroidFindBy(accessibility = "test-Wachtwoord")
    @iOSXCUITFindBy(accessibility = "test-Wachtwoord")
    private WebElement passwordFld;
    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    private WebElement loginBtn;
    @AndroidFindBy(accessibility = "test-Error bericht")
    @iOSXCUITFindBy(accessibility = "test-Error bericht")
    private WebElement errorLbl;

    public SwagLoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public SwagLoginPage isLoaded() {
        usernameFld.isEnabled();
        return this;
    }

    public SwagProductPage loginWith(String username, String password) {
        usernameFld.sendKeys(username);
        passwordFld.sendKeys(password);
        return new SwagProductPage(driver);
    }

    public SwagLoginPage andValidateError() {
        errorLbl.isDisplayed();
        return this;
    }

}
