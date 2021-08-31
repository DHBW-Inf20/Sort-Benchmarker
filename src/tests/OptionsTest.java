package tests;

import benchmarker.utils.options.Options;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OptionsTest {

    @Test
    void OptionsTest() {
        Options options = new Options();
        assertNotNull(options);
        // Gut, wenn noch Weiteres eingefügt wird.
    }
}
