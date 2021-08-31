package tests;

import benchmarker.utils.Addons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddonsTest {

    @Test
    void AddonsTest() {
        Addons addons = new Addons();
        assertNotNull(addons);
    }
}
