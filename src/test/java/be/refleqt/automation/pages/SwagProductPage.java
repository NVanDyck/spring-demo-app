package be.refleqt.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SwagProductPage {

    private final AppiumDriver driver;
    @AndroidFindBy(accessibility = "test-Winkelwagen drop zone")
    @iOSXCUITFindBy(accessibility = "PRODUKTEN")
    private WebElement homeItem;

    public SwagProductPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkProductsAreShown() {
        homeItem.isDisplayed();
    }

}
