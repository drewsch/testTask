package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ReportGenerator tests.json values.json");
            return;
        }

        String testsFile = args[0];
        String valuesFile = args[1];

        try {
            Object testsObj = new JSONParser().parse(new FileReader(testsFile));
            Object valuesObj = new JSONParser().parse(new FileReader(valuesFile));

            JSONObject testsJson = (JSONObject) testsObj;
            JSONObject valuesJson = (JSONObject) valuesObj;

            JSONArray testsArray = (JSONArray) testsJson.get("tests");
            JSONArray valuesArray = (JSONArray) valuesJson.get("values");

            for (Object item : testsArray) {
                fillValues((JSONObject) item, valuesArray);
            }

            FileWriter fileWriter = new FileWriter("report.json");
            fileWriter.write(testsJson.toJSONString());
            fileWriter.close();

            System.out.println("report.json file has been generated successfully.");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void fillValues(JSONObject test, JSONArray values) {
        if (test.containsKey("id")) {
            Object value = getValueById(values, (Long) test.get("id"));
            if (value != "") {
                test.put("value", value);
            }
        }

        if (test.containsKey("values")) {
            JSONArray nestedTests = (JSONArray) test.get("values");
            for (Object nestedTest : nestedTests) {
                JSONObject nestedTestObj = (JSONObject) nestedTest;
                Object value = getValueById(values, (Long) nestedTestObj.get("id"));
                if (value != null) {
                    nestedTestObj.put("value", value);
                }
                if (nestedTestObj.containsKey("values")) {
                    fillValues(nestedTestObj, values);
                }
            }
        }
    }

    private static String getValueById(JSONArray values, Long id) {
        for (Object item : values) {
            JSONObject jsonItem = (JSONObject) item;
            if (Objects.equals((Long) jsonItem.get("id"), id)) {
                return jsonItem.get("value").toString();
            }
        }

        return "";
    }
}