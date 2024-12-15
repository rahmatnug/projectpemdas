import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class BmiConverter {
    public BmiConverter() {
        JFrame frame = new JFrame("Hitung BMI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)) ;

        JTextField weightField = new JTextField(10);
        JTextField heightField = new JTextField(10);
        JButton calculateButton = new JButton("Hitung BMI");
        JLabel resultLabel = new JLabel("Hasil: ");

        frame.add(new JLabel("Berat (kg):"));
        frame.add(weightField);
        frame.add(new JLabel("Tinggi (cm):"));
        frame.add(heightField);
        frame.add(calculateButton);
        frame.add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double weight = Double.parseDouble(weightField.getText());
                    double height = Double.parseDouble(heightField.getText()) / 100;

                    if (height <= 0) {
                        resultLabel.setText("Tinggi badan tidak valid.");
                        return;
                    }

                    double bmi = weight / (height * height); // Rumus BMI

                    String category;
                    if (bmi < 18.5) {
                        category = "Berat badan kurang.";
                    } else if (bmi >= 18.5 && bmi <= 22.9) {
                        category = "Berat badan normal.";
                    } else if (bmi >= 23 && bmi <= 29.9) {
                        category = "Berat badan berlebih (kecenderungan obesitas).";
                    } else {
                        category = "Obesitas.";
                    }

                    DecimalFormat df = new DecimalFormat("#.##");
                    resultLabel.setText("Hasil: " + df.format(bmi) + " - " + category);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Input tidak valid.");
                }
            }
        });

        frame.setSize(410, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BmiConverter();
    }
}