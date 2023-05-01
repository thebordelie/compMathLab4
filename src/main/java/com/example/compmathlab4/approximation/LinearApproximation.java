package com.example.compmathlab4.approximation;

import com.example.compmathlab4.model.equation.AlgebraicFunction;
import com.example.compmathlab4.model.equation.Equation;
import com.example.compmathlab4.service.TableHandlerImpl;
import com.example.compmathlab4.model.TableOfValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("LinearApproximation")
public class LinearApproximation extends LeastSquaresMethod {

    @Autowired
    public LinearApproximation(TableHandlerImpl tableHandler) {
        super(tableHandler, 1, "Линейная апроксимация");
    }
}
