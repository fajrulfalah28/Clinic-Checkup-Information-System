package mainApp;

import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DoctorMenu extends javax.swing.JFrame {

    private JTabbedPane tabbedPane;
    private List<LocalDateTime> dates;
    private JLabel scheduleLabel;

    public DoctorMenu() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screenSize);
        setUndecorated(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(19, 117, 118));
    
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
            JFrame DoctorMenu = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            DoctorMenu.dispose();

            // LoginMenu loginMenu = new LoginMenu();   <------ ini gausah soalnya kalo ada begini jadi ada 2 windows
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

        JLabel titleLabel = new JLabel("Welcome Doctor Fajrul!", JLabel.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
        titleLabel.setForeground(new Color(19, 117, 118));
        GridBagConstraints c = new GridBagConstraints();

        JLabel chooseLabel = new JLabel("Choose where you want to go", JLabel.CENTER);
        chooseLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        chooseLabel.setForeground(Color.BLACK);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(235, 216, 200));
        buttonPanel.setLayout(new GridLayout(0, 2, 50, 50));

        JButton appointmentButton = new JButton("Appointment");
        appointmentButton.setFont(new Font("Poppins", Font.BOLD, 18));
        appointmentButton.setForeground(Color.WHITE);
        appointmentButton.setBackground(Color.BLACK);
        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        buttonPanel.add(appointmentButton);

        JButton patientButton = new JButton("Patient");
        patientButton.setFont(new Font("Poppins", Font.BOLD, 18));
        patientButton.setForeground(Color.WHITE);
        patientButton.setBackground(Color.BLACK);
        patientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(2);
            }
        });

        buttonPanel.add(patientButton);
        
        homePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = homePanel.getWidth();
                int height = homePanel.getHeight();
                int buttonWidth = (int)(width * 0.15);
                int buttonHeight = (int)(height * 0.05);

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
        });

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
    
        JLabel titleLabel = new JLabel("Hey doctor Fajrul,", JLabel.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
        titleLabel.setForeground(new Color(19, 117, 118));
        c.gridy = 0;
        c.insets = new Insets(20, 0, 0, 0);
        appointmentPanel.add(titleLabel, c);

        JLabel chooseLabel = new JLabel("Here are your appointment schedule...", JLabel.CENTER);
        chooseLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        chooseLabel.setForeground(Color.BLACK);
        c.gridy = 1;
        c.insets = new Insets(0, 0, 20, 0);
        appointmentPanel.add(chooseLabel, c);
        
        JLabel shiftLabel = new JLabel("Your current shift is: 08:00 - 17:00", JLabel.CENTER);
        shiftLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        shiftLabel.setForeground(new Color(19, 117, 118));     
        c.gridy = 2;
        c.insets = new Insets(0, 0, 30, 0);
        appointmentPanel.add(shiftLabel, c); 

        Timer timer = new Timer(60000, e -> updateScheduleLabel());
        timer.start();
          
        String[] columnNames = {"Date", "Time", "Patient Name"};
        Object[][] data = {
            {"May 3, 2023", "10:00 AM", "John Doe"},
            {"May 4, 2023", "2:30 PM", "Jane Smith"},
            {"May 5, 2023", "11:15 AM", "Bob Johnson"}
        };

        dates = new ArrayList<>();

        for (Object[] row : data) {
            String dateStr = (String) row[0];
            String timeStr = (String) row[1];
            String dateTimeStr = dateStr + " " + timeStr;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            dates.add(dateTime);
        }

        LocalDateTime closestDateTime = Collections.min(dates, LocalDateTime::compareTo);

        Duration duration = Duration.between(LocalDateTime.now(), closestDateTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        
        JTable table = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);
        c.gridy = 3;
        c.insets = new Insets(0, 50, 0, 50);
        c.weightx = 1.0;
        c.weighty = 1.0;
        appointmentPanel.add(tableScrollPane, c);

        String labelStr = String.format("Your next appointment will be held in %d hours and %d minutes", hours, minutes);
        scheduleLabel = new JLabel(labelStr, JLabel.CENTER);
        scheduleLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        scheduleLabel.setForeground(new Color(19, 117, 118));
        c.gridy = 4;
        c.insets = new Insets(0, 0, 0, 0);
        appointmentPanel.add(scheduleLabel, c);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(235, 216, 200));
        c.gridy = 5;
        c.insets = new Insets(0, 0, 10, 0);
        c.weightx = 1.0;
    
        JButton addButton = new JButton("Change Shift");
        addButton.setFont(new Font("Poppins", Font.BOLD, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLACK);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShiftChange shiftChange = new ShiftChange(shiftLabel);
                shiftChange.setVisible(true);
            }
        });
        buttonPanel.add(addButton);
    
        appointmentPanel.addComponentListener(new ComponentAdapter() {
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
        });
    
        return appointmentPanel;
    }

    private void updateScheduleLabel() {
        LocalDateTime closestDateTime = Collections.min(dates, LocalDateTime::compareTo);

        Duration duration = Duration.between(LocalDateTime.now(), closestDateTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        String labelStr = String.format("Your next appointment will be held in %d hours and %d minutes", hours, minutes);
        scheduleLabel.setText(labelStr);
    }
    
    public JPanel createPatientPanel() {
        JPanel patientPanel = new JPanel();
        patientPanel.setBackground(new Color(235, 216, 200));
        patientPanel.setLayout(new GridBagLayout());

        patientPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
    
        JLabel titleLabel = new JLabel("Hey doctor Fajrul,", JLabel.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 50));
        titleLabel.setForeground(new Color(19, 117, 118));
        c.gridy = 0;
        c.insets = new Insets(20, 0, 0, 0);
        patientPanel.add(titleLabel, c);

        JLabel chooseLabel = new JLabel("Here are your patient list", JLabel.CENTER);
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
        searchButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           // buat search
        }
        });
        searchPanel.add(searchButton, BorderLayout.EAST);
        patientPanel.add(searchPanel, c);
    
        String[] columnNames = {"Patient Name", "Systolic", "Diastolic", "Heart Rate", "Body Temperature", "Body Height", "Body Weight", "Doctor Notes"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);;
        
        JScrollPane tableScrollPane = new JScrollPane(table);
        c.gridy = 3;
        c.insets = new Insets(0, 50, 0, 50);
        c.weightx = 1.0;
        c.weighty = 1.0;
        patientPanel.add(tableScrollPane, c);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(235, 216, 200));
        c.gridy = 4;
        c.insets = new Insets(30, 0, 10, 0);
        c.weightx = 1.0;
    
        JButton addButton = new JButton("Add New Medical Record");
        addButton.setFont(new Font("Poppins", Font.BOLD, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLACK);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRecord addRecord = new AddRecord(table);
                addRecord.AddRecord();
            }
        });
        buttonPanel.add(addButton);
    
        patientPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = patientPanel.getWidth();
                int height = patientPanel.getHeight();
                
                float fontSize = 30.0f * Math.min(width, height) / 1000.0f;
                buttonPanel.setFont(buttonPanel.getFont().deriveFont(fontSize));
                patientPanel.add(buttonPanel, c);
        
                fontSize = 45.0f * Math.min(width, height) / 1000.0f;
                titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));
        
                fontSize = 27.0f * Math.min(width, height) / 1000.0f;
                chooseLabel.setFont(chooseLabel.getFont().deriveFont(fontSize));
            }
        });
    
        return patientPanel;
    }
    
    
}