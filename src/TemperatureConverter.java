import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    private JButton convertButton;
    private JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        fromUnit = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        toUnit = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        convertButton.addActionListener(new ConvertAction());

        add(new JLabel("Input:"));
        add(inputField);
        add(new JLabel("From:"));
        add(fromUnit);
        add(new JLabel("To:"));
        add(toUnit);
        add(convertButton);
        add(resultLabel);

        setVisible(true);
    }

    private class ConvertAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double inputValue = Double.parseDouble(inputField.getText());
            String from = (String) fromUnit.getSelectedItem();
            String to = (String) toUnit.getSelectedItem();

            double result = convertTemperature(inputValue, from, to);
            resultLabel.setText("Result: " + result);
        }
    }

    private double convertTemperature(double value, String from, String to) {
        double celsius = switch (from) {
            case "Celsius" -> value;
            case "Fahrenheit" -> (value - 32) * 5 / 9;
            case "Kelvin" -> value - 273.15;
            default -> throw new IllegalArgumentException("Invalid temperature unit");
        };

        return switch (to) {
            case "Celsius" -> celsius;
            case "Fahrenheit" -> (celsius * 9 / 5) + 32;
            case "Kelvin" -> celsius + 273.15;
            default -> throw new IllegalArgumentException("Invalid temperature unit");
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverter::new);
    }
}