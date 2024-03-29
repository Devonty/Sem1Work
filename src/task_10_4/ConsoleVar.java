package task_10_4;

import java.io.IOException;
import java.util.List;

import static task_10_4.Utils.strFromList;

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
        List<Segment> segments;
        try {
            segments = Utils.readSegmentsFromFile(inputArgs.inputFile());
        } catch (IOException e) {
            System.err.println("File not found:\n" + e.getMessage());
            System.exit(404);
            return;
        }
        // work
        List<Segment> result = Solve.findMaxPair(segments);
        // save
        String toSave = strFromList(result);
        try {
            Utils.writeToFile(inputArgs.outputFile(), toSave);
        } catch (IOException e) {
            System.err.println("Write to file error:\n" + e.getMessage());
            System.exit(405);
            return;
        }
    }
}
