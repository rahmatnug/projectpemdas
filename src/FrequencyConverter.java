import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrequencyConverter {
    private JFrame frame;
    private JTextField inputField;
    private JComboBox<String> inputUnitComboBox;
    private JComboBox<String> outputUnitComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    public FrequencyConverter() {
        frame = new JFrame("Konversi Frekuensi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        inputUnitComboBox = new JComboBox<>(new String[]{"Hertz", "Kilohertz", "Megahertz"});
        outputUnitComboBox = new JComboBox<>(new String[]{"Hertz", "Kilohertz", "Megahertz"});
        convertButton = new JButton("Konversi");
        resultLabel = new JLabel("Hasil: ");


        JLabel fromLabel = new JLabel("Dari:");
        JLabel toLabel = new JLabel("Ke:");


        frame.add(inputField);
        frame.add(fromLabel);
        frame.add(inputUnitComboBox);
        frame.add(toLabel);
        frame.add(outputUnitComboBox);
        frame.add(convertButton);
        frame.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputUnit = (String) inputUnitComboBox.getSelectedItem();
                double inputValue = Double.parseDouble(inputField.getText());
                String outputUnit = (String) outputUnitComboBox.getSelectedItem();
                double outputValue;


                inputValue = convertToHertz(inputUnit, inputValue);


                outputValue = convertFromHertz(outputUnit, inputValue);


                resultLabel.setText("Hasil: " + outputValue + " " + outputUnit);
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setSize(320, 330);
    }

    private double convertToHertz(String unit, double value) {
        switch (unit.toLowerCase()) {
            case "kilohertz": return value * 1000;
            case "megahertz": return value * 1000000;
            default: return value; // Hertz
        }
    }

    private double convertFromHertz(String unit, double value) {
        switch (unit.toLowerCase()) {
            case "kilohertz": return value / 1000;
            case "megahertz": return value / 1000000;
            default: return value; // Hertz
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FrequencyConverter();
            }
        });
    }
}
