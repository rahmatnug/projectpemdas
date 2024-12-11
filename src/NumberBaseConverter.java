import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberBaseConverter {
    private JFrame frame;
    private JTextField inputField;
    private JComboBox<String> outputBaseComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    public NumberBaseConverter() {
        frame = new JFrame("Konversi Angka");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        outputBaseComboBox = new JComboBox<>(new String[]{"Biner", "Oktal", "Heksadesimal"});
        convertButton = new JButton("Konversi");
        resultLabel = new JLabel("Hasil: ");

        // Create labels
        JLabel inputLabel = new JLabel("Masukkan Angka Desimal:");
        JLabel outputLabel = new JLabel("Ke:");

        // Add components to the frame
        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(outputLabel);
        frame.add(outputBaseComboBox);
        frame.add(convertButton);
        frame.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int decimalValue = Integer.parseInt(inputField.getText());
                    String selectedBase = (String) outputBaseComboBox.getSelectedItem();
                    String convertedValue = convertNumber(decimalValue, selectedBase);
                    resultLabel.setText("Hasil: " + convertedValue);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Masukkan angka desimal yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setSize(320, 200);
    }

    private String convertNumber(int decimalValue, String base) {
        switch (base.toLowerCase()) {
            case "biner":
                return Integer.toBinaryString(decimalValue);
            case "oktal":
                return Integer.toOctalString(decimalValue);
            case "heksadesimal":
                return Integer.toHexString(decimalValue).toUpperCase();
            default:
                return String.valueOf(decimalValue); // Default to decimal
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberBaseConverter();
            }
        });
    }
}
