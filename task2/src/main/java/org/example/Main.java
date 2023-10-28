package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String circleFile = args[0];
        String pointsFile = args[1];

        try (BufferedReader circleReader = new BufferedReader(new FileReader(circleFile));
             BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile))) {

            String[] circleData = circleReader.readLine().split(" ");
            float centerX = Float.parseFloat(circleData[0]);
            float centerY = Float.parseFloat(circleData[1]);
            circleData = circleReader.readLine().split(" ");
            float radius = Float.parseFloat(circleData[0]);

            String line;
            while ((line = pointsReader.readLine()) != null) {
                String[] pointData = line.split(" ");
                float x = Float.parseFloat(pointData[0]);
                float y = Float.parseFloat(pointData[1]);

                int position = checkPointPosition(centerX, centerY, radius, x, y);
                System.out.println(position);
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Функция определения положения точки относительно окружности
    private static int checkPointPosition(float centerX, float centerY, float radius, float x, float y) {
        float distance = (float) Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
        if (distance < radius) {
            return 1; // Точка внутри окружности
        } else if (distance > radius) {
            return 2; // Точка снаружи окружности
        } else {
            return 0; // Точка на окружности
        }
    }

}