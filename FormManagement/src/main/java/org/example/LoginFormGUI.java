package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFormGUI extends  Form{
    public LoginFormGUI() {
        super("Login");
        addGuiComponents();

    }

    private void addGuiComponents(){

        //Creating login label
        JLabel loginLabel = new JLabel("Login");

        // Configuring component's x, y position and width/height values relative to the GUI
        loginLabel.setBounds(0,25,520,100);

        // Changing font color
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);

        // Changing the font size
        loginLabel.setFont(new Font("Dialog", Font.BOLD,40));

        // Centering text
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding component to GUI
        add(loginLabel);

        // Creating username label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30,150,400,25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN,18));

        // Creating username text field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30,185,450,55);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN,24));

        add(usernameField);
        add(usernameLabel);


        // Creating password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30,335,400,25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,18));

        // Creating password text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30,365,450,55);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN,24));

        add(passwordField);
        add(passwordLabel);

        // Creating login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD,18));


        // Changing the cursor to hand when hover the button
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(CommonConstants.TEXT_COLOR);
        loginButton.setBounds(125,520,250,50);
        add(loginButton);


        // Creating register label (used to load register GUI)
        JLabel registerLabel = new JLabel("Not a user? Register Here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);


        // Adding functionality so that when clicked it will launch the register form gui
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Dispose of this GUI
                LoginFormGUI.this.dispose();

                // Launch the register gui
                new RegisterFormGUI().setVisible(true);

            }
        });
        registerLabel.setBounds(125,600,250,30);
        add(registerLabel);
    }
}
