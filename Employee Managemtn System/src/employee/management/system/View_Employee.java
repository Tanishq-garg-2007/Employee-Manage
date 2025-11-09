package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class View_Employee extends JFrame implements ActionListener {

    JTable table;
    Choice choiceEMP;
    JButton searchbtn, print, update, back;

    // --- Modern UI Colors & Fonts ---
    private final Color COLOR_DARK_BG = new Color(30, 41, 59);
    private final Color COLOR_CARD_BG = new Color(42, 59, 80);
    private final Color COLOR_ACCENT = new Color(94, 234, 212);
    private final Font FONT_LABEL = new Font("Inter", Font.PLAIN, 16);
    private final Font FONT_BUTTON = new Font("Poppins", Font.BOLD, 14);

    View_Employee(){
        setTitle("View Employees");
        getContentPane().setBackground(COLOR_DARK_BG);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // --- Search Bar and Filter ---
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(COLOR_CARD_BG);
        searchPanel.setBounds(0, 0, 900, 60);
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        add(searchPanel);

        JLabel search = new JLabel("Search by Employee ID:");
        search.setForeground(Color.WHITE);
        search.setFont(FONT_LABEL);
        searchPanel.add(search);

        choiceEMP = new Choice();
        choiceEMP.setBackground(COLOR_DARK_BG);
        choiceEMP.setForeground(COLOR_ACCENT);
        choiceEMP.setFont(FONT_LABEL);
        choiceEMP.setPreferredSize(new Dimension(150, 30));
        searchPanel.add(choiceEMP);

        // --- Action Buttons ---
        searchbtn = createModernButton("SEARCH", COLOR_ACCENT, COLOR_DARK_BG);
        searchbtn.addActionListener(this);
        searchPanel.add(searchbtn);

        print = createModernButton("PRINT", COLOR_CARD_BG.brighter(), Color.WHITE);
        print.addActionListener(this);
        searchPanel.add(print);

        update = createModernButton("UPDATE", COLOR_CARD_BG.brighter(), Color.WHITE);
        update.addActionListener(this);
        searchPanel.add(update);

        back = createModernButton("BACK", Color.RED.darker(), Color.WHITE);
        back.addActionListener(this);
        searchPanel.add(back);

        // Populate Choice box
        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            while (resultSet.next()){
                choiceEMP.add(resultSet.getString("empId"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // --- Table Styling ---
        table = new JTable();
        // Set table colors for dark theme
        table.setBackground(COLOR_DARK_BG.brighter());
        table.setForeground(Color.WHITE);
        table.setFont(FONT_LABEL);
        table.getTableHeader().setBackground(COLOR_CARD_BG);
        table.getTableHeader().setForeground(COLOR_ACCENT);
        table.getTableHeader().setFont(FONT_BUTTON);

        try{
            conn c= new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0,60,900,600); // Start below the search panel
        jp.getViewport().setBackground(COLOR_DARK_BG.brighter()); // Ensure viewport is also dark
        add(jp);

        setSize(900,700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Helper method to create a modern button
    private JButton createModernButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setFont(FONT_BUTTON);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchbtn){
            String query = "select * from employee where empId = '"+choiceEMP.getSelectedItem()+"'";
            try {
                conn c = new conn();
                ResultSet resultSet = c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == update){
            setVisible(false);
            new UpdateEmployee(choiceEMP.getSelectedItem());
        } else {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new View_Employee();
    }
}