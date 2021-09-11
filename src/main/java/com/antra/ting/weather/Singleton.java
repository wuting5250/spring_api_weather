package com.antra.ting.weather;
// for practice

public class Singleton {
    private final static Singleton singleton = new Singleton();

    private Singleton() {} // this prevents other classes from using
    // the constructor to make objects.

    public static Singleton getInstance() {
        return singleton;
    }


}
