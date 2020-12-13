package com.wrongwrong;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaMetaFactoryWrapperTest {
    // static関数に適用した例
    @Test
    void methodTest() throws Throwable {
        Method method = SampleClass.class.getDeclaredMethod("factory", int.class);

        Function<Integer, SampleClass> optimizedFunction = LambdaMetaFactoryWrapper.toOptimizedFunction(method);

        assertEquals(new SampleClass(1).getArg(), optimizedFunction.apply(1).getArg());
    }

    // getterに適用した例
    @Test
    void getterTest() throws Throwable {
        Method method = SampleClass.class.getDeclaredMethod("getArg");

        Function<SampleClass, Integer> optimizedFunction = LambdaMetaFactoryWrapper.toOptimizedFunction(method);

        SampleClass sampleClass = new SampleClass(1);
        assertEquals(sampleClass.getArg(), optimizedFunction.apply(sampleClass));
    }

    @Test
    void function5Test() throws Throwable {
        Method method =
                SampleClass.class.getDeclaredMethod("sum", int.class, int.class, int.class, int.class, int.class);

        Function5<Integer, Integer, Integer, Integer, Integer, SampleClass> optimizedFunction =
                LambdaMetaFactoryWrapper.toOptimizedFunction5(method);

        assertEquals(15, optimizedFunction.invoke(1, 2, 3, 4, 5).getArg());
    }
}
