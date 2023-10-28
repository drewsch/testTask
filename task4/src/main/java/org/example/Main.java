package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(args[0]);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(list);
        int median = (int) Math.floor((double) list.size() / 2);
        int result = 0;
        for (Integer number : list) {
            result += Math.abs(number - list.get(median));
        }
        System.out.println(result);
    }
}