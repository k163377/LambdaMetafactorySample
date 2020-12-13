package com.wrongwrong;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.util.function.Function;

public class LambdaMetaFactoryWrapper {
    public static <T, R> Function<T, R> toOptimizedFunction(Method method) throws Throwable {
        // lookupはセキュリティ上の懸念も有るため使い捨てる or publicLookup()を使うのが良さそう
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // Constructorの場合はlookup.unreflectConstructorを用いる
        MethodHandle methodHandle = lookup.unreflect(method);

        CallSite callSite = LambdaMetafactory.metafactory(
                lookup,
                // 下で指定しているインターフェースの呼び出しインターフェースの名前
                "apply",
                // site.target.invokeExact()した時に出てくるインターフェースのクラス
                MethodType.methodType(Function.class),
                // 引数情報、generic()することでintとIntegerのような指定が齟齬を起こさなくなる
                methodHandle.type().generic(),
                // 呼ばれる関数のMethodHandle
                methodHandle,
                // 引数情報
                methodHandle.type()
        );

        // uncheck castは必須
        @SuppressWarnings("unchecked")
        Function<T, R> function = (Function<T, R>) callSite.getTarget().invokeExact();

        return function;
    }

    public static <P1, P2, P3, P4, P5, R> Function5<P1, P2, P3, P4, P5, R> toOptimizedFunction5(
            Method method) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.unreflect(method);

        CallSite callSite = LambdaMetafactory.metafactory(
                lookup,
                // 下で指定しているインターフェースの呼び出しインターフェースの名前
                "invoke",
                // site.target.invokeExact()した時に出てくるインターフェースのクラス
                MethodType.methodType(Function5.class),
                // 引数情報、generic()することでintとIntegerのような指定が齟齬を起こさなくなる
                methodHandle.type().generic(),
                // 呼ばれる関数のMethodHandle
                methodHandle,
                // 引数情報
                methodHandle.type()
        );

        //noinspection unchecked
        return (Function5<P1, P2, P3, P4, P5, R>) callSite.getTarget().invokeExact();
    }
}
