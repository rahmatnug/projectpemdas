import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LengthConverter {
    private JFrame frame;
    private JTextField inputField;
    private JComboBox<String> inputUnitComboBox;
    private JComboBox<String> outputUnitComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    public LengthConverter() {
        frame = new JFrame("Konversi Panjang");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        inputUnitComboBox = new JComboBox<>(new String[]{"km", "hm", "dm", "m", "dam", "cm", "zm"});
        outputUnitComboBox = new JComboBox<>(new String[]{"km", "hm", "dm", "m", "dam", "cm", "zm"});
        convertButton = new JButton("Konversi");
        resultLabel = new JLabel("Hasil: ");

        // Create labels for "Dari" and "Ke"
        JLabel fromLabel = new JLabel("Dari:");
        JLabel toLabel = new JLabel("Ke:");

        // Add components to the frame
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

                // Convert input value to meters
                inputValue = convertToMeters(inputUnit, inputValue);

                // Convert meters to output value
                outputValue = convertFromMeters(outputUnit, inputValue);

                // Update the result label
                resultLabel.setText("Hasil: " + outputValue + " " + outputUnit);
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setSize(320, 330);
    }

    private double convertToMeters(String unit, double value) {
        switch (unit.toLowerCase()) {
            case "km": return value * 1000;
            case "hm": return value * 100;
            case "dm": return value * 0.1;
            case "dam": return value * 10;
            case "cm": return value * 0.01;
            case "zm": return value * 0.001;
            default: return value;
        }
    }

    private double convertFromMeters(String unit, double value) {
        switch (unit.toLowerCase()) {
            case "km": return value / 1000;
            case "hm": return value / 100;
            case "dm": return value / 0.1;
            case "dam": return value / 10;
            case "cm": return value / 0.01;
            case "zm": return value / 0.001;
            default: return value;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LengthConverter();
            }
        });
    }
}