package tests;

import benchmarker.utils.Settings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SettingsTest {

    @Test
    void SettingsTest() {
        Settings settings = new Settings();
        assertNotNull(settings);
    }
}
