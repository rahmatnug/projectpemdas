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

        convertButton.setEnabled(false); // Disable button initially

        // Add action listeners
        inputField.addActionListener(e -> checkInput());
        fromUnit.addActionListener(e -> checkInput());
        toUnit.addActionListener(e -> checkInput());
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

    private void checkInput() {
        String inputText = inputField.getText();
        if (!inputText.isEmpty()) {
            double inputValue = Double.parseDouble(inputText);
            String from = (String) fromUnit.getSelectedItem();
            String to = (String) toUnit.getSelectedItem();

            // Enable button only if input is valid and units are different
            convertButton.setEnabled(inputValue != 0 && !from.equals(to));
        } else {
            convertButton.setEnabled(false);
        }
    }

    private class ConvertAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double inputValue = Double.parseDouble(inputField.getText());
            String from = (String) fromUnit.getSelectedItem();
            String to = (String) toUnit.getSelectedItem();

            if (from.equals(to)) {
                JOptionPane.showMessageDialog(null, "Input and output units cannot be the same!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double result = convertTemperature(inputValue, from, to);
            resultLabel.setText("Result: " + result);
            animateConversion();
        }
    }

    private double convertTemperature(double value, String from, String to) {
        // Convert input value to Celsius
        double celsius = switch (from) {
            case "Fahrenheit" -> (value - 32) * 5 / 9;
            case "Kelvin" -> value - 273.15;
            default -> value; // Celsius
        };

        // Convert Celsius to target unit
        return switch (to) {
            case "Fahrenheit" -> (celsius * 9 / 5) + 32;
            case "Kelvin" -> celsius + 273.15;
            default -> celsius; // Celsius
        };
    }

    private void animateConversion() {
        // Simple animation effect (for demonstration purposes)
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resultLabel.setForeground(i % 2 == 0 ? Color.RED : Color.BLACK);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            resultLabel.setForeground(Color.BLACK); // Reset color
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverter::new);
    }
}