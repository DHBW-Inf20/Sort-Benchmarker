package tests;

import benchmarker.logic.Benchmarker;
import benchmarker.view.components.ResultPanel;
import benchmarker.view.components.SortHeader;
import benchmarker.view.components.SortSelection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SortHeaderTest {

    @Test
    void SortHeaderTest() {
        SortHeader sortHeader = new SortHeader(new Benchmarker(), new ResultPanel(new Benchmarker()), new SortSelection(new Benchmarker()));
        assertNotNull(sortHeader);
    }
}
