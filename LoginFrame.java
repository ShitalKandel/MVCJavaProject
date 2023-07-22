import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame {
    private JTextField un;
    private JPasswordField getpswd;
    private JButton loginButton;
    private JButton signupButton;

    private UserController userController;

    public LoginFrame(UserController userController) {
        this.userController = userController;
        initComponents();
    }

    private void initComponents() {
        setTitle("Login");
        setSize(280, 280);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10, 20, 80, 25);
        add(usernameLabel);

        un = new JTextField(25);
        un.setBounds(110, 20, 150, 25);
        add(un);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 60, 80, 25);
        add(passwordLabel);

        getpswd = new JPasswordField(25);
        getpswd.setBounds(110, 60, 150, 25);
        add(getpswd);

        loginButton = new JButton("Login");
        loginButton.setBounds(40, 100, 100, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = un.getText();
                char[] passwordChars = getpswd.getPassword();
                String password = new String(passwordChars);

                // Check if the login credentials are valid
                User user = userController.loginUser(username, password);
                if (user != null) {
                    // Close the login frame and show the welcome frame
                    LoginFrame.this.setVisible(false);
                    userController.showWelcomeFrame(user);
                } else {
                    // Show an error message if the login is unsuccessful
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Invalid username or password!",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(loginButton);

        signupButton = new JButton("Sign up");
        signupButton.setBounds(150, 100, 100, 25);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the login frame and show the registration frame
                LoginFrame.this.setVisible(false);
                userController.showWelcomeFrame(null); // Pass null as the user parameter
            }
        });
        add(signupButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}