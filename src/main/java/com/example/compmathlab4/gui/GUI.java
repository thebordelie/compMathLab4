package com.example.compmathlab4.gui;

import com.example.compmathlab4.graph.Graph;
import com.example.compmathlab4.model.equation.Equation;
import com.example.compmathlab4.model.TableOfValues;
import com.example.compmathlab4.reader.InputDataReader;
import com.example.compmathlab4.service.EquationService;
import org.jfree.ui.RefineryUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;

@Component
public class GUI extends AbstractGUI<TableOfValues, ArrayList<Equation>> {
    private final InputDataReader<TableOfValues> tableReader;
    private final EquationService service;
    private TableOfValues table;

    @Autowired
    public GUI(InputDataReader<TableOfValues> reader, EquationService service) {
        this.tableReader = reader;
        this.service = service;
    }

    @Override
    public TableOfValues getDataFromUser() {
        System.out.println("Привет пользователь");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TableOfValues table;
        while (true) {
            try {
                System.out.println("Выбери способ ввода таблицы\n1. С консоли\n2. Из файла");
                String userAnswer = reader.readLine();
                switch (userAnswer) {
                    case "1":
                        table = tableReader.readDataFromStream(new InputStreamReader(System.in));
                        break;
                    case "2":
                        System.out.println("Введите название файла");
                        userAnswer = reader.readLine();
                        table = tableReader.readDataFromStream(new FileReader(userAnswer));
                        if (table == null) throw new NumberFormatException();
                        break;
                    default:
                        throw new NumberFormatException();
                }
                break;
            } catch (FileNotFoundException e) {
                System.err.println("Данного файла не существует");
            } catch (NumberFormatException e) {
                System.err.println("Неправильно введены данные");
            } catch (IOException e) {
                System.err.println("Ошибка при выполнении");
                System.exit(-1);
            }


        }
        this.table = table;
        return table;
    }

    @Override
    public ArrayList<Equation> processData(TableOfValues data) {
        return service.getApproximationFunction(data);
    }

    @Override
    public void outputProcessedData(ArrayList<Equation> processedData) {
        Graph graph = new Graph("Графики апроксимации", "Графики", table, processedData);
        graph.pack();
        RefineryUtilities.centerFrameOnScreen(graph);
        graph.setVisible(true);
    }
}
