package tests;

import benchmarker.utils.options.Option;
import org.junit.jupiter.api.Test;

import static benchmarker.utils.options.OptionType.NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OptionTest {

    @Test
    void OptionTest() {
        Option option = new Option("TestOption", NUMBER, 8);
        assertNotNull(option);
        assertEquals("TestOption", option.getOption());
        assertEquals(NUMBER, option.getOptionType());
        assertEquals(8, option.getDefaultValue());
    }
}
