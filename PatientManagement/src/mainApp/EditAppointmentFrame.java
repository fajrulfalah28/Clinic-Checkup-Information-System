package mainApp;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class EditAppointmentFrame extends JFrame {

  private JComboBox<String> monthComboBox;
  private JComboBox<Integer> dayComboBox;
  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> timeComboBox;
  private JTextField patientNameField;
  private JPanel doctorPanel;
  private int row;
  private JTable table; 

  public EditAppointmentFrame(
    Object date,
    Object time,
    Object patientName,
    Object doctor,
    int row,
    JTable table
  ) {
    this.row = row;
    this.table = table; 
    setTitle("Edit Appointment");
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
    monthComboBox.setSelectedIndex(getMonthIndex(date));
    monthComboBox.setFont(poppinsPlain);
    panel.add(monthComboBox, c);

    c.gridx = 2;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.insets = new Insets(10, 5, 10, 5);
    dayComboBox =
      new JComboBox<>();
    dayComboBox.setFont(poppinsPlain);
    panel.add(dayComboBox, c);

    c.gridx = 3;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 5, 10, 10);
    yearComboBox =
      new JComboBox<>(
        new Integer[] {
          2021,
          2022,
          2023,
          2024,
          2025
        }
      );
    yearComboBox.setSelectedItem(getYear(date));
    yearComboBox.setFont(poppinsPlain);
    panel.add(yearComboBox, c);

    c.gridx = 0;
    c.gridy = 1;
    JLabel timeLabel = new JLabel("Time:");
    timeLabel.setFont(poppinsBold);
    panel.add(timeLabel, c);

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
    timeComboBox.setSelectedItem(time);
    timeComboBox.setFont(poppinsPlain);
    panel.add(timeComboBox, c);

    c.gridx = 0;
    c.gridy = 2;
    JLabel patientNameLabel = new JLabel("Patient Name:");
    patientNameLabel.setFont(poppinsBold);
    panel.add(patientNameLabel, c);

    c.gridx = 1;
    patientNameField = new JTextField(patientName.toString(), 20);
    panel.add(patientNameField, c);

    c.gridx = 0;
    c.gridy = 3;
    JLabel doctorLabel = new JLabel("Doctor:");
    doctorLabel.setFont(poppinsBold);
    panel.add(doctorLabel, c);

    c.gridx = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 5, 10, 10);
    String[] doctorOptions = new String[] {"Andi", "Budi", "Rusdi"};
    JComboBox<String> doctorComboBox = new JComboBox<>(doctorOptions);
    doctorComboBox.setSelectedItem(doctor.toString());
    doctorComboBox.setFont(poppinsPlain);
    panel.add(doctorComboBox, c);

    c.gridx = 0;
    c.gridy = 4;
    c.gridwidth = 4;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(10, 10, 10, 10);
    JButton saveButton = new JButton("Save");
    saveButton.setFont(poppinsBold);
    saveButton.setBackground(Color.BLACK);
    saveButton.setForeground(Color.WHITE);
    saveButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String newDate = String.format(
            "%s %d, %d",
            monthComboBox.getSelectedItem(),
            dayComboBox.getSelectedItem(),
            yearComboBox.getSelectedItem()
          );
          String newTime = timeComboBox.getSelectedItem().toString();
          String newPatientName = patientNameField.getText();
          String newDoctor = doctorComboBox.getSelectedItem().toString();
          table.setValueAt(newDate, row, 0);
          table.setValueAt(newTime, row, 1);
          table.setValueAt(newPatientName, row, 2);
          table.setValueAt(newDoctor, row, 3);
          dispose();
        }
      }
    );
    panel.add(saveButton, c);

    add(panel);

    monthComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateDayComboBox();
      }
    });
    yearComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateDayComboBox();
      }
    });

    updateDayComboBox();
  }

  private int getMonthIndex(Object date) {
    String[] parts = date.toString().split(" ");
    String month = parts[0];
    switch (month) {
      case "January":
        return 0;
      case "February":
        return 1;
      case "March":
        return 2;
      case "April":
        return 3;
      case "May":
        return 4;
      case "June":
        return 5;
      case "July":
        return 6;
      case "August":
        return 7;
      case "September":
        return 8;
      case "October":
        return 9;
      case "November":
        return 10;
      case "December":
        return 11;
      default:
        return 0;
    }
  }

  private int getYear(Object date) {
    String[] parts = date.toString().split(" ");
    return Integer.parseInt(parts[2]);
  }

  private void updateDayComboBox() {
    int monthIndex = monthComboBox.getSelectedIndex();
    int year = (int) yearComboBox.getSelectedItem();
    int daysInMonth;
    switch (monthIndex) {
      case 1:
        daysInMonth = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
        break;
      case 3:
      case 5:
      case 8:
      case 10:
        daysInMonth = 30;
        break;
      default:
        daysInMonth = 31;
        break;
    }
    Integer[] days = new Integer[daysInMonth];
    for (int i = 1; i <= daysInMonth; i++) {
      days[i-1] = i;
    }
    DefaultComboBoxModel<Integer> dayModel = new DefaultComboBoxModel<>(days);
    dayComboBox.setModel(dayModel);
  }

}