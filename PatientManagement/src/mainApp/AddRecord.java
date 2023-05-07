package mainApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class AddRecord extends JFrame {

  public static int[] patientID;
  public static String[] listpatient;
  private JLabel patientNameLabel;
  private JLabel currentDateLabel;
  private JLabel diagnosisResultLabel;
  private JTextArea diagnosisResultTextArea;
  private JRadioButton referralRadioButton;
  private JRadioButton prescriptionRadioButton;
  private ButtonGroup actionStatusButtonGroup;

  public AddRecord(String patientName) {
    super("Add Record");
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setSize(400, 400);
    setLocationRelativeTo(null);
    setResizable(false);

    patientNameLabel = new JLabel("Patient Name: " + patientName);
    patientNameLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
    patientNameLabel.setForeground(Color.BLACK);

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    String currentDate = now.format(formatter);
    currentDateLabel = new JLabel("Date: " + currentDate);
    currentDateLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
    currentDateLabel.setForeground(Color.BLACK);

    diagnosisResultLabel = new JLabel("Diagnosis Result:");
    diagnosisResultLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
    diagnosisResultLabel.setForeground(Color.BLACK);

    diagnosisResultTextArea = new JTextArea();
    diagnosisResultTextArea.setFont(new Font("Poppins", Font.PLAIN, 14));
    diagnosisResultTextArea.setPreferredSize(new Dimension(350, 100));

    referralRadioButton = new JRadioButton("Referral");
    referralRadioButton.setFont(new Font("Poppins", Font.PLAIN, 14));
    referralRadioButton.setForeground(Color.BLACK);

    prescriptionRadioButton = new JRadioButton("Prescription");
    prescriptionRadioButton.setFont(new Font("Poppins", Font.PLAIN, 14));
    prescriptionRadioButton.setForeground(Color.BLACK);

    actionStatusButtonGroup = new ButtonGroup();
    actionStatusButtonGroup.add(referralRadioButton);
    actionStatusButtonGroup.add(prescriptionRadioButton);

    JButton finishSessionButton = new JButton("Finish Session");
    finishSessionButton.setFont(new Font("Poppins", Font.BOLD, 14));
    finishSessionButton.setForeground(Color.WHITE);
    finishSessionButton.setBackground(Color.BLACK);
    finishSessionButton.setPreferredSize(new Dimension(175, 30));
    finishSessionButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String name = patientName;
          String diagnosis = diagnosisResultTextArea.getText();
          String status = "";
          if (prescriptionRadioButton.isSelected()) {
            status = "Prescription";
          } else if (referralRadioButton.isSelected()) {
            status = "Referral";
          }
          String date = currentDateLabel.getText().substring(6);

          Object[] rowData = { name, diagnosis, status};
          try {
            servController.addRecord(patientID, listpatient, rowData, (int)DoctorMenu.user[0]);
          } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }

          /*
          DefaultTableModel model = (DefaultTableModel) table.getModel();
          model.addRow(rowData);
          
          Masih binggung add rownya
          */

          dispose();
        }
      }
    );

    JPanel contentPane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(10, 10, 10, 10);
    c.anchor = GridBagConstraints.WEST;
    contentPane.add(patientNameLabel, c);

    c.gridy = 1;
    contentPane.add(currentDateLabel, c);

    c.gridy = 2;
    contentPane.add(diagnosisResultLabel, c);

    c.gridy = 3;
    contentPane.add(diagnosisResultTextArea, c);

    c.gridy = 4;
    contentPane.add(referralRadioButton, c);

    c.gridx = 1;
    contentPane.add(prescriptionRadioButton, c);

    c.gridx = 0;
    c.gridy = 5;
    c.gridwidth = 2;
    c.anchor = GridBagConstraints.CENTER;
    contentPane.add(finishSessionButton, c);

    setContentPane(contentPane);
  }

}
