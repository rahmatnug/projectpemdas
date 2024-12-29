import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeConverter {
    private JFrame frame;
    private JTextField inputField;
    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    private JButton convertButton;
    private JLabel resultLabel;

    public TimeConverter() {
        frame = new JFrame("Konversi Waktu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        fromUnit = new JComboBox<>(new String[]{"Detik", "Menit", "Jam", "Hari", "Bulan", "Tahun"});
        toUnit = new JComboBox<>(new String[]{"Detik", "Menit", "Jam", "Hari", "Bulan", "Tahun"});
        convertButton = new JButton("Konversi");
        resultLabel = new JLabel("Hasil: ");

        convertButton.addActionListener(new ConvertAction());

        frame.add(new JLabel("Input:"));
        frame.add(inputField);
        frame.add(new JLabel("Dari:"));
        frame.add(fromUnit);
        frame.add(new JLabel("Ke:"));
        frame.add(toUnit);
        frame.add(convertButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    private class ConvertAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double inputValue = Double.parseDouble(inputField.getText());
            String from = (String) fromUnit.getSelectedItem();
            String to = (String) toUnit.getSelectedItem();

            double result = convertTime(inputValue, from, to);
            resultLabel.setText("Hasil: " + result);
        }
    }

    private double convertTime(double value, String from, String to) {
        double seconds = switch (from) {
            case "Detik" -> value;
            case "Menit" -> value * 60;
            case "Jam" -> value * 3600;
            case "Hari" -> value * 86400;
            case "Bulan" -> value * (30.4167 * 86400);
            case "Tahun" -> value * (365.25 * 86400);
            default -> throw new IllegalArgumentException("Invalid time unit");
        };

        return switch (to) {
            case "Detik" -> seconds;
            case "Menit" -> seconds / 60;
            case "Jam" -> seconds / 3600;
            case "Hari" -> seconds / 86400;
            case "Bulan" -> seconds / (30.4167 * 86400);
            case "Tahun" -> seconds / (365.25 * 86400);
            default -> throw new IllegalArgumentException("Invalid time unit");
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TimeConverter::new);
    }
}
