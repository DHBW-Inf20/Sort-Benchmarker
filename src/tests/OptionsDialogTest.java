package tests;

import benchmarker.utils.options.Options;
import benchmarker.view.components.OptionsDialog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OptionsDialogTest {

    @Test
    void SortHeaderTest() {
        OptionsDialog optionsDialog = new OptionsDialog("OptionsTest", "ButtonTest", new Options());
        assertNotNull(optionsDialog);
    }
}
