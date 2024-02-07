package be.refleqt.automation.steps;

import be.refleqt.automation.pages.SwagLoginPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class SwagSteps {

    private final AppiumDriver appiumDriver;

    @Given("the app is loaded")
    public void iCheckTheApp() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        new SwagLoginPage(appiumDriver).isLoaded();
    }

    @When("i login with:")
    public void iLoginWith(Map<String, String> entry) {
        new SwagLoginPage(appiumDriver)
                .loginWith(
                        entry.getOrDefault("username", "placeholder"),
                        entry.getOrDefault("password", "placeholder"));
    }

    @Then("an error is expected")
    public void loginErrorIsDisplayed() {
        new SwagLoginPage(appiumDriver)
                .andValidateError();
    }

    @Then("a new error is expected")
    public void loginErrorIsDisplayedNew() {
        this.loginErrorIsDisplayed();
    }


    @Then("an other new error is expected")
    public void loginErrorIsDisplayedOther() {
        new SwagLoginPage(appiumDriver)
                .andValidateError();
    }

}
