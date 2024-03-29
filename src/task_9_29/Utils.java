package task_9_29;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static String listToStr(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : list) {
            stringBuilder.append(integer).append(' ');
        }
        return stringBuilder.toString();
    }

    public static List<Integer> listFromStr(String string) throws NumberFormatException {
        String[] strNums = string.split(" +");
        List<Integer> intNums = new ArrayList<>(strNums.length);

        for (String strNum : strNums)
            intNums.add(Integer.parseInt(strNum));

        return intNums;
    }

    public static List<String> readFileLines(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        List<String> lines = reader.lines().toList();
        reader.close();
        return lines;
    }

    public static void writeToFile(String filepath, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(data);
        writer.close();
    }
}
