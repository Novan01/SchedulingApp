import java.awt.*;
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

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,1,10,10)); 
        formPanel.add(lblLogin);
        formPanel.add(lblUsername);
        formPanel.add(tfUser);
        formPanel.add(lblPassword);
        formPanel.add(pfPassword);

        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
}
