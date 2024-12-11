import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DataConverter {
    private JFrame frame;
    private JTextField inputField;
    private JTextField outputField;
    private JButton convertButton;

    public DataConverter() {
        frame = new JFrame("Konversi Data");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        outputField = new JTextField(10);
        outputField.setEditable(false);
        convertButton = new JButton("Konversi");


        JLabel inputLabel = new JLabel("Masukkan Data (byte):");
        JLabel outputLabel = new JLabel("Hasil dalam Kilobyte:");


        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(convertButton);
        frame.add(outputLabel);
        frame.add(outputField);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double bytes = Double.parseDouble(inputField.getText());
                double kilobytes = bytes / 1024;
                outputField.setText(String.valueOf(kilobytes));
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setSize(320, 200);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DataConverter();
            }
        });
    }
}