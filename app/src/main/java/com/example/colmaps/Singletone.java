package com.example.colmaps;

public class Singletone {
    private static Singletone ourInstance = new Singletone();

    int numElements;
    int numElementsMap;

    public static Singletone getInstance() {
        return ourInstance;
    }
}
