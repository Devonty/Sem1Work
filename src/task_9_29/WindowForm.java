package task_9_29;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static task_9_29.Utils.listFromStr;
import static task_9_29.Utils.listToStr;

public class WindowForm extends JFrame {
    private JPanel panel;
    private JLabel list1Label;
    private JLabel list2Label;
    private JLabel listResultLabel;
    private JButton processButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textFieldResult;
    private JLabel errorLabel;
    private JButton secectFileButton;

    public WindowForm() throws HeadlessException {
        setContentPane(panel);
        setSize(400, 600);

        textField1.setText("4 5 2 1 7 5 2 9 6");
        textField2.setText("5 1 8 9 1");

        processButton.addActionListener(e -> process());
        secectFileButton.addActionListener(e -> askFile());
    }



    private void process() {
        List<Integer> list1, list2;
        // 1
        try {
            list1 = listFromStr(textField1.getText());
        } catch (NumberFormatException nfe) {
            errorLabel.setText("Wrong list1 format");
            return;
        }
        // 2
        try {
            list2 = listFromStr(textField2.getText());
        } catch (NumberFormatException nfe) {
            errorLabel.setText("Wrong list2 format");
            return;
        }
        // clear err message
        errorLabel.setText("");
        // result
        Solve.process(list1, list2);
        String result = listToStr(list1);
        textFieldResult.setText(result);

    }

    private void askFile(){
        FileDialog fd = new FileDialog(this, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        // open file
        String path = fd.getDirectory() + fd.getFile();
        List<String> lines;
        try {
            lines = Utils.readFileLines(path);
        } catch (IOException e) {
            errorLabel.setText("File opening error");
            return;
        }

        // clear err message
        errorLabel.setText("File opened successfully");
        // set text
        textField1.setText(lines.size() > 0 ? lines.get(0) : "");
        textField2.setText(lines.size() > 1 ? lines.get(1) : "");
        textFieldResult.setText("");
    }




    public static void main(String[] args) {
        WindowForm form = new WindowForm();
        form.setVisible(true);
    }
}
