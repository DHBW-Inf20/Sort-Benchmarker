package tests;

import benchmarker.logic.Benchmarker;
import benchmarker.view.components.ResultPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResultPanelTest {

    @Test
    void SortHeaderTest() {
        ResultPanel resultPanel = new ResultPanel(new Benchmarker());
        assertNotNull(resultPanel);
    }
}
