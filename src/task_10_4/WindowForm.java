package task_10_4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WindowForm extends JFrame {
    private JPanel panel;
    private JTable tableInput;
    private JButton addRowButton;
    private JButton removeRowButton;
    private JTable tableResult;
    private JButton calcButton;
    private JLabel InputLabel;
    private JLabel ResultLabel;
    private JLabel errorLabel;
    private JButton selectFileButton;

    public WindowForm() throws HeadlessException {
        setContentPane(panel);
        setSize(600, 800);

        removeRowButton.addActionListener(e -> removeRow());
        addRowButton.addActionListener(e -> addRow());
        calcButton.addActionListener(e -> calc());
        selectFileButton.addActionListener(e -> selectFile());

    }

    private void addRow() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableInput.getModel();
        defaultTableModel.addRow(new Object[]{"0", "0"});
    }

    private void calc() {

        DefaultTableModel inputModel = (DefaultTableModel) tableInput.getModel();
        List<Segment> segments = new ArrayList<>(inputModel.getRowCount());

        for (int i = 0; i < inputModel.getRowCount(); i++) {
            float a = Float.parseFloat((String) inputModel.getValueAt(i, 0));
            float b = Float.parseFloat((String) inputModel.getValueAt(i, 1));
            segments.add(new Segment(a, b));
        }

        List<Segment> result = Solve.findMaxPair(segments);
        DefaultTableModel resultModel = (DefaultTableModel) tableResult.getModel();

        try {
            tableResult.setValueAt(result.get(0).start, 0, 0);
            tableResult.setValueAt(result.get(0).end, 0, 1);
            tableResult.setValueAt(result.get(1).start, 1, 0);
            tableResult.setValueAt(result.get(1).end, 1, 1);
            errorLabel.setText("");
        } catch (NullPointerException e){
            errorLabel.setText("Wrong input format");
            return;
        }


    }

    private void removeRow() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableInput.getModel();
        if (defaultTableModel.getRowCount() == 0) return;
        defaultTableModel.removeRow(defaultTableModel.getRowCount() - 1);
    }


    private void selectFile(){
        FileDialog fd = new FileDialog(this, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        // open file
        String path = fd.getDirectory() + fd.getFile();
        List<Segment> segments;
        try {
            segments = Utils.readSegmentsFromFile(path);
        } catch (IOException e) {
            errorLabel.setText("File open error");
            return;
        }

        DefaultTableModel defaultTableModel = (DefaultTableModel) tableInput.getModel();
        defaultTableModel.setRowCount(segments.size());
        for (int i = 0; i < segments.size(); i++) {

            defaultTableModel.setValueAt(Float.toString(segments.get(i).start), i, 0);
            defaultTableModel.setValueAt(Float.toString(segments.get(i).end), i, 1);
        }


    }

    public static void main(String[] args) {
        WindowForm windowForm = new WindowForm();
        windowForm.setVisible(true);
    }

    private void createUIComponents() {
        tableInput = new JTable(new DefaultTableModel(0, 2));
        tableResult = new JTable(new DefaultTableModel(2, 2));
        tableResult.setDefaultEditor(Object.class, null);
    }
}
