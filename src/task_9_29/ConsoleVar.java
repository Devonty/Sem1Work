package task_9_29;

import java.io.IOException;
import java.util.List;

import static task_9_29.Utils.listToStr;

public class ConsoleVar {
    public static final String inputFlag = "--input-file=";
    public static final String outputFlag = "--output-file=";

    public static InputArgs parseCmdArgs(String[] args) {
        String inputFile = null, outputFile = null;
        for (String arg : args) {
            if (arg.startsWith(inputFlag))
                inputFile = arg.substring(inputFlag.length());
            else if (arg.startsWith(outputFlag))
                outputFile = arg.substring(outputFlag.length());

        }

        if (inputFile == null || outputFile == null) {
            System.err.println("Input or output arg is missing");
            System.exit(1);
            return null;
        }
        return new InputArgs(inputFile, outputFile);

    }

    public static void main(String[] args) {
        InputArgs inputArgs = parseCmdArgs(args);
        // open
        List<String> lines;
        try {
            lines = Utils.readFileLines(inputArgs.inputFile());
        } catch (IOException e) {
            System.err.println("File not found:\n" + e.getMessage());
            System.exit(404);
            return;
        }
        // read
        List<Integer> list1 = Utils.listFromStr(lines.size() > 0 ? lines.get(0) : "");
        List<Integer> list2 = Utils.listFromStr(lines.size() > 1 ? lines.get(1) : "");
        // work
        Solve.process(list1, list2);
        // save
        String result = listToStr(list1);
        try {
            Utils.writeToFile(inputArgs.outputFile(), result);
        } catch (IOException e) {
            System.err.println("Write to file error:\n" + e.getMessage());
            System.exit(405);
            return;
        }
    }
}
