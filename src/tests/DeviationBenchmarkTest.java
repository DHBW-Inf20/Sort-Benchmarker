package tests;

import benchmarker.logic.Benchmark;
import benchmarker.logic.benchmarks.DeviationBenchmark;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeviationBenchmarkTest {

    @Test
    void deviationBenchmarkTest() {
        DeviationBenchmark deviationBenchmark = new DeviationBenchmark();
        assertEquals("Abweichung Benchmark", deviationBenchmark.getName());
        deviationBenchmark.setArrayType(Benchmark.ArrayType.ASC);
        assertEquals(Benchmark.ArrayType.ASC, deviationBenchmark.getArrayType());
    }
}
