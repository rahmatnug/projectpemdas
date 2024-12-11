import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class BmiConverter {
    public BmiConverter() {
        JFrame frame = new JFrame("Hitung BMI");
        JTextField weightField = new JTextField();
        JTextField heightField = new JTextField();
        JButton calculateButton = new JButton("Hitung BMI");
        JLabel resultLabel = new JLabel("Hasil: ");
        

        JLabel weightLabel = new JLabel("Berat (kg):");
        weightLabel.setBounds(50, 20, 150, 30);
        

        JLabel heightLabel = new JLabel("Tinggi (m):");
        heightLabel.setBounds(50, 70, 150, 30);

        weightField.setBounds(50, 50, 150, 30);
        heightField.setBounds(50, 100, 150, 30);
        calculateButton.setBounds(50, 150, 150, 30);
        resultLabel.setBounds(50, 200, 250, 30);

        frame.add(weightLabel);
        frame.add(weightField);
        frame.add(heightLabel);
        frame.add(heightField);
        frame.add(calculateButton);
        frame.add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double weight = Double.parseDouble(weightField.getText());
                    double height = Double.parseDouble(heightField.getText());
                    
                    // Validasi tinggi badan
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

        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BmiConverter();
    }
}