package tests;

import benchmarker.utils.CSVUtils;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class CSVUtilsTest {

    @Test
    void CSVUtilsTest() {
        CSVUtils csv = new CSVUtils(new File("TestPath"));
        csv.setHeader(new String[] {"Title1", "Title2"});
        assertFalse(csv.addContent(new String[] {"Daten1"}));
        assertTrue(csv.addContent(new String[] {"Daten1", "Daten2"}));
        assertFalse(csv.addContent(new String[] {"Daten1", "Daten2", "Daten3"}));
    }
}
