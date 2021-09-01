package tests;

import benchmarker.logic.Benchmark;
import benchmarker.logic.benchmarks.RuntimeBenchmark;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuntimeBenchmarkTest {

    @Test
    void runtimeBenchmarkTest() {
        RuntimeBenchmark runtimeBenchmark = new RuntimeBenchmark();
        assertEquals("Laufzeit Benchmark", runtimeBenchmark.getName());
        runtimeBenchmark.setArrayType(Benchmark.ArrayType.ASC);
        assertEquals(Benchmark.ArrayType.ASC, runtimeBenchmark.getArrayType());
    }
}
