package tests;

import benchmarker.logic.benchmarks.RuntimeBenchmark;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RuntimeBenchmarkTest {

    @Test
    void RuntimeBenchmarkTest() {
        RuntimeBenchmark runtimeBenchmark = new RuntimeBenchmark();
        assertNotNull(runtimeBenchmark);
        assertEquals("Laufzeit Benchmark", runtimeBenchmark.getName());
    }
}
