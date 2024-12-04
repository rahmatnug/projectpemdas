import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeConverter extends JFrame {
    private JTextField inputField;
    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    private JButton convertButton;
    private JLabel resultLabel;

    public TimeConverter() {
        setTitle("Time Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        fromUnit = new JComboBox<>(new String[]{"Seconds", "Minutes", "Hours", "Days", "Months"});
        toUnit = new JComboBox<>(new String[]{"Seconds", "Minutes", "Hours", "Days", "Months"});
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

            double result = convertTime(inputValue, from, to);
            resultLabel.setText("Result: " + result);
            animateConversion();
        }
    }

    private double convertTime(double value, String from, String to) {
        // Konversi input value ke detik
        double seconds = switch (from) {
            case "Seconds" -> value;
            case "Minutes" -> value * 60;
            case "Hours" -> value * 3600;
            case "Days" -> value * 86400;
            case "Months" -> value * (365.0 / 12.0) * 86400; // 1 bulan = 30.4167 hari
            default -> throw new IllegalArgumentException("Invalid time unit");
        };
    
        // Konversi detik ke satuan target
        return switch (to) {
            case "Seconds" -> seconds;
            case "Minutes" -> seconds / 60;
            case "Hours" -> seconds / 3600;
            case "Days" -> seconds / 86400;
            case "Months" -> seconds / (30.4167 * 86400); // Rata-rata detik dalam sebulan
            default -> throw new IllegalArgumentException("Invalid time unit");
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
        SwingUtilities.invokeLater(TimeConverter::new);
    }
}