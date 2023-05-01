package com.example.compmathlab4.service;

import com.example.compmathlab4.model.equation.Equation;
import com.example.compmathlab4.model.TableOfValues;

public interface TableHandler {
    double calculateSumOfMultiOfXAndY(int degreeForX, int degreeForY, TableOfValues table);

    String getTable(Equation equation, TableOfValues table);

    double calculateDeviationMeasure(Equation equation, TableOfValues table);

    double calculateStandardDeviation(Equation equation, TableOfValues table);
}
