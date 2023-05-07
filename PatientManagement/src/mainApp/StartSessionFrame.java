package mainApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

public class StartSessionFrame extends JFrame {

;
  private JComboBox<String> patientComboBox;
  private JButton startSessionButton;
  private JLabel selectPatientLabel;

  public StartSessionFrame(Object[][] data) {
    super("Start Session");
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setSize(400, 200);
    setLocationRelativeTo(null);
    setResizable(false);

    // Filter out appointments that are more than 1 hour in the past
    ArrayList<String> patientList = new ArrayList<>();
    LocalDateTime now = LocalDateTime.now();
    for (Object[] row : data) {
      Timestamp dateStr =  (Timestamp)row[3];
      // String timeStr = (String) row[1];
      Timestamp dateTimeStr = dateStr ;
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a");
      LocalDateTime dateTime = LocalDateTime.ofInstant(dateTimeStr.toInstant(), ZoneId.systemDefault());
      if (dateTime.isAfter(now.minusHours(1)) || dateTime.isAfter(now)) {
        String patientName = (String) row[4];
        if (!patientList.contains(patientName)) {
          patientList.add(patientName);
        }
      }
    }

    // Create combo box with filtered patient names
    selectPatientLabel = new JLabel("Select the patient to start the session");
    selectPatientLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
    selectPatientLabel.setForeground(Color.BLACK);

    patientComboBox = new JComboBox<>(patientList.toArray(new String[patientList.size()]));
    patientComboBox.setPreferredSize(new Dimension(200, 30));

    // Create "Start Session" button with logic to open new AddRecord frame
    startSessionButton = new JButton("Start Session");
    startSessionButton.setFont(new Font("Poppins", Font.BOLD, 14));
    startSessionButton.setForeground(Color.WHITE);
    startSessionButton.setBackground(Color.BLACK);
    startSessionButton.setPreferredSize(new Dimension(175, 30));
    startSessionButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selectedPatient = (String) patientComboBox.getSelectedItem();
        if (selectedPatient != null) {
          AddRecord addRecord = new AddRecord(selectedPatient);
          addRecord.setVisible(true);
        }
        setVisible(false);
      }
    });

    // Add components to content pane
    JPanel contentPane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(10, 10, 10, 10);
    c.anchor = GridBagConstraints.WEST;
    contentPane.add(selectPatientLabel, c);

    c.gridy = 1;
    contentPane.add(patientComboBox, c);

    c.gridy = 2;
    c.anchor = GridBagConstraints.CENTER;
    contentPane.add(startSessionButton, c);

    setContentPane(contentPane);
  }
}