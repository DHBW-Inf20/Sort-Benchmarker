package tests;

import benchmarker.logic.Benchmarker;
import benchmarker.view.GUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GUITest {

    @Test
    void GUITest() {
        GUI gui = new GUI(new Benchmarker());
        assertNotNull(gui);
    }
}
