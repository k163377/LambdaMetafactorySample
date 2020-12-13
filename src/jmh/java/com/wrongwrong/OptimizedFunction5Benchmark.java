package com.wrongwrong;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@State(Scope.Benchmark)
public class OptimizedFunction5Benchmark {
    private Method method;
    private Function5<Integer, Integer, Integer, Integer, Integer, SampleClass> function5;

    public OptimizedFunction5Benchmark() {
        try {
            method =
                    SampleClass.class.getDeclaredMethod("sum", int.class, int.class, int.class, int.class, int.class);
            function5 = LambdaMetaFactoryWrapper.toOptimizedFunction5(method);
        } catch (Throwable t) {
            System.out.println(t);
            method = null;
            function5 = null;
        }
    }

    @Benchmark
    public SampleClass directCall() {
        return SampleClass.sum(1, 2, 3, 4, 5);
    }

    @Benchmark
    public SampleClass methodCall() throws InvocationTargetException, IllegalAccessException {
        return (SampleClass) method.invoke(null, 1, 2, 3, 4, 5);
    }

    @Benchmark
    public SampleClass optimizedFunction5Call() {
        return function5.invoke(1, 2, 3, 4, 5);
    }
}
