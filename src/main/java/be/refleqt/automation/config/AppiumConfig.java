package be.refleqt.automation.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import be.refleqt.automation.config.support.DesiredCapabilitiesBuilder;

import java.net.URL;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Configuration
@RequiredArgsConstructor
@Lazy
public class AppiumConfig {

    private static final String WARN = "warn";
    private static final String DEBUG = "debug";
    private static final String APPIUM = "appium:";
    private static final String LOCALHOST = "127.0.0.1";

    @Value("${automation.appium.os:placeholder}")
    private String os;

    @Scope(SCOPE_CUCUMBER_GLUE)
    @Bean(name = "appiumDriver", destroyMethod = "quit")
    public AppiumDriver appiumDriver(URL appiumServiceUrl) {
        return this.isAndroid() ? createAndroidDriver(appiumServiceUrl) : createIosDriver(appiumServiceUrl);
    }

    private AppiumDriver createAndroidDriver(URL appiumServiceUrl) {
        return new AndroidDriver(appiumServiceUrl, this.getAndroidCapabilities());
    }

    private AppiumDriver createIosDriver(URL appiumServiceUrl) {
        return new IOSDriver(appiumServiceUrl, this.getIosCapabilities());
    }

    ///////////////////////////////////////////////////////////////
    // Service
    ///////////////////////////////////////////////////////////////

    @Bean(initMethod = "start", destroyMethod = "stop")
    AppiumDriverLocalService appiumService() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimeout", 180);

        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.LOG_LEVEL, WARN)
                .withCapabilities(capabilities)
                .withIPAddress(LOCALHOST)
                .withArgument(() -> "--base-path", "/wd/hub"));
    }

    @Bean
    URL appiumServiceUrl(AppiumDriverLocalService service) {
        return service.getUrl();
    }

    ///////////////////////////////////////////////////////////////
    // Helper
    ///////////////////////////////////////////////////////////////

    public boolean isAndroid() {
        return MobilePlatform.ANDROID.equalsIgnoreCase(os);
    }

    private Capabilities getAndroidCapabilities() {
        return new DesiredCapabilitiesBuilder()
                .withCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
                .withCapability(APPIUM + MobileCapabilityType.UDID, "emulator-5554")
                .withCapability(APPIUM + MobileCapabilityType.PLATFORM_VERSION, "13.0")
                .withCapability(APPIUM + MobileCapabilityType.APP, "/Users/nick/Desktop/Hogent/refleqt-spring-automation/src/test/resources/app/DEMO_APK.zip")
                .withCapability(APPIUM + MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60)
                .withCapability(APPIUM + MobileCapabilityType.FULL_RESET, true)
                .withCapability(APPIUM + MobileCapabilityType.LANGUAGE, "NL")
                .withCapability(APPIUM + MobileCapabilityType.LOCALE, "BE")
                .withCapability(APPIUM + MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2)
                .withCapability(APPIUM + AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, false)
                .withCapability(APPIUM + AndroidMobileCapabilityType.APP_ACTIVITY,"MainActivity")
                .withCapability(APPIUM + AndroidMobileCapabilityType.APP_PACKAGE,"com.swaglabsmobileapp")
                .build();
    }

    private Capabilities getIosCapabilities() {
        return new DesiredCapabilitiesBuilder()
                .withCapability(MobileCapabilityType.PLATFORM_NAME, "iOS")
                .withCapability(APPIUM + MobileCapabilityType.UDID, "2D2A867D-0F68-4AFC-9064-88242C6B77FB")
                .withCapability(APPIUM + MobileCapabilityType.PLATFORM_VERSION, "16.2")
                .withCapability(APPIUM + MobileCapabilityType.APP, "/Users/nick/Desktop/Hogent/refleqt-spring-automation/src/test/resources/app/DEMO_APP.zip")
                .withCapability(APPIUM + MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60)
                .withCapability(APPIUM + MobileCapabilityType.FULL_RESET, true)
                .withCapability(APPIUM + MobileCapabilityType.LANGUAGE, "NL")
                .withCapability(APPIUM + MobileCapabilityType.LOCALE, "BE")
                .withCapability(APPIUM + MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST)
                .withCapability(APPIUM + IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, false)
                .build();
    }


}