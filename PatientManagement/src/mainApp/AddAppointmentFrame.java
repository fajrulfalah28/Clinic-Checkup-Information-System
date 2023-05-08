package mainApp;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.*;

public class AddAppointmentFrame extends JFrame {

    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> dayComboBox;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> timeComboBox;
    private JTextField patientNameField;
    private JPanel doctorPanel;
    private DefaultTableModel tableModel;
private JComboBox doctorComboBox;

    public AddAppointmentFrame(DefaultTableModel model) {
        this.tableModel = model;
        setTitle("Add Appointment");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Font poppinsBold = new Font("Poppins", Font.BOLD, 14);
        Font poppinsPlain = new Font("Poppins", Font.PLAIN, 14);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(poppinsBold);
        panel.add(dateLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.insets = new Insets(10, 5, 10, 5);
        monthComboBox =
                new JComboBox<>(
                        new String[] {
                                "January",
                                "February",
                                "March",
                                "April",
                                "May",
                                "June",
                                "July",
                                "August",
                                "September",
                                "October",
                                "November",
                                "December",
                        }
                );
        monthComboBox.setFont(poppinsPlain);
        panel.add(monthComboBox, c);

        c.gridx = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(10, 5, 10, 5);
        dayComboBox =
                new JComboBox<>(
                        new Integer[] {
                                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
                        }
                );
        dayComboBox.setFont(poppinsPlain);
        panel.add(dayComboBox, c);

        c.gridx = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.insets = new Insets(10, 5, 10, 10);
        yearComboBox =
                new JComboBox<>(new Integer[] {2021, 2022, 2023, 2024});
        yearComboBox.setFont(poppinsPlain);
        panel.add(yearComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(poppinsBold);
        panel.add(timeLabel, c);

        // Time combo box
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.insets = new Insets(10, 5, 10, 10);
        timeComboBox =
                new JComboBox<>(
                        new String[] {
                                "6:00 AM",
                                "7:00 AM",
                                "8:00 AM",
                                "9:00 AM",
                                "10:00 AM",
                                "11:00 AM",
                                "12:00 PM",
                                "1:00 PM",
                                "2:00 PM",
                                "3:00 PM",
                                "4:00 PM",
                                "5:00 PM",
                                "6:00 PM",
                                "7:00 PM",
                                "8:00 PM",
                                "9:00 PM",
                                "10:00 PM",
                        }
                );
        timeComboBox.setFont(poppinsPlain);
        panel.add(timeComboBox, c);

        c.gridx = 0;
        c.gridy = 2;
        JLabel patientNameLabel = new JLabel("Patient Name:");
        patientNameLabel.setFont(poppinsBold);
        panel.add(patientNameLabel, c);

        c.gridx = 1;
        patientNameField = new JTextField(20);
        panel.add(patientNameField, c);

        c.gridx = 0;
        c.gridy = 3;
        JLabel doctorLabel = new JLabel("Doctor:");
        doctorLabel.setFont(poppinsBold);
        panel.add(doctorLabel, c);

        c.gridx = 1;
        doctorPanel = new JPanel();
        doctorPanel.setLayout(new BoxLayout(doctorPanel, BoxLayout.Y_AXIS));
        doctorComboBox =
                new JComboBox<>(AdminMenu.doctors);
        // JRadioButton andiRadioButton = new JRadioButton("Andi");
        // JRadioButton budiRadioButton = new JRadioButton("Budi");
        // JRadioButton rusdiRadioButton = new JRadioButton("Rusdi");
        // ButtonGroup doctorButtonGroup = new ButtonGroup();
        // doctorButtonGroup.add(andiRadioButton);
        // doctorButtonGroup.add(budiRadioButton);
        // doctorButtonGroup.add(rusdiRadioButton);
        // doctorPanel.add(andiRadioButton);
        // doctorPanel.add(budiRadioButton);
        // doctorPanel.add(rusdiRadioButton);
        doctorPanel.add(doctorComboBox);
        panel.add(doctorPanel, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 10, 10, 10);
        JButton saveButton = new JButton("Add");
        saveButton.setFont(poppinsBold);
        panel.add(saveButton, c);

        // Set column names for table model
        String[] columnNames = AdminMenu.dataColumnName;

        add(panel);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int month =  monthComboBox.getSelectedIndex() + 1;
                int day = (int) dayComboBox.getSelectedItem();
                int year = (int) yearComboBox.getSelectedItem();
                String time = (String) timeComboBox.getSelectedItem();
                String patientName = patientNameField.getText();
                String date = day + "-" + month + "-" + year + " " + time;
                ;
                int id = (int)AdminMenu.patient[0][0];
                for(int i = 0; i < AdminMenu.patient.length; i++){
                        if (((String) AdminMenu.patient[i][3]).equalsIgnoreCase(patientName.split(" ")[0]) && ((String) AdminMenu.patient[i][4]).equalsIgnoreCase(patientName.split(" ")[1])){
                                id = (int) AdminMenu.patient[i][0];
                        }
                }
                String doctorName = AdminMenu.doctors[doctorComboBox.getSelectedIndex()];
                try {
                        servController.addAppointment(id, AdminMenu.servDoctorID[doctorComboBox.getSelectedIndex()], date);
                        JOptionPane.showMessageDialog(
                        panel,
                    "Successfully Add Appointment",
                    "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }
                // if (andiRadioButton.isSelected()) {
                //     doctorName = "Andi";
                // } else if (budiRadioButton.isSelected()) {
                //     doctorName = "Budi";
                // } else if (rusdiRadioButton.isSelected()) {
                //     doctorName = "Rusdi";
                // }

                // Add the appointment data to the table model
                // Object[] rowData = {month + " " + day + ", " + year, time, patientName, doctorName};
                // tableModel.addRow(rowData);

                // Clear the form fields
                monthComboBox.setSelectedIndex(0);
                dayComboBox.setSelectedIndex(0);
                yearComboBox.setSelectedIndex(0);
                timeComboBox.setSelectedIndex(0);
                patientNameField.setText("");            }
        });
    }
}