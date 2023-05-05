package mainApp;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

class AddPatientFrame extends JFrame {

  private JTextField patientNameField;
  private JTextField nurseNameField;
  private JTextField systolicField;
  private JTextField diastolicField;
  private JTextField heartRateField;
  private JTextField bodyTempField;
  private JTextField bodyHeightField;
  private JPanel doctorPanel;
  private int row;
  private JTable table;
  private DefaultTableModel tableModel;

  public AddPatientFrame(DefaultTableModel model) {
    this.tableModel = model;
    setTitle("Add Patient");
    setSize(800, 500);
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
    
    JLabel patientNameLabel = new JLabel("Patient Name:");
    patientNameLabel.setFont(poppinsBold);
    panel.add(patientNameLabel, c);

    c.gridx = 1;
    patientNameField = new JTextField(20);
    panel.add(patientNameField, c);
    

    c.gridx = 0;
    c.gridy = 2;
    JLabel nurseNameLabel = new JLabel("Nurse Name:");
    nurseNameLabel.setFont(poppinsBold);
    panel.add(nurseNameLabel, c);

    c.gridx = 1;
    nurseNameField = new JTextField(20);
    panel.add(nurseNameField, c);

    c.gridx = 0;
    c.gridy = 3;
    JLabel systolicLabel = new JLabel("Systolic :");
    systolicLabel.setFont(poppinsBold);
    panel.add(systolicLabel, c);

    c.gridx = 1;
    systolicField = new JTextField(20);
    panel.add(systolicField, c);

    c.gridx = 0;
    c.gridy = 4;
    JLabel diastolicLabel = new JLabel("Diastolic :");
    diastolicLabel.setFont(poppinsBold);
    panel.add(diastolicLabel, c);

    c.gridx = 1;
    diastolicField = new JTextField(20);
    panel.add(diastolicField, c);

    c.gridx = 0;
    c.gridy = 5;
    JLabel heartRateLabel = new JLabel("Heart Rate :");
    heartRateLabel.setFont(poppinsBold);
    panel.add(heartRateLabel, c);

    c.gridx = 1;
    heartRateField = new JTextField(20);
    panel.add(heartRateField, c);

    c.gridx = 0;
    c.gridy = 6;
    JLabel bodyTempLabel = new JLabel("Body Temperature :");
    bodyTempLabel.setFont(poppinsBold);
    panel.add(bodyTempLabel, c);

    c.gridx = 1;
    bodyTempField = new JTextField(20);
    panel.add(bodyTempField, c);

    c.gridx = 0;
    c.gridy = 7;
    JLabel bodyHeightLabel = new JLabel("Body Height :");
    bodyHeightLabel.setFont(poppinsBold);
    panel.add(bodyHeightLabel, c);

    c.gridx = 1;
    bodyHeightField = new JTextField(20);
    panel.add(bodyHeightField, c);

    c.gridx = 0;
    c.gridy = 8;
    JLabel doctorLabel = new JLabel("Doctor:");
    doctorLabel.setFont(poppinsBold);
    panel.add(doctorLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 5, 10, 10);
    String[] doctorOptions = new String[] {"Andi", "Budi", "Rusdi"};
    JComboBox<String> doctorComboBox = new JComboBox<>(doctorOptions);
    doctorComboBox.setFont(poppinsPlain);
    panel.add(doctorComboBox, c);

    c.gridx = 0;
    c.gridy = 9;
    c.gridwidth = 4;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(10, 10, 10, 10);

    JButton saveButton = new JButton("Add");
    saveButton.setFont(poppinsBold);
    saveButton.setBackground(Color.BLACK);
    saveButton.setForeground(Color.WHITE);
    panel.add(saveButton, c);

    String[] columnNames = {"MRID",
    "Patient",
    "Nurse",
    "Systolic",
    "Diastolic",
    "Heart Rate",
    "Body Temperature",
    "Body Height",
    "Diagnosis ID",
    "Doctor",
    "Diagnosis Result",
    "Action Status"};
    tableModel.setColumnIdentifiers(columnNames);

    add(panel);

    saveButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String newPatientName = patientNameField.getText();
          String newNurseName = nurseNameField.getText();
          String newSystolic = systolicField.getText();
          String newDiastolic = diastolicField.getText();
          String newHeartRate = heartRateField.getText();
          String newBodyTemp = bodyTempField.getText();
          String newBodyHeight = bodyHeightField.getText();
          String newDoctor = doctorComboBox.getSelectedItem().toString();

          Object[] rowData = {null, newPatientName, newNurseName, newSystolic, newDiastolic, newHeartRate, newBodyTemp, newBodyHeight, null, newDoctor, null, null};
                tableModel.addRow(rowData);

                patientNameField.setText("");
                nurseNameField.setText("");
                systolicField.setText("");
                diastolicField.setText("");
                heartRateField.setText("");
                bodyTempField.setText("");
                bodyHeightField.setText("");
        }
      }
    );
  }
}
