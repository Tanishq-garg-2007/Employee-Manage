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

    private final Color COLOR_DARK_BG = new Color(30, 41, 59); 
    private final Color COLOR_CARD_BG = new Color(42, 59, 80, 200); 
    private final Color COLOR_ACCENT = new Color(94, 234, 212); 
    private final Font FONT_LABEL = new Font("Inter", Font.PLAIN, 16);
    private final Font FONT_BUTTON = new Font("Poppins", Font.BOLD, 14);

    Login(){
        setTitle("Employee Management System - Login");
        getContentPane().setBackground(COLOR_DARK_BG); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(COLOR_CARD_BG);
        cardPanel.setBounds(100, 30, 400, 240); 
        cardPanel.setBorder(BorderFactory.createLineBorder(COLOR_ACCENT, 1));
        add(cardPanel);

        
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

        
        login = new JButton("LOGIN");
        login.setBounds(200,160,150,40);
        login.setBackground(COLOR_ACCENT);
        login.setForeground(COLOR_DARK_BG);
        login.setFont(FONT_BUTTON);
        login.setFocusPainted(false);
        login.setBorder(BorderFactory.createLineBorder(COLOR_ACCENT, 1));
        login.addActionListener(this);
        cardPanel.add(login);

        
        back = new JButton("BACK");
        back.setBounds(40,160,150,40);
        back.setBackground(COLOR_CARD_BG.darker());
        back.setForeground(COLOR_ACCENT);
        back.setFont(FONT_BUTTON);
        back.setFocusPainted(false);
        back.setBorder(BorderFactory.createLineBorder(COLOR_ACCENT, 1));
        back.addActionListener(this);
        cardPanel.add(back);

        
        setSize(600,350); 
        setLocationRelativeTo(null); 
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            try {
                
                String username = tusername.getText();
                
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
            System.exit(0); 
        }
    }

    public static void main(String[] args) {
        new Login();
    }

}
