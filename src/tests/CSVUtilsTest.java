package tests;

import benchmarker.utils.CSVUtils;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CSVUtilsTest {

    @Test
    void CSVUtilsTest() {
        CSVUtils csv = new CSVUtils(new File("TestPath"));
        assertNotNull(csv);
    }
}
