package GUI;


import API.UserHelp;
import util.I18n;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Objects;


public class Login extends BaseDialogUI
{

    public Login()
    {
        startLogin();
    }

    public void startLogin()
    {
        JFrame frame = new JFrame();
        frame.getContentPane().setPreferredSize(new Dimension(400, 400));
        frame.pack();
        frame.setTitle("Login");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        String filename = "/icons/car_rent_main.png";
        ImageIcon rawIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        frame.setIconImage(rawIcon.getImage());


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        JTextField userText = createTextField(50, 75, 300, 40);
        userText.setVisible(true);
        frame.add(userText);


        JPasswordField passText = createPasswordField(50, 195, 300, 40);
        passText.setVisible(true);
        frame.add(passText);


        JLabel loginSuccess = createLabel(null, 50, 50, 300 ,30);
        loginSuccess.setVisible(false);


        JButton loginButton = createButton("Login", 150, 280, 100, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = String.valueOf(passText.getPassword());

                int check = new UserHelp().checkCredentials(username,password);
                frame.add(loginSuccess);
                if(check==1)
                {
                    loginSuccess.setText(I18n.getString("login.success"));
                    loginSuccess.setBounds(120,240,180,30);
                    loginSuccess.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    loginSuccess.setForeground(Color.GREEN);
                    loginSuccess.setVisible(true);
                    frame.dispose();
                    password = null;
                    new MainApp(username);
                }
                else if(check==0)
                {
                    loginSuccess.setText(I18n.getString("login.password.wrong"));
                    loginSuccess.setBounds(125,240,180,30);
                    loginSuccess.setForeground(Color.RED);
                    loginSuccess.setVisible(true);
                    password = null;
                }
                else if(check==2)
                {
                    loginSuccess.setText(I18n.getString("login.user.notFound"));
                    loginSuccess.setBounds(90,240,220,30);
                    loginSuccess.setForeground(Color.RED);
                    loginSuccess.setVisible(true);
                    password = null;
                }
            }
        });
        loginButton.setVisible(true);
        frame.getRootPane().setDefaultButton(loginButton);
        frame.add(loginButton);



        JLabel userLabel = createLabel("Username", 50, 30, 200, 50);
        userLabel = addIconToLabel(userLabel, "/icons/user_login.png");
        userLabel.setVisible(true);
        frame.add(userLabel);



        JLabel passLabel = createLabel("Password", 55, 150, 200, 50);
        passLabel = addIconToLabel(passLabel, "/icons/pass_login.png");
        passLabel.setVisible(true);
        frame.add(passLabel);


        frame.setVisible(true);

    }
}
