package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField tusername;
    JPasswordField tpassword;
    JButton login, back;

    // --- Modern UI Colors & Fonts ---
    private final Color COLOR_DARK_BG = new Color(30, 41, 59); // Deep Navy/Slate
    private final Color COLOR_CARD_BG = new Color(42, 59, 80, 200); // Translucent Card
    private final Color COLOR_ACCENT = new Color(94, 234, 212); // Vibrant Cyan/Mint
    private final Font FONT_LABEL = new Font("Inter", Font.PLAIN, 16);
    private final Font FONT_BUTTON = new Font("Poppins", Font.BOLD, 14);

    Login(){
        setTitle("Employee Management System - Login");
        getContentPane().setBackground(COLOR_DARK_BG); // Main BG
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Use a central JPanel for the "Glassmorphic" Card Effect
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(COLOR_CARD_BG);
        cardPanel.setBounds(100, 30, 400, 240); // Centered Card
        cardPanel.setBorder(BorderFactory.createLineBorder(COLOR_ACCENT, 1));
        add(cardPanel);

        // 1. Username Label and Field
        JLabel username = new JLabel("USERNAME");
        username.setBounds(40,40,100,30);
        username.setForeground(Color.WHITE);
        username.setFont(FONT_LABEL);
        cardPanel.add(username);

        tusername = new JTextField();
        tusername.setBounds(150,40,200,30);
        tusername.setBackground(COLOR_DARK_BG.darker());
        tusername.setForeground(Color.WHITE);
        tusername.setCaretColor(COLOR_ACCENT);
        tusername.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cardPanel.add(tusername);

        // 2. Password Label and Field
        JLabel password = new JLabel("PASSWORD");
        password.setBounds(40,90,100,30);
        password.setForeground(Color.WHITE);
        password.setFont(FONT_LABEL);
        cardPanel.add(password);

        tpassword = new JPasswordField();
        tpassword.setBounds(150,90,200,30);
        tpassword.setBackground(COLOR_DARK_BG.darker());
        tpassword.setForeground(Color.WHITE);
        tpassword.setCaretColor(COLOR_ACCENT);
        tpassword.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cardPanel.add(tpassword);

        // 3. Login Button (Primary Action)
        login = new JButton("LOGIN");
        login.setBounds(200,160,150,40);
        login.setBackground(COLOR_ACCENT);
        login.setForeground(COLOR_DARK_BG);
        login.setFont(FONT_BUTTON);
        login.setFocusPainted(false);
        login.setBorder(BorderFactory.createLineBorder(COLOR_ACCENT, 1));
        login.addActionListener(this);
        cardPanel.add(login);

        // 4. Back Button (Secondary/Ghost Action)
        back = new JButton("BACK");
        back.setBounds(40,160,150,40);
        back.setBackground(COLOR_CARD_BG.darker());
        back.setForeground(COLOR_ACCENT);
        back.setFont(FONT_BUTTON);
        back.setFocusPainted(false);
        back.setBorder(BorderFactory.createLineBorder(COLOR_ACCENT, 1));
        back.addActionListener(this);
        cardPanel.add(back);

        // Removed all image logic for a clean, minimal design

        setSize(600,350); // Increased height slightly for better spacing
        setLocationRelativeTo(null); // Center the window
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            try {
                // Existing login logic remains...
                String username = tusername.getText();
                // Note: getPassword() is preferred for security, but getText() is used to maintain original logic
                String password = new String(tpassword.getPassword());

                conn conn = new conn();
                String query = "select * from login where username = '"+ username +"' and password = '"+password+"'";
                ResultSet resultSet = conn.statement.executeQuery(query);
                if (resultSet.next()){
                    setVisible(false);
                    new Main_class();
                }else {
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }

            }catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource() == back) {
            System.exit(0); // Standard exit code
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}