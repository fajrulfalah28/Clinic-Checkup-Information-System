package mainApp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShiftChange extends JFrame {

  private JComboBox<String> startHourComboBox;
  private JComboBox<String> finishHourComboBox;
  private JLabel shiftLabel;

  public ShiftChange(JLabel shiftLabel) {
    this.shiftLabel = shiftLabel;

    setTitle("Change Shift");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(400, 250);
    setLocationRelativeTo(null);

    getContentPane().setBackground(new Color(235, 216, 200));

    JPanel comboBoxPanel = new JPanel(new GridBagLayout());
    comboBoxPanel.setBackground(new Color(235, 216, 200));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 10, 0, 10);

    startHourComboBox = new JComboBox<String>();
    for (int i = 6; i <= 21; i++) {
      startHourComboBox.addItem(String.format("%02d:00", i));
    }
    startHourComboBox.setFont(new Font("Poppins", Font.PLAIN, 16));
    startHourComboBox.setForeground(Color.BLACK);
    gbc.gridy++;
    comboBoxPanel.add(new JLabel("Start Hour:"), gbc);
    gbc.gridy++;
    comboBoxPanel.add(startHourComboBox, gbc);

    finishHourComboBox = new JComboBox<String>();
    for (int i = 7; i <= 22; i++) {
      finishHourComboBox.addItem(String.format("%02d:00", i));
    }
    finishHourComboBox.setFont(new Font("Poppins", Font.PLAIN, 16));
    finishHourComboBox.setForeground(Color.BLACK);
    gbc.gridy++;
    comboBoxPanel.add(new JLabel("Finish Hour:"), gbc);
    gbc.gridy++;
    comboBoxPanel.add(finishHourComboBox, gbc);

    add(comboBoxPanel, BorderLayout.CENTER);

    JButton changeButton = new JButton("Change Shift Hour");
    changeButton.setFont(new Font("Poppins", Font.BOLD, 12));
    changeButton.setForeground(Color.WHITE);
    changeButton.setBackground(Color.BLACK);
    changeButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          updateShiftLabel();
          dispose();
        }
      }
    );
    add(changeButton, BorderLayout.SOUTH);
  }

  private void updateShiftLabel() {
    String startHour = startHourComboBox.getSelectedItem().toString();
    String finishHour = finishHourComboBox.getSelectedItem().toString();
    shiftLabel.setText(
      "Your current shift is: " + startHour + " - " + finishHour
    );
  }
}
