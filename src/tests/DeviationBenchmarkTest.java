package tests;

import benchmarker.logic.benchmarks.DeviationBenchmark;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeviationBenchmarkTest {

    @Test
    void DeviationBenchmarkTest() {
        DeviationBenchmark deviationBenchmark = new DeviationBenchmark();
        assertNotNull(deviationBenchmark);
        assertEquals("Abweichung Benchmark", deviationBenchmark.getName());
    }
}
