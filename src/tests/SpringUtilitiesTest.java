package tests;

import benchmarker.utils.layout.SpringUtilities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpringUtilitiesTest {

    @Test
    void SpringUtilitiesTest() {
        SpringUtilities springUtilities = new SpringUtilities();
        assertNotNull(springUtilities);
    }
}
