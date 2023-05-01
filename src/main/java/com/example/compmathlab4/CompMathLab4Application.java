package com.example.compmathlab4;

import com.example.compmathlab4.config.CompLab4Config;
import com.example.compmathlab4.gui.AbstractGUI;
import com.example.compmathlab4.model.Matrix;
import com.example.compmathlab4.model.equation.Equation;
import com.example.compmathlab4.model.TableOfValues;
import com.example.compmathlab4.service.TableHandlerImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class CompMathLab4Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CompLab4Config.class);
        AbstractGUI<TableOfValues, ArrayList<Equation>> gui = context.getBean(AbstractGUI.class);
        gui.runApp();



    }

}
