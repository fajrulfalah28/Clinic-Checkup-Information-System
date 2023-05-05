package mainApp;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class AdminMenu extends javax.swing.JFrame {

  private JTabbedPane tabbedPane;
  private JTable table;
  static Object[] user;
  public static Object[][] dataSchedule;
  public static String[] dataColumnName;
  public static Object[][] DiagnosisTable;
  public static String[] diagnosisColumn;

  public AdminMenu() {
    try {
      servController.getDataScheduleAdmin();
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
    tabbedPane.setBackground(Color.BLACK);
    tabbedPane.setForegroundAt(0, Color.WHITE);
    tabbedPane.setForegroundAt(1, Color.WHITE);
    tabbedPane.setForegroundAt(2, Color.WHITE);
    tabbedPane.setForegroundAt(3, Color.WHITE);
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
            Object date = table.getValueAt(row, 3);
            Object time = table.getValueAt(row, 4);
            Object patientName = table.getValueAt(row, 2);
            Object doctor = table.getValueAt(row, 1);
            EditAppointmentFrame editFrame = new EditAppointmentFrame(
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

    String[] columnNames = {
      "MRID",
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
      "Action Status"
    };
    Object[][] data = {
      {"1", "Budi", "Aisyah", 1, 1, 1, 1.0, 1, "D-123", "Rusdi", null, null}
    };

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

    JLabel titleLabel = new JLabel("Hey Admin Fajrul,", JLabel.CENTER);
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

    String[] columnNames = {
      "userID", "userPassword",
    };
    Object[][] data = {
      {1, "admin1_"},
      {2, "doctor1_"},
      {3, "nurse1_"}
    };

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
}
