package com.example.compmathlab4.service;

import com.example.compmathlab4.approximation.ApproximationFunction;
import com.example.compmathlab4.approximation.LinearApproximation;
import com.example.compmathlab4.model.equation.Equation;
import com.example.compmathlab4.model.TableOfValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            } else {
                equations.add(equation);
                System.out.println("Полученное уравнение: " + equation + "\n");
                System.out.println(handler.getTable(equation, table));
                System.out.printf("e^2: %f\n", handler.calculateDeviationMeasure(equation, table));
                double approximation = handler.calculateStandardDeviation(equation, table);
                System.out.printf("Отклонение: %f\n", approximation);
                if (method instanceof LinearApproximation) {
                    double xAverage = calculateAverageValue((ArrayList<Double>) table.getXValues());
                    double yAverage = calculateAverageValue((ArrayList<Double>) table.getYValues());
                    double sum1 = 0;
                    double sum2 = 0;
                    double sum3 = 0;
                    List<Double> xValues = table.getXValues();
                    List<Double> yValues = table.getYValues();
                    for (int counter = 0; counter < table.getTableSize(); counter++) {
                        double xDifference = xValues.get(counter) - xAverage;
                        double yDifference = yValues.get(counter) - yAverage;
                        sum1 += xDifference * yDifference;
                        sum2 += Math.pow(xDifference, 2);
                        sum3 += Math.pow(yDifference, 2);
                    }
                    double coefficient = sum1 / Math.sqrt(sum2 * sum3);
                    if (!Double.isNaN(coefficient)) System.out.printf("Коэффициент Пирсона = %.3f\n", coefficient);

                }
            }
            System.out.println("\n----------------------------------------\n");

        });
        Equation bestEquation = new Equation(0);
        double bestApproximation = Double.MAX_VALUE;
        for (Equation equation : equations) {
            double approximation = handler.calculateStandardDeviation(equation, table);
            if (approximation <= bestApproximation) {
                bestApproximation = approximation;
                bestEquation = equation;
            }
        }
        System.out.println("Лучшая апроксимирующая функция: " + bestEquation + "\nМетод: " + bestEquation.getInformation());
        return equations;
    }

    private double calculateAverageValue(ArrayList<Double> values) {
        double average = 0;
        for (double value : values) {
            average += value;
        }
        average /= values.size();
        return average;
    }
}
