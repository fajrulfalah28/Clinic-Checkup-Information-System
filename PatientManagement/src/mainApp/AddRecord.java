package mainApp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddRecord {
    private JTable table;

    public AddRecord(JTable table) {
        this.table = table;
    }

    public void AddRecord() {
        JFrame frame = new JFrame("Add Record");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(235, 216, 200));
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;
        Font boldFont = new Font("Poppins", Font.BOLD, 14);
        Font plainFont = new Font("Poppins", Font.PLAIN, 14);

        JLabel nameLabel = new JLabel("Patient Name:");
        nameLabel.setFont(boldFont);
        c.gridx = 0;
        c.gridy = 0;
        frame.getContentPane().add(nameLabel, c);

        JTextField nameField = new JTextField(20);
        nameField.setFont(plainFont);
        c.gridx = 1;
        c.gridy = 0;
        frame.getContentPane().add(nameField, c);

        JLabel systolicLabel = new JLabel("Systolic:");
        systolicLabel.setFont(boldFont);
        c.gridx = 0;
        c.gridy = 1;
        frame.getContentPane().add(systolicLabel, c);

        JTextField systolicField = new JTextField(20);
        systolicField.setFont(plainFont);
        c.gridx = 1;
        c.gridy = 1;
        frame.getContentPane().add(systolicField, c);

        JLabel diastolicLabel = new JLabel("Diastolic:");
        diastolicLabel.setFont(boldFont);
        c.gridx = 0;
        c.gridy = 2;
        frame.getContentPane().add(diastolicLabel, c);

        JTextField diastolicField = new JTextField(20);
        diastolicField.setFont(plainFont);
        c.gridx = 1;
        c.gridy = 2;
        frame.getContentPane().add(diastolicField, c);

        JLabel heartRateLabel = new JLabel("Heart Rate:");
        heartRateLabel.setFont(boldFont);
        c.gridx = 0;
        c.gridy = 3;
        frame.getContentPane().add(heartRateLabel, c);

        JTextField heartRateField = new JTextField(20);
        heartRateField.setFont(plainFont);
        c.gridx = 1;
        c.gridy = 3;
        frame.getContentPane().add(heartRateField, c);

        JLabel tempLabel = new JLabel("Body Temperature:");
        tempLabel.setFont(boldFont);
        c.gridx = 0;
        c.gridy = 4;
        frame.getContentPane().add(tempLabel, c);

        JTextField tempField = new JTextField(20);
        tempField.setFont(plainFont);
        c.gridx = 1;
        c.gridy = 4;
        frame.getContentPane().add(tempField, c);

        JLabel heightLabel = new JLabel("Body Height:");
        heightLabel.setFont(boldFont);
        c.gridx = 0;
        c.gridy = 5;
        frame.getContentPane().add(heightLabel, c);

        JTextField heightField = new JTextField(20);
        heightField.setFont(plainFont);
        c.gridx = 1;
        c.gridy = 5;
        frame.getContentPane().add(heightField, c);

        JLabel weightLabel = new JLabel("Body Weight:");
        weightLabel.setFont(boldFont);
        c.gridx = 0;
        c.gridy = 6;
        frame.getContentPane().add(weightLabel, c);

        JTextField weightField = new JTextField(20);
        weightField.setFont(plainFont);
        c.gridx = 1;
        c.gridy = 6;
        frame.getContentPane().add(weightField, c);

        JLabel notesLabel = new JLabel("Doctor Notes:");
        notesLabel.setFont(boldFont);
        c.gridx = 0;
        c.gridy = 7;
        frame.getContentPane().add(notesLabel, c);

        JTextArea notesArea = new JTextArea(5, 20);
        notesArea.setFont(plainFont);
        JScrollPane notesScrollPane = new JScrollPane(notesArea);
        c.gridx = 1;
        c.gridy = 7;
        frame.getContentPane().add(notesScrollPane, c);

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Poppins", Font.BOLD, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                int systolic = Integer.parseInt(systolicField.getText());
                int diastolic = Integer.parseInt(diastolicField.getText());
                int heartRate = Integer.parseInt(heartRateField.getText());
                double temp = Double.parseDouble(tempField.getText());
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());
                String notes = notesArea.getText();

                Object[] record = {name, systolic, diastolic, heartRate, temp, height, weight, notes};

                DefaultTableModel model = (DefaultTableModel) table.getModel();

                model.addRow(record);

                frame.dispose();
            }
        });
        frame.getContentPane().add(addButton, c);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}