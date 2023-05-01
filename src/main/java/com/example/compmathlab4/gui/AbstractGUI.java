package com.example.compmathlab4.gui;

abstract public class AbstractGUI<T, V> {
    abstract public T getDataFromUser();
    abstract public V processData(T data);
    abstract public void outputProcessedData(V processedData);
    public void runApp() {
        T inputData = getDataFromUser();
        V processedData = processData(inputData);
        outputProcessedData(processedData);
    }
}
