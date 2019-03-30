package com.yxt.network;

public class NetUrl {
    static {
        System.loadLibrary("native-lib");
    }
    public static String getNetConnect(int num) {
        return stringFromJNI(num);
    }

    private native static String stringFromJNI(int num);
}


