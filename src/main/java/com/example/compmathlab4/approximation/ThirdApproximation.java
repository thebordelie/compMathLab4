package com.example.compmathlab4.approximation;

import com.example.compmathlab4.service.TableHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThirdApproximation extends LeastSquaresMethod{

    @Autowired
    public ThirdApproximation(TableHandler tableHandler) {
        super(tableHandler, 3, "Кубическая апроксимация");
    }
}
