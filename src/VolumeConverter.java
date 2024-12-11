import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VolumeConverter {
    private JFrame frame;
    private JTextField inputField;
    private JComboBox<String> inputUnitComboBox;
    private JComboBox<String> outputUnitComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    public VolumeConverter() {
        frame = new JFrame("Konversi Volume");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        inputUnitComboBox = new JComboBox<>(new String[]{"Liter", "Milliliter", "Cubic Meter", "Gallon"});
        outputUnitComboBox = new JComboBox<>(new String[]{"Liter", "Milliliter", "Cubic Meter", "Gallon"});
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


                inputValue = convertToLiters(inputUnit, inputValue);

   
                outputValue = convertFromLiters(outputUnit, inputValue);


                resultLabel.setText("Hasil: " + outputValue + " " + outputUnit);
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setSize(320, 330);
    }

    private double convertToLiters(String unit, double value) {
        switch (unit.toLowerCase()) {
            case "milliliter": return value / 1000;
            case "cubic meter": return value * 1000;
            case "gallon": return value * 3.78541;
            default: return value; 
        }
    }

    private double convertFromLiters(String unit, double value) {
        switch (unit.toLowerCase()) {
            case "milliliter": return value * 1000;
            case "cubic meter": return value / 1000;
            case "gallon": return value / 3.78541;
            default: return value; 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VolumeConverter();
            }
        });
    }
}