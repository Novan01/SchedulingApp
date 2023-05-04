import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;


public class Login extends JFrame {
    final private Font mainFont = new Font("Arial", Font.BOLD, 18);
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField tfUser;
    JPasswordField pfPassword;

    public Login() {
        super("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
        setLocationRelativeTo(null);

        usernameLabel = new JLabel("Username");
        tfUser = new JTextField(20);
        usernameLabel.setFont(mainFont);

        passwordLabel = new JLabel("Password");
        pfPassword = new JPasswordField(20);
        passwordLabel.setFont(mainFont);

        //Button panel
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                String username = tfUser.getText();
                String password = String.valueOf(pfPassword.getPassword());

                boolean isAuth = getAuthenticatedUser(username, password);
            }
        });
        //Initialize the fram
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(5,5,5,5);
        add(usernameLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tfUser, gc);

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        add(pfPassword, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.CENTER;
        add(btnLogin, gc);

        pack();
        setVisible(true);
    }


    public boolean getAuthenticatedUser(String username, String password) {

        //url connection using jbdc to the sql database | doesnt work yet | no jdbc drivers set up
        final String DB_URL = "jdbc:mysql:\\192.168.21.14:3306\schedulingapp";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, username, password);
            // Connected to database successfully...

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Valid login");
                return true;
            }

            preparedStatement.close();
            conn.close();

        }catch(Exception e){
            System.out.println("Wrong username or password! Please try again");
        }

        return false;
    }
    
}
