package mainApp;

import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.microsoft.sqlserver.jdbc.StringUtils;

public class AdminMenu extends javax.swing.JFrame {

  private JTabbedPane tabbedPane;
  private JTable table;
  static Object[] user;
  public static Object[][] dataSchedule;
  public static String[] dataColumnName;
  public static Object[][] DiagnosisTable;
  public static String[] diagnosisColumn;
  public static Object[][] MedicalRecord;
  public static String[] MedRecordColumn;
  public static Object[][] Credentials;
  public static String[] credColumn;
public static String[] doctors;
public static int[] servDoctorID;
public static Object[][] patient;
public static String[] patientcol;
  private JComboBox<String> monthComboBox;
  private JComboBox<Integer> dayComboBox;
  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> timeComboBox;
  private JTextField patientNameField;
  private JPanel doctorPanel;
  private DefaultTableModel tableModel;

  public AdminMenu() {
    try {
      servController.getDataScheduleAdmin();
      servController.getMedRecord();
      servController.getCred((int)user[0]);
      servController.getAllDoctor();
      
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
    tabbedPane.addTab("Edit Appointment", createEditAppointmentPanel());
    tabbedPane.addTab("Edit Patient", createPatientPanel());
    tabbedPane.addTab("Edit Account", createDoctorPanel());
    tabbedPane.addTab("Add Patient", createAddPatient());
    tabbedPane.addTab("Patient list", createListPatientPanel());
    tabbedPane.setBackground(Color.BLACK);
    tabbedPane.setForegroundAt(0, Color.WHITE);
    tabbedPane.setForegroundAt(1, Color.WHITE);
    tabbedPane.setForegroundAt(2, Color.WHITE);
    tabbedPane.setForegroundAt(3, Color.WHITE);
    tabbedPane.setForegroundAt(4, Color.WHITE);
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

    JLabel titleLabel = new JLabel("Welcome Admin " + String.valueOf(user[3]) + " " + String.valueOf(user[4])+",", JLabel.CENTER);
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
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

    JButton appointmentButton = new JButton("Edit Appointment");
    appointmentButton.setFont(new Font("Poppins", Font.BOLD, 18));
    appointmentButton.setForeground(Color.WHITE);
    appointmentButton.setBackground(Color.BLACK);
    appointmentButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            servController.getAllDoctor();
          } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          tabbedPane.setSelectedIndex(1);
        }
      }
    );
    buttonPanel.add(appointmentButton);

    JButton patientButton = new JButton("Edit Patient");
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

    JButton doctorButton = new JButton("Edit Doctor");
    doctorButton.setFont(new Font("Poppins", Font.BOLD, 18));
    doctorButton.setForeground(Color.WHITE);
    doctorButton.setBackground(Color.BLACK);
    doctorButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          tabbedPane.setSelectedIndex(3);
        }
      }
    );
    buttonPanel.add(doctorButton);

    JButton addDocButton = new JButton("Add Patient");
    addDocButton.setFont(new Font("Poppins", Font.BOLD, 18));
    addDocButton.setForeground(Color.WHITE);
    addDocButton.setBackground(Color.BLACK);
    addDocButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          tabbedPane.setSelectedIndex(4);
        }
      }
    );
    buttonPanel.add(doctorButton);

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

  public JPanel createEditAppointmentPanel() {
    JPanel appointmentPanel = new JPanel();
    appointmentPanel.setBackground(new Color(235, 216, 200));
    appointmentPanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    JLabel titleLabel = new JLabel("Hey Admin " + String.valueOf(user[3]) + " " + String.valueOf(user[4])+",", JLabel.CENTER);
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
    titleLabel.setForeground(new Color(19, 117, 118));
    c.gridy = 0;
    c.insets = new Insets(20, 0, 0, 0);
    appointmentPanel.add(titleLabel, c);

    JLabel chooseLabel = new JLabel(
      "Here are the current available appointments...",
      JLabel.CENTER
    );
    chooseLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
    chooseLabel.setForeground(Color.BLACK);
    c.gridy = 1;
    c.insets = new Insets(0, 0, 50, 0);
    appointmentPanel.add(chooseLabel, c);

    // String[] columnNames = { "Date", "Time", "Patient Name", "Doctor" };
    Object[][] data = dataSchedule;

    DefaultTableModel tableModel = new DefaultTableModel(data, dataColumnName);
    JTable table = new JTable(tableModel);

    JScrollPane tableScrollPane = new JScrollPane(table);
    c.gridy = 2;
    c.insets = new Insets(0, 50, 0, 50);
    c.weightx = 1.0;
    c.weighty = 1.0;
    appointmentPanel.add(tableScrollPane, c);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(235, 216, 200));
    c.gridy = 3;
    c.insets = new Insets(50, 0, 10, 0);
    c.weightx = 1.0;

    JButton editButton = new JButton("Edit Data");
    editButton.setFont(new Font("Poppins", Font.BOLD, 18));
    editButton.setForeground(Color.WHITE);
    editButton.setBackground(Color.BLACK);
    buttonPanel.add(editButton);

    JButton addButton = new JButton("Add Data");
    addButton.setFont(new Font("Poppins", Font.BOLD, 18));
    addButton.setForeground(Color.WHITE);
    addButton.setBackground(Color.BLACK);
    buttonPanel.add(addButton);

    appointmentPanel.add(buttonPanel, c);

    editButton.addActionListener(
      new ActionListener() {
        boolean isEditing = false;

        @Override
        public void actionPerformed(ActionEvent e) {
          if (isEditing) {
            isEditing = false;
            editButton.setText("Edit Data");
            table.setEnabled(true);
            table.setRowSelectionAllowed(true);
          } else {
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();
            int Id = (int)table.getValueAt(row, 0);
            Object date = table.getValueAt(row, 3);
            Object time = table.getValueAt(row, 4);
            Object patientName = table.getValueAt(row, 2);
            Object doctor = table.getValueAt(row, 1);
            EditAppointmentFrame editFrame = new EditAppointmentFrame(
              Id,
              date,
              time,
              patientName,
              doctor,
              row,
              table
            ); // pass table as argument
            editFrame.setVisible(true);

            isEditing = true;
            editButton.setText("Finish");
            table.setEnabled(false);
            table.setRowSelectionAllowed(false);
          }
        }
      }
    );

    addButton.addActionListener(
    new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddAppointmentFrame addFrame = new AddAppointmentFrame(tableModel);
            addFrame.setVisible(true);
        }
    }
);

    appointmentPanel.addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = appointmentPanel.getWidth();
          int height = appointmentPanel.getHeight();

          float fontSize = 30.0f * Math.min(width, height) / 1000.0f;
          buttonPanel.setFont(buttonPanel.getFont().deriveFont(fontSize));

          fontSize = 45.0f * Math.min(width, height) / 1000.0f;
          titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));
        }
      }
    );

    return appointmentPanel;
  }

  public JPanel createPatientPanel() {
    JPanel patientPanel = new JPanel();
    patientPanel.setBackground(new Color(235, 216, 200));
    patientPanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    JLabel titleLabel = new JLabel("Hey Admin " + String.valueOf(user[3]) + " " + String.valueOf(user[4])+",", JLabel.CENTER);
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
    titleLabel.setForeground(new Color(19, 117, 118));
    c.gridy = 0;
    c.insets = new Insets(20, 0, 0, 0);
    patientPanel.add(titleLabel, c);

    JLabel chooseLabel = new JLabel(
      "Here are the current available appointments...",
      JLabel.CENTER
    );
    chooseLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
    chooseLabel.setForeground(Color.BLACK);
    c.gridy = 1;
    c.insets = new Insets(0, 0, 50, 0);
    patientPanel.add(chooseLabel, c);

    String[] columnNames = MedRecordColumn;
    Object[][] data = MedicalRecord;

    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
    JTable table = new JTable(tableModel);

    JScrollPane tableScrollPane = new JScrollPane(table);
    c.gridy = 2;
    c.insets = new Insets(0, 50, 0, 50);
    c.weightx = 1.0;
    c.weighty = 1.0;
    patientPanel.add(tableScrollPane, c);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(235, 216, 200));
    c.gridy = 3;
    c.insets = new Insets(50, 0, 10, 0);
    c.weightx = 1.0;

    JButton editButton = new JButton("Edit Data");
    editButton.setFont(new Font("Poppins", Font.BOLD, 18));
    editButton.setForeground(Color.WHITE);
    editButton.setBackground(Color.BLACK);
    buttonPanel.add(editButton);

    JButton addButton = new JButton("Add Data");
    addButton.setFont(new Font("Poppins", Font.BOLD, 18));
    addButton.setForeground(Color.WHITE);
    addButton.setBackground(Color.BLACK);
    buttonPanel.add(addButton);
    patientPanel.add(buttonPanel, c);

    editButton.addActionListener(
      new ActionListener() {
        boolean isEditing = false;

        @Override
        public void actionPerformed(ActionEvent e) {
          if (isEditing) {
            isEditing = false;
            editButton.setText("Edit Data");
            table.setEnabled(true);
            table.setRowSelectionAllowed(true);
          } else {
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();
            Object MRID = table.getValueAt(row, 0);
            Object patientName = table.getValueAt(row, 1);
            Object nurseName = table.getValueAt(row, 2);
            Object Systolic = table.getValueAt(row, 3);
            Object Diastolic = table.getValueAt(row,4);
            Object heartRate = table.getValueAt(row,5);
            Object bodyTemp = table.getValueAt(row, 6);
            Object bodyHeight = table.getValueAt(row, 7);
            Object diagID = table.getValueAt(row,8);
            Object Doctor = table.getValueAt(row,9);
            Object diagRes = table.getValueAt(row,10);
            Object actStat = table.getValueAt(row,11);
            EditPatientFrame editFrame = new EditPatientFrame(
              MRID, patientName, nurseName, Systolic, Diastolic, heartRate, bodyTemp, bodyHeight, diagID, Doctor, diagRes, actStat, row, table
            ); // pass table as argument
            editFrame.setVisible(true);
            isEditing = true;
            editButton.setText("Finish");
            table.setEnabled(false);
            table.setRowSelectionAllowed(false);
          }
        }
      }
    );

    addButton.addActionListener(
    new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddPatientFrame addFrame = new AddPatientFrame(tableModel);
            addFrame.setVisible(true);
        }
      }
    );
    patientPanel.addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = patientPanel.getWidth();
          int height = patientPanel.getHeight();

          float fontSize = 30.0f * Math.min(width, height) / 1000.0f;
          buttonPanel.setFont(buttonPanel.getFont().deriveFont(fontSize));

          fontSize = 45.0f * Math.min(width, height) / 1000.0f;
          titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));
        }
      }
    );

    return patientPanel;
  }

  public JPanel createDoctorPanel() {
    JPanel doctorPanel = new JPanel();
    doctorPanel.setBackground(new Color(235, 216, 200));
    doctorPanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    JLabel titleLabel = new JLabel("Hey Admin " + String.valueOf(user[3]) + " " + String.valueOf(user[4])+"," , JLabel.CENTER);
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
    titleLabel.setForeground(new Color(19, 117, 118));
    c.gridy = 0;
    c.insets = new Insets(20, 0, 0, 0);
    doctorPanel.add(titleLabel, c);

    JLabel chooseLabel = new JLabel(
      "Here are the current available appointments...",
      JLabel.CENTER
    );
    chooseLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
    chooseLabel.setForeground(Color.BLACK);
    c.gridy = 1;
    c.insets = new Insets(0, 0, 50, 0);
    doctorPanel.add(chooseLabel, c);

    String[] columnNames = credColumn;
    Object[][] data = Credentials;

    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
    JTable table = new JTable(tableModel);

    JScrollPane tableScrollPane = new JScrollPane(table);
    c.gridy = 2;
    c.insets = new Insets(0, 50, 0, 50);
    c.weightx = 1.0;
    c.weighty = 1.0;
    doctorPanel.add(tableScrollPane, c);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(235, 216, 200));
    c.gridy = 3;
    c.insets = new Insets(50, 0, 10, 0);
    c.weightx = 1.0;

    JButton editButton = new JButton("Delete Account");
    editButton.setFont(new Font("Poppins", Font.BOLD, 18));
    editButton.setForeground(Color.WHITE);
    editButton.setBackground(Color.BLACK);
    buttonPanel.add(editButton);
    doctorPanel.add(buttonPanel, c);

    editButton.addActionListener(new ActionListener() {
      boolean isEditing = false;
  
      @Override
      public void actionPerformed(ActionEvent e) {
          int selectedRow = table.getSelectedRow();
          if (selectedRow != -1) {
            
              int confirmResult = JOptionPane.showConfirmDialog(
                      doctorPanel,
                      "Are you sure you want to delete this account?",
                      "Confirmation",
                      JOptionPane.YES_NO_OPTION);
              if (confirmResult == JOptionPane.YES_OPTION) {
                try {
                  servController.deleteCredential((int)table.getModel().getValueAt(table.getSelectedRow(), 0));
                } catch (SQLException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
                }
                  DefaultTableModel model = (DefaultTableModel) table.getModel();
                  model.removeRow(selectedRow);
              }
          }
      }
  });

    doctorPanel.addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = doctorPanel.getWidth();
          int height = doctorPanel.getHeight();

          float fontSize = 30.0f * Math.min(width, height) / 1000.0f;
          buttonPanel.setFont(buttonPanel.getFont().deriveFont(fontSize));

          fontSize = 45.0f * Math.min(width, height) / 1000.0f;
          titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));
        }
      }
    );

    return doctorPanel;
  }

  public JPanel createAddPatient() {
    JPanel patientPanel = new JPanel();
    patientPanel.setBackground(new Color(235, 216, 200));
    patientPanel.setLayout(new GridBagLayout());

    Font poppinsBold = new Font("Poppins", Font.BOLD, 14);
    Font poppinsPlain = new Font("Poppins", Font.PLAIN, 14);

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.weightx = 1.0;
    c.insets = new Insets(0, 50, 0, 0);
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    c.gridy = 0;
    JLabel titleLabel = new JLabel("Add Patient");
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
    titleLabel.setForeground(new Color(19, 117, 118));
    c.insets = new Insets(10, 50, 10, 10);
    patientPanel.add(titleLabel, c);

    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(10, 50, 10, 10);
    JLabel IDCardLabel = new JLabel("ID Card Number:");
    IDCardLabel.setFont(poppinsBold);
    patientPanel.add(IDCardLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField IDCardField = new JTextField(20);
    patientPanel.add(IDCardField, c);

    c.gridx = 0;
    c.gridy = 2;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel FNameLabel = new JLabel("First Name:");
    FNameLabel.setFont(poppinsBold);
    patientPanel.add(FNameLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField FNameField = new JTextField(20);
    patientPanel.add(FNameField, c);

    c.gridx = 0;
    c.gridy = 3;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel LNameLabel = new JLabel("Last Name:");
    LNameLabel.setFont(poppinsBold);
    patientPanel.add(LNameLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField LNameField = new JTextField(20);
    patientPanel.add(LNameField, c);

    c.gridx = 0;
    c.gridy = 4;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel dateLabel = new JLabel("Date Of Birth:");
    dateLabel.setFont(poppinsBold);
    patientPanel.add(dateLabel, c);

    c.gridx = 2;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(10, 5, 10, 5);
    monthComboBox =
      new JComboBox<>(
        new String[] {
          "Januari",
          "Februari",
          "Maret",
          "April",
          "Mei",
          "Juni",
          "Juli",
          "Agustus",
          "September",
          "Oktober",
          "November",
          "Desember",
        }
      );
    monthComboBox.setFont(poppinsPlain);
    patientPanel.add(monthComboBox, c);

    c.gridx = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(10, 5, 10, 5);
    dayComboBox = new JComboBox<>();
    dayComboBox.setFont(poppinsPlain);
    patientPanel.add(dayComboBox, c);

    c.gridx = 3;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(10, 5, 10, 750);
    yearComboBox =
      new JComboBox<>(
        new Integer[] {
          2000,
          2001,
          2002,
          2003,
          2004,
          2005,
          2006,
          2007,
          2008,
          2009,
          2010,
          2011,
          2012,
          2013,
          2014,
          2015,
          2016,
          2017,
          2018,
          2019,
          2020,
          2021,
          2022,
          2023,
        }
      );
    yearComboBox.setFont(poppinsPlain);
    patientPanel.add(yearComboBox, c);

    c.gridx = 0;
    c.gridy = 5;
    c.insets = new Insets(10, 50, 10, 10);
    JLabel genderLabel = new JLabel("Gender:");
    genderLabel.setFont(poppinsBold);
    patientPanel.add(genderLabel, c);

    c.gridx = 1;
    JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    ButtonGroup genderGroup = new ButtonGroup();
    JRadioButton maleButton = new JRadioButton("Male");
    JRadioButton femaleButton = new JRadioButton("Female");
    genderPanel.setBackground(new Color(235, 216, 200));
    maleButton.setBackground(new Color(235, 216, 200));
    femaleButton.setBackground(new Color(235, 216, 200));
    genderGroup.add(maleButton);
    genderGroup.add(femaleButton);
    genderPanel.add(maleButton);
    genderPanel.add(femaleButton);
    patientPanel.add(genderPanel, c);

    c.gridx = 0;
    c.gridy = 6;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel cityAddressLabel = new JLabel("City Address:");
    cityAddressLabel.setFont(poppinsBold);
    patientPanel.add(cityAddressLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField cityAddressField = new JTextField(20);
    patientPanel.add(cityAddressField, c);

    c.gridx = 0;
    c.gridy = 7;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel streetAddressLabel = new JLabel("Street Address:");
    streetAddressLabel.setFont(poppinsBold);
    patientPanel.add(streetAddressLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField streetAddressField = new JTextField(20);
    patientPanel.add(streetAddressField, c);

    c.gridx = 0;
    c.gridy = 8;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel zipCodeLabel = new JLabel("Zip Code:");
    zipCodeLabel.setFont(poppinsBold);
    patientPanel.add(zipCodeLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField zipCodeField = new JTextField(20);
    patientPanel.add(zipCodeField, c);

    c.gridx = 0;
    c.gridy = 9;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel emailLabel = new JLabel("Email:");
    emailLabel.setFont(poppinsBold);
    patientPanel.add(emailLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField emailField = new JTextField(20);
    patientPanel.add(emailField, c);

    c.gridx = 0;
    c.gridy = 10;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel phoneNumLabel = new JLabel("Phone Number:");
    phoneNumLabel.setFont(poppinsBold);
    patientPanel.add(phoneNumLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField phoneNumField = new JTextField(20);
    patientPanel.add(phoneNumField, c);

    c.gridx = 0;
    c.gridy = 11;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel fContactLabel = new JLabel("Contact 1 Name:");
    fContactLabel.setFont(poppinsBold);
    patientPanel.add(fContactLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField fContactField = new JTextField(20);
    patientPanel.add(fContactField, c);

    c.gridx = 0;
    c.gridy = 12;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel fContactPhoneLabel = new JLabel("Contact 1 Phone Number:");
    fContactPhoneLabel.setFont(poppinsBold);
    patientPanel.add(fContactPhoneLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField fContactPhoneField = new JTextField(20);
    patientPanel.add(fContactPhoneField, c);

    c.gridx = 0;
    c.gridy = 13;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel sContactLabel = new JLabel("Contact 2 Name:");
    sContactLabel.setFont(poppinsBold);
    patientPanel.add(sContactLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField sContactField = new JTextField(20);
    patientPanel.add(sContactField, c);

    c.gridx = 0;
    c.gridy = 14;
    c.insets = new Insets(10, 50, 10, 0);
    JLabel sContactPhoneLabel = new JLabel("Contact 2 Phone Number:");
    sContactPhoneLabel.setFont(poppinsBold);
    patientPanel.add(sContactPhoneLabel, c);

    c.gridx = 1;
    c.insets = new Insets(10, 0, 10, 0);
    JTextField sContactPhoneField = new JTextField(20);
    patientPanel.add(sContactPhoneField, c);

    c.gridx = 0;
    c.gridy = 15;
    c.gridwidth = 4;
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(10, 50, 10, 1125);
    JButton saveButton = new JButton("Add");
    saveButton.setFont(poppinsBold);
    patientPanel.add(saveButton, c);

    saveButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          // String idCardValue = IDCardField.getText();
          int idCardNumber = Integer.parseInt(IDCardField.getText());
          String fPatientName = FNameField.getText();
          String lPatientName = LNameField.getText();
          String month = String.valueOf(monthComboBox.getSelectedIndex() + 1);
          int day = (int) dayComboBox.getSelectedItem();
          int year = (int) yearComboBox.getSelectedItem();
          String gender;
          if (maleButton.isSelected()) {
            gender = "M";
          } else if (femaleButton.isSelected()) {
            gender = "F";
          } else {
            gender = "";
          }
          String cAddress = cityAddressField.getText();
          String sAddress = streetAddressField.getText();
          int zCode = Integer.valueOf(zipCodeField.getText());
          String email = emailField.getText();
          int pNum = Integer.valueOf(phoneNumField.getText());
          String fContactName = fContactField.getText();
          int fContactPhone = Integer.valueOf(fContactPhoneField.getText());
          String sContactName = sContactField.getText();
          int sContactPhone = Integer.valueOf(sContactPhoneField.getText());
          String dateofbirth = month + "-"+ day +"-"+ year;

          
          if(!fPatientName.isBlank() && !lPatientName.isBlank() && !dateofbirth.isBlank() && !gender.isBlank() && !cAddress.isBlank() && !sAddress.isBlank() && !email.isBlank() && !fContactName.isBlank() && !sContactName.isBlank()){
            if(idCardNumber > 0 && zCode > 0 && pNum > 0 && fContactPhone >0 && sContactPhone >0){
              try {
                servController.servAddPatient(idCardNumber, fPatientName, lPatientName, dateofbirth , gender, cAddress, sAddress , zCode, email, pNum, fContactName, fContactPhone, sContactName, sContactPhone);
              } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              }
              JOptionPane.showMessageDialog(
                    patientPanel,
                    "Successfully adding patient",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
              JOptionPane.showMessageDialog(
                    patientPanel,
                    "Fill out the form!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
          } else {
            JOptionPane.showMessageDialog(
                    patientPanel,
                    "Fill out the form!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
          }
          
          // Object[] rowData = {
          //   idCardNumber,
          //   fPatientName,
          //   lPatientName,
          //   month + " " + day + ", " + year,
          //   gender,
          //   cAddress,
          //   zCode,
          //   email,
          //   pNum,
          //   fContactName,
          //   fContactPhone,
          //   sContactName,
          //   sContactPhone,
          // };
          // tableModel.addRow(rowData);
        }
      }
    );
    monthComboBox.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          updateDayComboBox();
        }
      }
    );
    yearComboBox.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          updateDayComboBox();
        }
      }
    );

    updateDayComboBox();
    return patientPanel;
  }

  private void updateDayComboBox() {
    int monthIndex = monthComboBox.getSelectedIndex();
    int year = (int) yearComboBox.getSelectedItem();
    int daysInMonth;
    switch (monthIndex) {
      case 1:
        daysInMonth =
          (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
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
      days[i - 1] = i;
    }
    DefaultComboBoxModel<Integer> dayModel = new DefaultComboBoxModel<>(days);
    dayComboBox.setModel(dayModel);
  }

  public JPanel createListPatientPanel() {
    try {
      servController.listAllPatient();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    JPanel patient = new JPanel();
    patient.setBackground(new Color(235, 216, 200));
    patient.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    JLabel titleLabel = new JLabel("Hey Admin " + String.valueOf(user[3]) + " " + String.valueOf(user[4])+"," , JLabel.CENTER);
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
    titleLabel.setForeground(new Color(19, 117, 118));
    c.gridy = 0;
    c.insets = new Insets(20, 0, 0, 0);
    patient.add(titleLabel, c);

    JLabel chooseLabel = new JLabel(
      "Here are the list of patient...",
      JLabel.CENTER
    );
    chooseLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
    chooseLabel.setForeground(Color.BLACK);
    c.gridy = 1;
    c.insets = new Insets(0, 0, 50, 0);
    patient.add(chooseLabel, c);

    // String[] columnNames = patientcol;
    // Object[][] data = AdminMenu.patient;

    DefaultTableModel tableModel = new DefaultTableModel(AdminMenu.patient, patientcol);
    JTable table = new JTable(tableModel);

    JScrollPane tableScrollPane = new JScrollPane(table);
    c.gridy = 2;
    c.insets = new Insets(0, 50, 0, 50);
    c.weightx = 1.0;
    c.weighty = 1.0;
    patient.add(tableScrollPane, c);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(235, 216, 200));
    c.gridy = 3;
    c.insets = new Insets(50, 0, 10, 0);
    c.weightx = 1.0;

    JButton editButton = new JButton("Delete Account");
    editButton.setFont(new Font("Poppins", Font.BOLD, 18));
    editButton.setForeground(Color.WHITE);
    editButton.setBackground(Color.BLACK);
    // buttonPanel.add(editButton);
    patient.add(buttonPanel, c);

    editButton.addActionListener(new ActionListener() {
      boolean isEditing = false;
  
      @Override
      public void actionPerformed(ActionEvent e) {
          int selectedRow = table.getSelectedRow();
          if (selectedRow != -1) {
            
              int confirmResult = JOptionPane.showConfirmDialog(
                      patient,
                      "Are you sure you want to delete this account?",
                      "Confirmation",
                      JOptionPane.YES_NO_OPTION);
              if (confirmResult == JOptionPane.YES_OPTION) {
                try {
                  servController.deleteCredential((int)table.getModel().getValueAt(table.getSelectedRow(), 0));
                } catch (SQLException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
                }
                  DefaultTableModel model = (DefaultTableModel) table.getModel();
                  model.removeRow(selectedRow);
              }
          }
      }
  });

    patient.addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = patient.getWidth();
          int height = patient.getHeight();

          float fontSize = 30.0f * Math.min(width, height) / 1000.0f;
          buttonPanel.setFont(buttonPanel.getFont().deriveFont(fontSize));

          fontSize = 45.0f * Math.min(width, height) / 1000.0f;
          titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));
        }
      }
    );

    return patient;
  }

}
