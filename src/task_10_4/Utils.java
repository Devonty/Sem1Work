package task_10_4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static String strFromList(List<Segment> segments){
        StringBuilder stringBuilder = new StringBuilder();
        for(Segment segment: segments){
            stringBuilder.append(segment).append('\n');
        }
        return stringBuilder.toString();
    }

    public static void writeToFile(String filepath, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(data);
        writer.close();
    }

    public static List<Segment> readSegmentsFromFile(String filepath) throws IOException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);

        List<Segment> segments = new ArrayList<>();
        while (scanner.hasNextInt()) {
            float a = scanner.nextFloat();
            float b = scanner.nextFloat();
            segments.add(new Segment(a, b));
        }

        return segments;
    }
}
