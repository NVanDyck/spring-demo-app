package be.refleqt.automation.config.support;

import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesBuilder {

    private final DesiredCapabilities caps;

    public DesiredCapabilitiesBuilder() {
        this(new DesiredCapabilities());
    }

    public DesiredCapabilitiesBuilder(DesiredCapabilities caps) {
        this.caps = caps;
    }

    public DesiredCapabilitiesBuilder withCapability(String capabilityName, boolean value) {
        caps.setCapability(capabilityName, value);
        return this;
    }

    public DesiredCapabilitiesBuilder withCapability(String capabilityName, String value) {
        if (!value.isEmpty()) {
            caps.setCapability(capabilityName, value);
        }
        return this;
    }

    public DesiredCapabilitiesBuilder withCapability(String key, Object value) {
        if (value != null) {
            caps.setCapability(key, value);
        }
        return this;
    }

    public DesiredCapabilities build() {
        return caps;
    }

}
