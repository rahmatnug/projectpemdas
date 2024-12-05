import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MassConverter extends JFrame {
    private JTextField inputTextField;
    private JComboBox<String> fromUnitComboBox;
    private JComboBox<String> toUnitComboBox;
    private JTextField resultTextField;
    private JButton convertButton;
    private ValueAnimator animationTimer;

    // Satuan massa yang tersedia
    private String[] massUnits = {"kg", "hg", "dg", "g", "dag", "cg", "mg", "ton"};

    // Faktor konversi ke gram (satuan dasar)
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

    // Kelas khusus untuk animasi nilai
    private class ValueAnimator {
        private Timer timer;
        private double currentValue = 0;
        private double targetValue = 0;
        private int stepCount = 0;

        public ValueAnimator() {
            timer = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (stepCount < 20) {
                        // Interpolasi linier untuk animasi
                        currentValue += (targetValue - currentValue) * 0.2;
                        resultTextField.setText(String.format("%.4f", currentValue));
                        stepCount++;
                    } else {
                        // Hentikan animasi
                        timer.stop();
                        resultTextField.setText(String.format("%.4f", targetValue));
                    }
                }
            });
        }

        public void startAnimation(double target) {
            currentValue = 0;
            targetValue = target;
            stepCount = 0;
            timer.start();
        }
    }

    public MassConverter() {
        // Pengaturan dasar frame
        setTitle("Konversi Berat");
        setSize(480, 330);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Komponen input
        inputTextField = new JTextField(10);
        fromUnitComboBox = new JComboBox<>(massUnits);
        toUnitComboBox = new JComboBox<>(massUnits);
        convertButton = new JButton("Konversi");
        resultTextField = new JTextField(10);
        resultTextField.setEditable(false);

        // Menambahkan komponen ke frame
        add(new JLabel("Masukkan Nilai:"));
        add(inputTextField);
        add(new JLabel("Dari Satuan:"));
        add(fromUnitComboBox);
        add(new JLabel("Ke Satuan:"));
        add(toUnitComboBox);
        add(convertButton);
        add(new JLabel("Hasil Konversi:"));
        add(resultTextField);


        setVisible(true);
            

        // Inisialisasi animator
        animationTimer = new ValueAnimator();

        // Mengubah font untuk semua komponen
        Font font = new Font("Arial", Font.PLAIN, 14);
        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JLabel || comp instanceof JTextField || comp instanceof JButton || comp instanceof JComboBox) {
                comp.setFont(font);
            }
        }

        // Menambahkan event listener
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        // Listener untuk mengecek kondisi konversi
        fromUnitComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateConvertButtonState();
            }
        });

        toUnitComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateConvertButtonState();
            }
        });

        // Update status tombol konversi awal
        updateConvertButtonState();
    }

    // Metode untuk mengecek dan mengupdate status tombol konversi
    private void updateConvertButtonState() {
        convertButton.setEnabled(!inputTextField.getText().isEmpty());
    }

    // Metode untuk melakukan konversi
    private void performConversion() {
        try {
            double inputValue = Double.parseDouble(inputTextField.getText());
 int fromIndex = fromUnitComboBox.getSelectedIndex();
            int toIndex = toUnitComboBox.getSelectedIndex();
            double result = inputValue * (conversionFactors[fromIndex] / conversionFactors[toIndex]);
            animationTimer.startAnimation(result);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan nilai yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MassConverter::new);
    }
}