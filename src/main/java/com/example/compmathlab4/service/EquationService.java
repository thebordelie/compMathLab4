package com.example.compmathlab4.service;

import com.example.compmathlab4.approximation.ApproximationFunction;
import com.example.compmathlab4.model.equation.Equation;
import com.example.compmathlab4.model.TableOfValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class EquationService {
    private final TableHandler handler;
    private final ArrayList<ApproximationFunction> methodsOfApproximation;

    @Autowired
    public EquationService(TableHandler handler, ApproximationFunction... methods) {
        this.handler = handler;
        methodsOfApproximation = new ArrayList<>();
        this.methodsOfApproximation.addAll(Arrays.asList(methods));
    }

    public ArrayList<Equation> getApproximationFunction(TableOfValues table) {
        ArrayList<Equation> equations = new ArrayList<>();
        System.out.println("Таблица:\n" + table.toString());
        methodsOfApproximation.forEach(method -> {
            System.out.println("Способ апроксимации: " + method.toString());
            Equation equation = method.findFunction(table);
            if (equation.getEquation().get(0).getCoefficients().contains(Double.NaN)) {
                System.out.println("Не получилось апроксимировать данным методом");
            }
            else {
                equations.add(equation);
                System.out.println("Полученное уравнение: " + equation+"\n");
                System.out.println(handler.getTable(equation, table));
                System.out.printf("e^2: %f\n", handler.calculateDeviationMeasure(equation, table));
                double approximation = handler.calculateStandardDeviation(equation, table);
                System.out.printf("Отклонение: %f\n", approximation);
            }
            System.out.println("\n----------------------------------------\n");

        });
        Equation bestEquation = new Equation(0);
        double bestApproximation = Double.MAX_VALUE;
        for (Equation equation : equations) {
            double approximation = handler.calculateStandardDeviation(equation, table);
            if (approximation < bestApproximation) {
                bestApproximation = approximation;
                bestEquation = equation;
            }
        }
        System.out.println("Лучшая апроксимирующая функция: " + bestEquation);
        return equations;
    }
}
