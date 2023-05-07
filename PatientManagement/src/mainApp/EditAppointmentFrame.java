package mainApp;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.*;

class EditAppointmentFrame extends JFrame {

  private JComboBox<String> monthComboBox;
  private JComboBox<Integer> dayComboBox;
  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> timeComboBox;
  private JComboBox<String> DoctorComboBox;
  private JTextField patientNameField;
  private JPanel doctorPanel;
  private int row;
  private JTable table; // declare table as an instance variable
  public static String[] doctors;
  public static int doctorId;
  public static int[] servDoctorID;
  

  public EditAppointmentFrame(
    int Id,
    Object date,
    Object time,
    Object patientName,
    Object doctor,
    int row,
    JTable table
  ) {
    this.row = row;
    this.table = table; // initialize the instance variable with the passed-in parameter
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
          "01",
          "02",
          "03",
          "04",
          "05",
          "06",
          "07",
          "08",
          "09",
          "10",
          "11",
          "12",
        }
      );
    monthComboBox.setSelectedIndex(Integer.parseInt(date.toString().split("-")[1]) -1);
    monthComboBox.setFont(poppinsPlain);
    panel.add(monthComboBox, c);

    c.gridx = 2;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.insets = new Insets(10, 5, 10, 5);
    dayComboBox =
      new JComboBox<>(
        createDayArray(Integer.parseInt(date.toString().split("-")[1]) - 1, Integer.parseInt(getDay(date)))
      );
    dayComboBox.setSelectedIndex(Integer.parseInt(getDay(date)) - 1);
    dayComboBox.setFont(poppinsPlain);
    panel.add(dayComboBox, c);

    c.gridx = 3;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 5, 10, 10);
    yearComboBox =
      new JComboBox<>(createYearArray(Integer.parseInt(getYear(date))));
    yearComboBox.setSelectedIndex(3);
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
          "11:00 PM",
          "12:00 AM",
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
    doctorPanel = new JPanel();
    doctorPanel.setLayout(new BoxLayout(doctorPanel, BoxLayout.Y_AXIS)); // change layout to BoxLayout
    DoctorComboBox = new JComboBox<>(doctors);
    // JRadioButton andiRadioButton = new JRadioButton("Andi");
    // JRadioButton budiRadioButton = new JRadioButton("Budi");
    // JRadioButton rusdiRadioButton = new JRadioButton("Rusdi");
    // ButtonGroup doctorButtonGroup = new ButtonGroup();
    // doctorButtonGroup.add(andiRadioButton);
    // doctorButtonGroup.add(budiRadioButton);
    // doctorButtonGroup.add(rusdiRadioButton);
    // if (doctor.toString().equals("1")) {
    //   andiRadioButton.setSelected(true);
    // } else if (doctor.toString().equals("2")) {
    //   budiRadioButton.setSelected(true);
    // } else if (doctor.toString().equals("3")) {
    //   rusdiRadioButton.setSelected(true);
    // }
    // doctorPanel.add(andiRadioButton);
    // doctorPanel.add(budiRadioButton);
    // doctorPanel.add(rusdiRadioButton);
    doctorPanel.add(DoctorComboBox);
    timeComboBox.setSelectedItem(1);
    panel.add(doctorPanel, c);

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
          String newDate;
          if((int)dayComboBox.getSelectedItem() >= 10){
          newDate =  String.valueOf(yearComboBox.getSelectedItem()) + "-" + String.valueOf(monthComboBox.getSelectedItem()) + "-" + String.valueOf(dayComboBox.getSelectedItem());
          } else {
          newDate =  String.valueOf(yearComboBox.getSelectedItem()) + "-" + String.valueOf(monthComboBox.getSelectedItem()) + "-0" + String.valueOf(dayComboBox.getSelectedItem());
          }
          String newTime = timeComboBox.getSelectedItem().toString();
          String newDateTime = newDate + " " + newTime;
          String newPatientName = patientNameField.getText();
          String newDoctor = String.valueOf(DoctorComboBox.getSelectedItem());
          doctorId = DoctorComboBox.getSelectedIndex();
          // if (andiRadioButton.isSelected()) {
          //   newDoctor = "Andi";
          //   //edit di database juga buat id docternya
          // } else if (budiRadioButton.isSelected()) {
          //   newDoctor = "Budi";
          //   //edit di database juga buat id docternya
          // } else if (rusdiRadioButton.isSelected()) {
          //   newDoctor = "Rusdi";
            
          // }
          table.setValueAt(newDate, row, 3);
          table.setValueAt(newTime, row, 4);
          table.setValueAt(newPatientName, row, 2);
          table.setValueAt(newDoctor, row, 1);
          System.out.println(doctorId);
          try {
            
            servController.servAppointmentUpdate(servDoctorID[doctorId], newDateTime, Id );
          } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          dispose();
        }
      }
    );
    panel.add(saveButton, c);

    add(panel);
  }

  // private int getMonthIndex(Object date) {
  //   String month = date.toString().split("-")[1];
  //   String[] months = new String[] {
  //     "January",
  //     "February",
  //     "March",
  //     "April",
  //     "May",
  //     "June",
  //     "July",
  //     "August",
  //     "September",
  //     "October",
  //     "November",
  //     "December",
  //   };
  //   for (int i = 0; i < months.length; i++) {
  //     if (months[i].equals(month)) {
  //       return i;
  //     }
  //   }
  //   return -1;
  // }

  private String getDay(Object date) {
    return date.toString().split("-")[2].replace(",", "");
  }

  private String getYear(Object date) {
    return date.toString().split("-")[0];
  }

  private Integer[] createYearArray(int currentYear) {
    int startYear = 2020;
    Integer[] years = new Integer[currentYear - startYear + 1];
    for (int i = 0; i < years.length; i++) {
      years[i] = startYear + i;
    }
    return years;
  }

  private Integer[] createDayArray(int monthIndex, int currentDay) {
    int numDays = getNumDays(monthIndex);
    Integer[] days = new Integer[numDays];
    for (int i = 0; i < days.length; i++) {
      days[i] = i + 1;
    }
    return days;
  }

  private int getNumDays(int monthIndex) {
    int[] numDays = new int[] {
      31,
      28,
      31,
      30,
      31,
      30,
      31,
      31,
      30,
      31,
      30,
      31,
    };
    return numDays[monthIndex];
  }
}
