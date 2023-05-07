package mainApp;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.tools.Diagnostic;

public class DoctorMenu extends javax.swing.JFrame {

  private JTabbedPane tabbedPane;
  private List<LocalDateTime> dates;
  private JLabel scheduleLabel;
  private JTable table;
  public static Object[] user;
  public static Object[][] dataSchedule;
  public static String[] dataColumnName;
  public static Object[][] DiagnosisTable;
  public static String[] diagnosisColumn;

  public DoctorMenu() {
    try {
        servController.getDataSchedule((int)user[0]);
        servController.getDiagnosisData();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setPreferredSize(screenSize);
    setUndecorated(true);

    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBackground(new Color(19, 117, 118));

    JButton logoutButton = new JButton("Logout");
    logoutButton.addActionListener(e -> {
      int option = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to logout?",
        "Logout",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
      );
      if (option == JOptionPane.YES_OPTION) {
        JFrame DoctorMenu = (JFrame) SwingUtilities.getWindowAncestor(
          mainPanel
        );
        DoctorMenu.dispose();

        LoginMenu loginMenu = new LoginMenu();
      }
    });
    logoutButton.setFont(new Font("Poppins", Font.BOLD, 21));
    logoutButton.setForeground(Color.WHITE);
    logoutButton.setBackground(new Color(19, 117, 118));
    logoutButton.setBorderPainted(false);

    JPanel buttonPanel = new JPanel(new BorderLayout());
    buttonPanel.setBackground(new Color(19, 117, 118));
    buttonPanel.add(logoutButton, BorderLayout.EAST);
    mainPanel.add(buttonPanel, BorderLayout.NORTH);

    tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
    tabbedPane.addTab("Home", createHomePanel());
    tabbedPane.addTab("Appointment", createAppointmentPanel());
    tabbedPane.addTab("Patient", createPatientPanel());
    tabbedPane.setBackground(Color.BLACK);
    tabbedPane.setForegroundAt(0, Color.WHITE);
    tabbedPane.setForegroundAt(1, Color.WHITE);
    tabbedPane.setForegroundAt(2, Color.WHITE);
    tabbedPane.setTabPlacement(JTabbedPane.TOP);

    mainPanel.add(tabbedPane, BorderLayout.CENTER);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1920, 1080);
    frame.setLayout(new BorderLayout());
    frame.getContentPane().setBackground(new Color(255, 250, 250));
    frame.add(mainPanel, BorderLayout.CENTER);

    pack();
    frame.setVisible(true);
  }

  public JPanel createHomePanel() {
    JPanel homePanel = new JPanel();
    homePanel.setBackground(new Color(235, 216, 200));
    homePanel.setLayout(new GridBagLayout());

    JLabel titleLabel = new JLabel("Welcome Doctor " + String.valueOf(user[3]) + " " + String.valueOf(user[4])+",", JLabel.CENTER);
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
    titleLabel.setForeground(new Color(19, 117, 118));
    GridBagConstraints c = new GridBagConstraints();

    JLabel chooseLabel = new JLabel(
      "Choose where you want to go",
      JLabel.CENTER
    );
    chooseLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
    chooseLabel.setForeground(Color.BLACK);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(235, 216, 200));
    buttonPanel.setLayout(new GridLayout(0, 2, 50, 50));

    JButton appointmentButton = new JButton("Appointment");
    appointmentButton.setFont(new Font("Poppins", Font.BOLD, 18));
    appointmentButton.setForeground(Color.WHITE);
    appointmentButton.setBackground(Color.BLACK);
    appointmentButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          tabbedPane.setSelectedIndex(1);
        }
      }
    );
    buttonPanel.add(appointmentButton);

    JButton patientButton = new JButton("Patient");
    patientButton.setFont(new Font("Poppins", Font.BOLD, 18));
    patientButton.setForeground(Color.WHITE);
    patientButton.setBackground(Color.BLACK);
    patientButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          tabbedPane.setSelectedIndex(2);
        }
      }
    );

    buttonPanel.add(patientButton);

    homePanel.addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = homePanel.getWidth();
          int height = homePanel.getHeight();
          int buttonWidth = (int) (width * 0.15);
          int buttonHeight = (int) (height * 0.05);

          c.gridx = 0;
          c.gridy = 0;
          c.insets = new Insets(20, 20, 0, 20);
          homePanel.add(titleLabel, c);

          c.gridx = 0;
          c.gridy = 1;
          c.insets = new Insets(10, 20, 20, 20);
          homePanel.add(chooseLabel, c);

          c.gridx = 0;
          c.gridy = 2;
          c.insets = new Insets(20, 20, 20, 20);
          homePanel.add(buttonPanel, c);

          float fontSize = 45.0f * Math.min(width, height) / 1000.0f;
          titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));

          fontSize = 27.0f * Math.min(width, height) / 1000.0f;
          chooseLabel.setFont(chooseLabel.getFont().deriveFont(fontSize));

          if (width < 896) {
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(20, 20, 0, 20);
            homePanel.add(titleLabel, c);

            c.gridx = 0;
            c.gridy = 1;
            c.insets = new Insets(10, 20, 20, 20);
            homePanel.add(chooseLabel, c);

            c.gridx = 0;
            c.gridy = 2;
            c.insets = new Insets(20, 20, 20, 20);
            homePanel.add(buttonPanel, c);

            fontSize = 45.0f * Math.min(width, height) / 1300.0f;
            titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));

            fontSize = 25.0f * Math.min(width, height) / 1300.0f;
            chooseLabel.setFont(chooseLabel.getFont().deriveFont(fontSize));
          }

          homePanel.revalidate();
          homePanel.repaint();
        }
      }
    );

    return homePanel;
  }

  public JPanel createAppointmentPanel() {
    JPanel appointmentPanel = new JPanel();
    appointmentPanel.setBackground(new Color(235, 216, 200));
    appointmentPanel.setLayout(new GridBagLayout());

    appointmentPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    JLabel titleLabel = new JLabel("Hey doctor "+ String.valueOf(user[3]) + " " +String.valueOf(user[4])+",", JLabel.CENTER);
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
    titleLabel.setForeground(new Color(19, 117, 118));
    c.gridy = 0;
    c.insets = new Insets(20, 0, 0, 0);
    appointmentPanel.add(titleLabel, c);

    JLabel chooseLabel = new JLabel(
      "Here are your appointment schedule...",
      JLabel.CENTER
    );
    chooseLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
    chooseLabel.setForeground(Color.BLACK);
    c.gridy = 1;
    c.insets = new Insets(0, 0, 20, 0);
    appointmentPanel.add(chooseLabel, c);

    JLabel shiftLabel = new JLabel(
      "Your current shift is: 08:00 - 17:00",
      JLabel.CENTER
    );
    shiftLabel.setFont(new Font("Poppins", Font.BOLD, 16));
    shiftLabel.setForeground(new Color(19, 117, 118));
    c.gridy = 2;
    c.insets = new Insets(0, 0, 30, 0);
    appointmentPanel.add(shiftLabel, c);

    TableModel datamodel = new DefaultTableModel(dataSchedule, dataColumnName);
    JTable table = new JTable(datamodel);
    TableRowSorter sort = new TableRowSorter<TableModel>(datamodel);
    table.setRowSorter(sort);
    table.getTableHeader().setReorderingAllowed(false);
    JScrollPane tableScrollPane = new JScrollPane(table);
    c.gridy = 3;
    c.insets = new Insets(0, 50, 0, 50);
    c.weightx = 1.0;
    c.weighty = 1.0;
    table.setEnabled(false);
    appointmentPanel.add(tableScrollPane, c);

    scheduleLabel = new JLabel(getScheduleLabelText(), JLabel.CENTER);
    scheduleLabel.setFont(new Font("Poppins", Font.BOLD, 16));
    scheduleLabel.setForeground(new Color(19, 117, 118));
    c.gridy = 4;
    c.insets = new Insets(0, 0, 0, 0);
    appointmentPanel.add(scheduleLabel, c);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonPanel.setBackground(new Color(235, 216, 200));

    JButton changeShiftButton = new JButton("Change Shift");
    changeShiftButton.setFont(new Font("Poppins", Font.BOLD, 14));
    changeShiftButton.setForeground(Color.WHITE);
    changeShiftButton.setBackground(Color.BLACK);
    changeShiftButton.setPreferredSize(new Dimension(175, 30));
    changeShiftButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          ShiftChange shiftChange = new ShiftChange(shiftLabel);
          shiftChange.setVisible(true);
        }
      }
    );
    buttonPanel.add(changeShiftButton);

    JButton startSessionButton = new JButton("Start Session");
    startSessionButton.setFont(new Font("Poppins", Font.BOLD, 14));
    startSessionButton.setForeground(Color.WHITE);
    startSessionButton.setBackground(Color.BLACK);
    startSessionButton.setPreferredSize(new Dimension(175, 30));
    startSessionButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            servController.listPatient((int)user[0]);
          } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          StartSessionFrame startSessionFrame = new StartSessionFrame(
            dataSchedule
          );
          startSessionFrame.setVisible(true);
        }
      }
    );
    buttonPanel.add(startSessionButton);

    c.gridy = 5;
    c.weightx = 1.0;
    c.anchor = GridBagConstraints.CENTER;
    appointmentPanel.add(buttonPanel, c);

    appointmentPanel.addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = appointmentPanel.getWidth();
          int height = appointmentPanel.getHeight();

          float fontSize = 30.0f * Math.min(width, height) / 1000.0f;
          buttonPanel.setFont(buttonPanel.getFont().deriveFont(fontSize));
          appointmentPanel.add(buttonPanel, c);

          fontSize = 45.0f * Math.min(width, height) / 1000.0f;
          titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));

          fontSize = 27.0f * Math.min(width, height) / 1000.0f;
          chooseLabel.setFont(chooseLabel.getFont().deriveFont(fontSize));
        }
      }
    );
    return appointmentPanel;
  }

//   private Object[][] getData() {
//     Object[][] data = {
//       { "May 3, 2023", "10:00 AM", "John Doe"},
//       { "May 4, 2023", "10:30 AM", "Jane Smith"},
//       { "May 5, 2023", "11:15 AM", "Bob Johnson"},
//     };
//     return data;
//   }

//   private String[] getColumnNames() {
//     String[] columnNames = {
//       "Date",
//       "Time",
//       "Patient Name"
//     };
//     return columnNames;
//   }

  private String getScheduleLabelText() {
    Object[][] data = dataSchedule;
    // SimpleDateFormat sdformat = new SimpleDateFormat("MMMM d, yyyy h:mm a");
    // Date now = new Date();
    Instant closestDateTime;
    Instant sql;
    Timestamp dateTimeStr = (Timestamp) data[0][3];
    sql = dateTimeStr.toInstant();
    closestDateTime = dateTimeStr.toInstant();
    
    for (Object[] row : data) {
    //   String dateStr = (String) row[3];
    //   String timeStr = (String) row[1];
      dateTimeStr = (Timestamp) row[3];
      sql = dateTimeStr.toInstant();
    //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
    //     "MMMM d, yyyy h:mm a"
    //   );
    //   Date dateTime = new Date(dateTimeStr.getTime());
      if (sql.compareTo(Instant.now()) >= 0 && (sql.isBefore(closestDateTime))) {
        closestDateTime = sql;
        break;
      }
    }
    // if (closestDateTime == null) {
    //   return "No upcoming appointments";
    // }

    Duration duration = Duration.between(Instant.now(), closestDateTime);
    long days = duration.toDays();
    long hours = duration.toHours() % 24;
    long minutes = duration.toMinutes() % 60;
    String labelStr = null;
    if(duration.toDays() > 0 ){
        labelStr = String.format(
            "Your next appointment will be held in %d days %d hours and %d minutes",
            days,
            hours,
            minutes
        );
    }else if(duration.toDays() == 0 ){
        labelStr = String.format(
            "Your next appointment will be held in %d hours and %d minutes",
            hours,
            minutes
        );
    } else {
        labelStr = "No upcoming Appoinments";
    }

    return labelStr;
  }

  public JPanel createPatientPanel() {
    JPanel patientPanel = new JPanel();
    patientPanel.setBackground(new Color(235, 216, 200));
    patientPanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    JLabel titleLabel = new JLabel("Hey doctor " + String.valueOf(user[3]) + " " +String.valueOf(user[4])+",", JLabel.CENTER);
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
    titleLabel.setForeground(new Color(19, 117, 118));
    c.gridy = 0;
    c.insets = new Insets(20, 0, 0, 0);
    patientPanel.add(titleLabel, c);

    JLabel chooseLabel = new JLabel(
      "Here are your patient session history",
      JLabel.CENTER
    );
    chooseLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
    chooseLabel.setForeground(Color.BLACK);
    c.gridy = 1;
    c.insets = new Insets(0, 0, 20, 0);
    patientPanel.add(chooseLabel, c);

    c.gridy = 2;
    c.insets = new Insets(0, 50, 0, 50);
    JPanel searchPanel = new JPanel(new BorderLayout());
    JTextField searchField = new JTextField(20);
    searchPanel.add(searchField, BorderLayout.CENTER);
    JButton searchButton = new JButton("Search");
    searchButton.setFont(new Font("Poppins", Font.BOLD, 18));
    searchButton.setForeground(Color.WHITE);
    searchButton.setBackground(Color.BLACK);
    searchPanel.add(searchButton, BorderLayout.EAST);
    patientPanel.add(searchPanel, c);
    
    TableModel model = new DefaultTableModel(DiagnosisTable, diagnosisColumn);
    JTable table = new JTable(model);
    TableRowSorter rowSort = new TableRowSorter(model);
    table.setRowSorter(rowSort);
    table.getTableHeader().setReorderingAllowed(false);
    JScrollPane scrollPane = new JScrollPane(table);
    c.gridy = 3;
    c.weightx = 1.0;
    c.weighty = 1.0;
    c.insets = new Insets(0, 50, 100, 50);
    patientPanel.add(scrollPane, c);
    
    searchButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String searchTerm = searchField.getText();
            rowSort.setRowFilter(new filter(searchTerm));
        //   List<Object[]> filteredRows = performSearch(searchTerm);

        //   DefaultTableModel model = (DefaultTableModel) table.getModel();
        //   model.setDataVector(
        //     filteredRows.toArray(new Object[0][]),
        //     new String[] {
        //       "Patient Name",
        //       "Diagnosis Result",
        //       "Status",
        //       "Date",
        //     }
        //   );
        }

        // private List<Object[]> performSearch(String searchTerm) {
        //   return null;
        // }
      }
    );




    // // Buat Nambahin Row Baru di tabel patient
    // Object[] rowData = {};

    patientPanel.addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = patientPanel.getWidth();
          int height = patientPanel.getHeight();

          float fontSize = 45.0f * Math.min(width, height) / 1000.0f;
          titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));

          fontSize = 27.0f * Math.min(width, height) / 1000.0f;
          chooseLabel.setFont(chooseLabel.getFont().deriveFont(fontSize));
        }
      }
    );

    return patientPanel;
  }
  private class filter extends RowFilter{
    String searchText;
    filter(String searchText){
        this.searchText = searchText.toLowerCase();
    }
    @Override
    public boolean include(Entry entry) {
        // TODO Auto-generated method stub
       return entry.getStringValue(0).toLowerCase().indexOf(searchText) >= 0;
    }
    
  }
}
