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
        fromUnit = new JComboBox<>(new String[]{"Detik", "Menit", "Jam", "Hari", "Bulan"});
        toUnit = new JComboBox<>(new String[]{"Detik", "Menit", "Jam", "Hari", "Bulan"});
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

            double result = convertTime(inputValue, from, to);
            resultLabel.setText("Result: " + result);
        }
    }

    private double convertTime(double value, String from, String to) {
        double days = switch (from) {
            case "Detik" -> value / 86400; 
            case "Menit" -> value / 1440; 
            case "Jam" -> value / 24; 
            case "Hari" -> value; 
            case "Bulan" -> value * 30; 
            default -> throw new IllegalArgumentException("Unit waktu tidak valid");
        };
    
        return switch (to) {
            case "Detik" -> days * 86400; 
            case "Menit" -> days * 1440; 
            case "Jam" -> days * 24; 
            case "Hari" -> days; 
            case "Bulan" -> days / 30; 
            default -> throw new IllegalArgumentException("Unit waktu tidak valid");
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TimeConverter::new);
    }
}