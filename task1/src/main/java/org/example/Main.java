package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int[] arr = new int[n];
        Arrays.setAll(arr, i -> ++i);

        int current = 0;
        do {
            System.out.print(arr[current]);
            current = (current + m - 1) % n;
        } while (current != 0);
    }





    //task4
    public static void main1(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(args[0]);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null ) {
                list.add(Integer.parseInt(line));

            }
        }
        Collections.sort(list);
        int median = list.size() / 2;
        int result = 0;

        for (int i = 0; i < list.size(); i++) {
            result += Math.abs(i - list.get(median));
        }
        System.out.println(result);
    }
}