package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_class extends JFrame {

    // --- Modern UI Colors & Fonts ---
    private final Color COLOR_DARK_BG = new Color(30, 41, 59); // Deep Navy/Slate
    private final Color COLOR_ACCENT = new Color(94, 234, 212); // Vibrant Cyan/Mint
    private final Color COLOR_CARD_BG = new Color(42, 59, 80); // Darker Card BG
    private final Font FONT_HEADING = new Font("Poppins", Font.BOLD, 32);
    private final Font FONT_BUTTON = new Font("Poppins", Font.BOLD, 16);

    Main_class(){
        setTitle("Employee Management System - Dashboard");
        getContentPane().setBackground(COLOR_DARK_BG);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Removed background image for a cleaner, minimal design
        // Use the main frame content pane as the container

        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(300, 50, 600, 40);
        heading.setFont(FONT_HEADING);
        heading.setForeground(Color.WHITE);
        add(heading);

        // Creating a panel for centered, grid-like button layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 40, 40)); // 2x2 grid with spacing
        buttonPanel.setBounds(300, 150, 520, 200);
        buttonPanel.setBackground(COLOR_DARK_BG);
        add(buttonPanel);

        // 1. Add Employee Button
        JButton add = createModernButton("ADD EMPLOYEE", COLOR_ACCENT, COLOR_DARK_BG);
        add.addActionListener(e -> {
            new AddEmployee();
            setVisible(false);
        });
        buttonPanel.add(add);

        // 2. View Employee Button
        JButton view = createModernButton("VIEW EMPLOYEE", COLOR_CARD_BG.brighter(), Color.WHITE);
        view.addActionListener(e -> {
            new View_Employee();
            setVisible(false);
        });
        buttonPanel.add(view);

        // 3. Remove Employee Button
        JButton rem = createModernButton("REMOVE EMPLOYEE", COLOR_CARD_BG.brighter(), Color.WHITE);
        rem.addActionListener(e -> new RemoveEmployee());
        buttonPanel.add(rem);

        // 4. Exit Button (New)
        JButton exit = createModernButton("EXIT SYSTEM", Color.RED.darker(), Color.WHITE);
        exit.addActionListener(e -> System.exit(0));
        buttonPanel.add(exit);


        setSize(1120,630);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    // Helper method to create a modern button
    private JButton createModernButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setFont(FONT_BUTTON);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        // Simulate rounded corners (best we can do in standard Swing)
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        new Main_class();
    }
}