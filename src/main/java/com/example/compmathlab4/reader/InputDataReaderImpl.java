package com.example.compmathlab4.reader;

import com.example.compmathlab4.model.TableOfValues;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Component
public class InputDataReaderImpl implements InputDataReader<TableOfValues> {
    @Override
    public TableOfValues readDataFromStream(InputStreamReader inputStreamReader) {
        if (inputStreamReader instanceof FileReader) return getDataFromFile(inputStreamReader);
        return getDataFromConsole(inputStreamReader);
    }

    private TableOfValues getDataFromConsole(InputStreamReader inputStreamReader) {
        BufferedReader reader = new BufferedReader(inputStreamReader);
        while (true) {
            System.out.println("Введите количество опытов");
            try {
                int numberOfTests = Integer.parseInt(reader.readLine());
                Double[] xValues = new Double[numberOfTests];
                Double[] yValues = new Double[numberOfTests];
                System.out.println("Введите " + numberOfTests + " значений для x (через пробел)");
                String[] xValuesString = reader.readLine().split(" ");
                if (xValuesString.length != numberOfTests) throw new NumberFormatException();
                for (int valueId = 0; valueId < numberOfTests; valueId++) {
                    xValues[valueId] = Double.parseDouble(xValuesString[valueId].replaceAll(",", "."));
                }
                System.out.println("Введите " + numberOfTests + " значений для y (через пробел)");
                String[] yValuesString = reader.readLine().split(" ");
                if (yValuesString.length != numberOfTests) throw new NumberFormatException();
                for (int valueId = 0; valueId < numberOfTests; valueId++) {
                    yValues[valueId] = Double.parseDouble(yValuesString[valueId].replaceAll(",", "."));
                }
                return new TableOfValues(xValues, yValues, numberOfTests);
            } catch (IOException e) {
                System.err.println("Ошибка при выполнении");
                break;
            } catch (NumberFormatException exception) {
                System.err.println("Неправильный формат данных");
            }

        }
        return null;
    }

    private TableOfValues getDataFromFile(InputStreamReader inputStreamReader) {
        BufferedReader reader = new BufferedReader(inputStreamReader);
        try {
            int numberOfTests = Integer.parseInt(reader.readLine());
            Double[] xValues;
            Double[] yValues;
            String[] xValuesString = reader.readLine().split(" ");
            if (xValuesString.length != numberOfTests) throw new NumberFormatException();
            xValues = Arrays.stream(xValuesString).map(value->Double.parseDouble(value.replaceAll(",","."))).toArray(Double[]::new);
            String[] yValuesString = reader.readLine().split(" ");
            if (yValuesString.length != numberOfTests) throw new NumberFormatException();
            yValues = Arrays.stream(yValuesString).map(value->Double.parseDouble(value.replaceAll(",","."))).toArray(Double[]::new);
            return new TableOfValues(xValues, yValues, numberOfTests);

        } catch (IOException e) {
            System.err.println("Ошибка при выполнении");
        } catch (NumberFormatException e) {
            System.err.println();
        }

        return null;
    }
}
