package com.example.compmathlab4.approximation;

import com.example.compmathlab4.model.Matrix;
import com.example.compmathlab4.model.equation.AbstractFunction;
import com.example.compmathlab4.model.equation.AlgebraicFunction;
import com.example.compmathlab4.model.equation.Equation;
import com.example.compmathlab4.model.TableOfValues;
import com.example.compmathlab4.service.TableHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class QuadraticApproximation extends LeastSquaresMethod {

    @Autowired
    public QuadraticApproximation(TableHandler tableHandler) {
        super(tableHandler, 2,"Квадратичная апроксимация");
    }
}
