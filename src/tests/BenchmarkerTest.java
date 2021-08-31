package tests;

import benchmarker.logic.Benchmarker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BenchmarkerTest {

    @Test
    void BenchmarkerTest() {
        Benchmarker benchmarker = new Benchmarker();
        assertNotNull(benchmarker);
        assertEquals(benchmarker, benchmarker.getCurrentBenchmark());
        // Gut, wenn noch Weiteres eingefügt wird.
    }
}
