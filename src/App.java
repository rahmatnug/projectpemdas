import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplikasi Konverter");
        JButton lengthButton = new JButton("Konversi Panjang");
        JButton massButton = new JButton("Konversi Massa");
        JButton timeButton = new JButton("Konversi Waktu");
        JButton temperatureButton = new JButton("Konversi Suhu");
        JButton bmiButton = new JButton("Hitung BMI");
        JButton volumeButton = new JButton("Konversi Volume");
        JButton frequencyButton = new JButton("Konversi Frekuensi");
        JButton numberBaseButton = new JButton("Konversi Angka");

        lengthButton.setBounds(50, 50, 200, 30);
        massButton.setBounds(50, 100, 200, 30);
        timeButton.setBounds(50, 150, 200, 30);
        temperatureButton.setBounds(50, 200, 200, 30);
        bmiButton.setBounds(50, 250, 200, 30);
        volumeButton.setBounds(50, 300, 200, 30);
        frequencyButton.setBounds(50, 350, 200, 30);
        numberBaseButton.setBounds(50, 400, 200, 30);

        frame.add(lengthButton);
        frame.add(massButton);
        frame.add(timeButton);
        frame.add(temperatureButton);
        frame.add(bmiButton);
        frame.add(volumeButton);
        frame.add(frequencyButton);
        frame.add(numberBaseButton);

        lengthButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LengthConverter();
            }
        });

        massButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MassConverter(); 
            }
        });

        timeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TimeConverter();
            }
        });

        temperatureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TemperatureConverter();
            }
        });

        bmiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BmiConverter();
            }
        });

        volumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VolumeConverter();
            }
        });

        frequencyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FrequencyConverter();
            }
        });

        numberBaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NumberBaseConverter();
            }
        });

        frame.setSize(1440, 1024);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}