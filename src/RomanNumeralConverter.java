import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RomanNumeralConverter {
    private JFrame frame;
    private JTextField inputField;
    private JTextField outputField;
    private JButton convertButton;

    public RomanNumeralConverter() {
        frame = new JFrame("Konversi Angka Romawi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        outputField = new JTextField(10);
        outputField.setEditable(false);
        convertButton = new JButton("Konversi");


        JLabel inputLabel = new JLabel("Masukkan Angka:");
        JLabel outputLabel = new JLabel("Hasil Angka Romawi:");


        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(convertButton);
        frame.add(outputLabel);
        frame.add(outputField);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(inputField.getText());
                String romanNumeral = convertToRoman(number);
                outputField.setText(romanNumeral);
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setSize(320, 200);
    }

    private String convertToRoman(int number) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[number / 1000] +
               hundreds[(number % 1000) / 100] +
               tens[(number % 100) / 10] +
               units[number % 10];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RomanNumeralConverter();
            }
        });
    }
}