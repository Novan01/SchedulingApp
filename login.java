import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;


public class login extends JFrame {
    final private Font mainFont = new Font("Arial", Font.BOLD, 18);
    JTextField tfUser;
    JPasswordField pfPassword;

    public void initialize() {
        JLabel lblLogin = new JLabel("Login", SwingConstants.CENTER);
        lblLogin.setFont(mainFont);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(mainFont);

        tfUser = new JTextField();
        tfUser.setFont(mainFont);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(mainFont);

        //create form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,1,10,10));
        formPanel.add(lblLogin);
        formPanel.add(lblUsername);
        formPanel.add(tfUser);
        formPanel.add(lblPassword);
        formPanel.add(pfPassword);

        //Button panel
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                String username = tfUser.getText();
                String password = String.valueOf(pfPassword.getPassword());

                User user = getAuthenticatedUser(username, password);
            }
        });
        //Initialize the fram
        add(formPanel, BorderLayout.NORTH);

        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        //change this
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.username = resultSet.getString("username");
                user.password = resultSet.getString("password");
            }

            preparedStatement.close();
            conn.close();

        }catch(Exception e){
            System.out.println("Database connexion failed!");
        }


        return user;
    }
    
}
