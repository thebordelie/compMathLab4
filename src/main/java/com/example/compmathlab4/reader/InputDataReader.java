package com.example.compmathlab4.reader;

import java.io.InputStreamReader;
import java.util.ArrayList;

public interface InputDataReader<T> {
    T readDataFromStream(InputStreamReader inputStreamReader);
}
