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

    JLabel diagnosisLabel = new JLabel("Diagnosis Result:");
    diagnosisLabel.setFont(boldFont);
    c.gridx = 0;
    c.gridy = 1;
    frame.getContentPane().add(diagnosisLabel, c);

    JTextArea diagnosisArea = new JTextArea(5, 20);
    diagnosisArea.setFont(plainFont);
    JScrollPane diagnosisScrollPane = new JScrollPane(diagnosisArea);
    c.gridx = 1;
    c.gridy = 1;
    frame.getContentPane().add(diagnosisScrollPane, c);

    JLabel actionLabel = new JLabel("Action Status:");
    actionLabel.setFont(boldFont);
    c.gridx = 0;
    c.gridy = 2;
    frame.getContentPane().add(actionLabel, c);

    JComboBox<String> actionComboBox = new JComboBox<>(new String[]{"a", "b"});
    actionComboBox.setFont(plainFont);
    c.gridx = 1;
    c.gridy = 2;
    frame.getContentPane().add(actionComboBox, c);

    JButton addButton = new JButton("Add");
    addButton.setFont(new Font("Poppins", Font.BOLD, 18));
    addButton.setForeground(Color.WHITE);
    addButton.setBackground(Color.BLACK);
    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 2;
    c.anchor = GridBagConstraints.CENTER;
    addButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String name = nameField.getText();
          String diagnosis = diagnosisArea.getText();
          String action = (String) actionComboBox.getSelectedItem();

          Object[] record = {
            name,
            diagnosis,
            action
          };

          DefaultTableModel model = (DefaultTableModel) table.getModel();

          model.addRow(record);

          frame.dispose();
        }
      }
    );
    frame.getContentPane().add(addButton, c);

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}