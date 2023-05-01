package com.example.compmathlab4.approximation;

import com.example.compmathlab4.model.equation.Equation;
import com.example.compmathlab4.model.TableOfValues;

public interface ApproximationFunction {
    Equation findFunction(TableOfValues table);

}
