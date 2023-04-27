
package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginMenu extends javax.swing.JFrame {

    public LoginMenu() {
        
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(new Color(235, 216, 200));

    Dimension topPanelDimension = new Dimension(800, 50);
    JPanel topPanel = new JPanel();
    topPanel.setPreferredSize(topPanelDimension);
    topPanel.setBackground(new Color(19, 117, 118));

    JLabel titleLabel = new JLabel("Clinic Checkup Management System");
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 48));
    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    titleLabel.setVerticalAlignment(JLabel.CENTER);

    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(24);

    JLabel passwordLabel = new JLabel("Password:");
    JTextField passwordField = new JTextField(12);

    JButton signIn = new JButton("Sign In");
    signIn.setBackground(Color.BLACK);
    signIn.setForeground(Color.WHITE);

    JButton signUp = new JButton("Sign Up");
    signUp.setBackground(new Color(19, 117, 118));
    signUp.setForeground(Color.WHITE);

    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(0, 0, 10, 0); 
    mainPanel.add(titleLabel, c);

    c.gridy = 1;
    c.anchor = GridBagConstraints.CENTER;
    mainPanel.add(usernameLabel, c);

    c.gridy = 2;
    mainPanel.add(usernameField, c);

    c.gridy = 3;
    mainPanel.add(passwordLabel, c);

    c.gridy = 4;
    mainPanel.add(passwordField, c);

    c.gridy = 5;
    mainPanel.add(signIn, c);

    c.gridy = 6;
    mainPanel.add(signUp, c);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLayout(new BorderLayout());
    frame.getContentPane().setBackground(new Color(255, 250, 250));
    frame.add(mainPanel, BorderLayout.CENTER);
    frame.add(topPanel, BorderLayout.NORTH);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int centerX = (int) screenSize.getWidth() / 2;
    int centerY = (int) screenSize.getHeight() / 2;
    frame.setLocation(centerX - frame.getSize().width / 2, centerY - frame.getSize().height / 2);

    frame.setVisible(true);
    }

}