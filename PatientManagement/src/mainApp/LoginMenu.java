package mainApp;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class LoginMenu extends javax.swing.JFrame {
static Object[] login;
  public LoginMenu() {
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(new Color(235, 216, 200));

    Dimension topPanelDimension = new Dimension(800, 50);
    JPanel topPanel = new JPanel();
    topPanel.setPreferredSize(topPanelDimension);
    topPanel.setBackground(new Color(19, 117, 118));

    Dimension titlePanelDimension = new Dimension(800, 100);
    JPanel titlePanel = new JPanel();
    titlePanel.setPreferredSize(titlePanelDimension);
    mainPanel.setBackground(new Color(235, 216, 200));

    JLabel titleLabel = new JLabel("Clinic Checkup Management System");
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 68));
    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    titleLabel.setVerticalAlignment(JLabel.CENTER);

    JLabel usernameLabel = new JLabel("Username:");
    usernameLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
    JTextField usernameField = new JTextField(24);

    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
    JPasswordField passwordField = new JPasswordField(12);

    JButton signIn = new JButton("Log In");
    signIn.setBackground(Color.BLACK);
    signIn.setForeground(Color.WHITE);

    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(mainPanel.getBackground());

    buttonPanel.add(signIn);
    titlePanel.add(titleLabel);

    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1920, 1080);
    frame.setLayout(new BorderLayout());
    frame.getContentPane().setBackground(new Color(255, 250, 250));
    frame.add(mainPanel, BorderLayout.CENTER);
    frame.add(topPanel, BorderLayout.NORTH);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int centerX = (int) screenSize.getWidth() / 2;
    int centerY = (int) screenSize.getHeight() / 2;
    frame.setLocation(
      centerX - frame.getSize().width / 2,
      centerY - frame.getSize().height / 2
    );

    frame.addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = frame.getWidth();
          int height = frame.getHeight();
          int insetX = (int) (width * 0.4);
          int insetY = (int) (height * 0.05);
          float fontSize = 45.0f * Math.min(width, height) / 1000.0f;
          titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));

          c.insets = new Insets(insetY, insetX, insetY, insetX);

          mainPanel.removeAll();
          mainPanel.setLayout(new GridBagLayout());

          c.gridy = 0;
          c.insets = new Insets(0, 20, insetY, 20);
          mainPanel.add(titleLabel, c);

          c.gridy = 1;
          c.insets = new Insets(0, insetX, 5, insetX);
          mainPanel.add(usernameLabel, c);

          c.gridy = 2;
          c.insets = new Insets(0, insetX, 0, insetX);
          mainPanel.add(usernameField, c);

          c.gridy = 3;
          c.insets = new Insets(10, insetX, 5, insetX);
          mainPanel.add(passwordLabel, c);

          c.gridy = 4;
          c.insets = new Insets(0, insetX, 20, insetX);
          mainPanel.add(passwordField, c);

          c.gridy = 5;
          c.weightx = 1.0;
          c.anchor = GridBagConstraints.CENTER;
          c.insets = new Insets(0, 20, insetY, 20);
          mainPanel.add(buttonPanel, c);

          if (width < 896) {
            fontSize = 45.0f * Math.min(width, height) / 1300.0f;
            titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));
            insetX = (int) (width * 0.3);

            c.gridy = 1;
            c.insets = new Insets(0, insetX, 2, insetX);
            mainPanel.add(usernameLabel, c);

            c.gridy = 2;
            c.insets = new Insets(0, insetX, 0, insetX);
            mainPanel.add(usernameField, c);

            c.gridy = 3;
            c.insets = new Insets(6, insetX, 2, insetX);
            mainPanel.add(passwordLabel, c);

            c.gridy = 4;
            c.insets = new Insets(0, insetX, 15, insetX);
            mainPanel.add(passwordField, c);
          }

          mainPanel.revalidate();
          mainPanel.repaint();
        }
      }
    );

    signIn.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int user = Integer.parseInt(usernameField.getText());
            String passwordPlaceholder = String.valueOf(passwordField.getPassword());
            try {
                login = servController.getLoginCred(user, passwordPlaceholder);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if ((login[0] != null) && (login[1] != null)){
                if ((user == (int)(login[0])) && (passwordPlaceholder.equals(String.valueOf(login[1])))) 
                {
                      try {
                          servController.getUser((int)(login[0]));
                      } catch (SQLException e1) {
                          // TODO Auto-generated catch block
                          e1.printStackTrace();
                      }
                      if ((int)(DoctorMenu.user[1]) == 2){
                      DoctorMenu docmenu = new DoctorMenu();
                      frame.dispose();
                      } else if ((int)(DoctorMenu.user[1]) == 1){
                      AdminMenu adminmenu = new AdminMenu();
                      frame.dispose();
                      }
                } 
            } else {
                    
                JOptionPane.showMessageDialog(
                    frame,
                    "Invalid username or password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            
        }
      }
    );

    frame.setVisible(true);
  }
}
