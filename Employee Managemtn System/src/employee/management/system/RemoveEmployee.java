package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {
    Choice choiceEMPID;
    JButton delete, back;

    
    private final Color COLOR_DARK_BG = new Color(30, 41, 59);
    private final Color COLOR_CARD_BG = new Color(42, 59, 80);
    private final Color COLOR_ACCENT = new Color(94, 234, 212);
    private final Color COLOR_TEXT = Color.WHITE;
    private final Font FONT_LABEL = new Font("Inter", Font.BOLD, 15);
    private final Font FONT_DATA = new Font("Inter", Font.PLAIN, 15);
    private final Font FONT_BUTTON = new Font("Poppins", Font.BOLD, 16);

    
    private JButton createModernButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setFont(FONT_BUTTON);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(background, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    RemoveEmployee(){
        setTitle("Remove Employee");
        getContentPane().setBackground(COLOR_DARK_BG);

        
        JLabel label = new JLabel("Employee ID");
        label.setBounds(50,50,150,30);
        label.setFont(FONT_LABEL);
        label.setForeground(COLOR_TEXT);
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(250,50,150,30);
        choiceEMPID.setBackground(COLOR_CARD_BG);
        choiceEMPID.setForeground(COLOR_ACCENT);
        choiceEMPID.setFont(FONT_DATA);
        add(choiceEMPID);

        
        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            while (resultSet.next()){
                choiceEMPID.add(resultSet.getString("empId"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50,100,150,30);
        labelName.setFont(FONT_LABEL);
        labelName.setForeground(COLOR_TEXT);
        add(labelName);

        JLabel textName = new JLabel();
        textName.setBounds(250,100,200,30);
        textName.setFont(FONT_DATA);
        textName.setForeground(COLOR_ACCENT);
        add(textName);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50,150,150,30);
        labelPhone.setFont(FONT_LABEL);
        labelPhone.setForeground(COLOR_TEXT);
        add(labelPhone);

        JLabel textPhone = new JLabel();
        textPhone.setBounds(250,150,200,30);
        textPhone.setFont(FONT_DATA);
        textPhone.setForeground(COLOR_ACCENT);
        add(textPhone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50,200,150,30);
        labelemail.setFont(FONT_LABEL);
        labelemail.setForeground(COLOR_TEXT);
        add(labelemail);

        JLabel textEmail = new JLabel();
        textEmail.setBounds(250,200,200,30);
        textEmail.setFont(FONT_DATA);
        textEmail.setForeground(COLOR_ACCENT);
        add(textEmail);

        
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee where empId = '"+choiceEMPID.getSelectedItem()+"'");
            if (resultSet.next()){
                textName.setText(resultSet.getString("name"));
                textPhone.setText(resultSet.getString("phone"));
                textEmail.setText(resultSet.getString("email"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        
        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery("select * from employee where empId = '"+choiceEMPID.getSelectedItem()+"'");
                    if (resultSet.next()) {
                        textName.setText(resultSet.getString("name"));
                        textPhone.setText(resultSet.getString("phone"));
                        textEmail.setText(resultSet.getString("email"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        
        delete = createModernButton("DELETE", Color.RED.darker(), COLOR_TEXT);
        delete.setBounds(80,300,150,40);
        delete.addActionListener(this);
        add(delete);

        back = createModernButton("BACK", COLOR_CARD_BG.brighter(), COLOR_TEXT);
        back.setBounds(280,300,150,40);
        back.addActionListener(this);
        add(back);

        

        setSize(550,450); 
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==delete){
            try {
                conn c = new conn();
                String query = "delete from employee where empId = '"+choiceEMPID.getSelectedItem()+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Employee Deleted Sucessfully");
                setVisible(false);
                new Main_class();

            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }

}
