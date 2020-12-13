package com.wrongwrong;

public class SampleClass {
    private final int arg;

    public SampleClass(int arg) {
        this.arg = arg;
    }

    public static SampleClass factory(int arg) {
        return new SampleClass(arg);
    }

    public static SampleClass sum(int arg1, int arg2, int arg3, int arg4, int arg5) {
        return new SampleClass(arg1 + arg2 + arg3 + arg4 + arg5);
    }

    public int getArg() {
        return arg;
    }
}
