package tests;

import benchmarker.logic.Benchmarker;
import benchmarker.view.components.SortSelection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SortSelectionTest {

    @Test
    void SortSelectionTest() {
        SortSelection sortSelection = new SortSelection(new Benchmarker());
        assertNotNull(sortSelection);
    }
}
