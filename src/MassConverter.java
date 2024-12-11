import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MassConverter {
    private JFrame frame;
    private JTextField inputTextField;
    private JComboBox<String> fromUnitComboBox;
    private JComboBox<String> toUnitComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    private String[] massUnits = {"kg", "hg", "dg", "g", "dag", "cg", "mg", "ton"};
    private double[] conversionFactors = {
        1000.0,    // kg
        100.0,     // hg
        10.0,      // dg
        1.0,       // g
        0.1,       // dag
        0.01,      // cg
        0.001,     // mg
        1000000.0  // ton
    };

    public MassConverter() {
        frame = new JFrame("Konversi Massa");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        inputTextField = new JTextField(10);
        fromUnitComboBox = new JComboBox<>(massUnits);
        toUnitComboBox = new JComboBox<>(massUnits);
        convertButton = new JButton("Konversi");
        resultLabel = new JLabel("Hasil: ");

        JLabel inputLabel = new JLabel("Masukkan Nilai:");
        JLabel fromLabel = new JLabel("Dari Satuan:");
        JLabel toLabel = new JLabel("Ke Satuan:");

        frame.add(inputLabel);
        frame.add(inputTextField);
        frame.add(fromLabel);
        frame.add(fromUnitComboBox);
        frame.add(toLabel);
        frame.add(toUnitComboBox);
        frame.add(convertButton);
        frame.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        frame.setSize(480, 330);
        frame.setVisible(true);
    }

    private void performConversion() {
        try {
            double inputValue = Double.parseDouble(inputTextField.getText());
            int fromIndex = fromUnitComboBox.getSelectedIndex();
            int toIndex = toUnitComboBox.getSelectedIndex();
            double result = inputValue * (conversionFactors[fromIndex] / conversionFactors[toIndex]);
            resultLabel.setText("Hasil: " + result + " " + massUnits[toIndex]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Masukkan nilai yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MassConverter::new);
    }
}