import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

class RegistrationFrame extends JFrame {
    private JTextField fn;
    private JTextField ln;
    private JTextField em;
    private JPasswordField pswd;
    private JButton loginButton;
    private JButton signUpButton;
    private UserController userController;
    private FileWriter fileWriter;

    public RegistrationFrame(UserController userController) {
        this.userController = userController;
        try {
            // Initialize file writer to append data to a file
            fileWriter = new FileWriter("user_data.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    private void initComponents() {
        setTitle("Registration");
        setSize(280, 280);
        setLayout((LayoutManager) this);

        JLabel firstnameLabel = new JLabel("First Name");
        firstnameLabel.setBounds(10, 10, 80, 25);
        add(firstnameLabel);

        JLabel lastnameLabel = new JLabel("Last Name");
        lastnameLabel.setBounds(10, 50, 80, 25);
        add(lastnameLabel);

        fn = new JTextField();
        fn.setBounds(110, 10, 110, 25);
        add(fn);

        ln = new JTextField();
        ln.setBounds(110, 50, 110, 25);
        add(ln);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 90, 80, 25);
        add(emailLabel);

        em = new JTextField();
        em.setBounds(110, 90, 110, 25);
        add(em);

        JLabel paswdLabel = new JLabel("Password");
        paswdLabel.setBounds(10, 130, 80, 25);
        add(paswdLabel);

        pswd = new JPasswordField();
        pswd.setBounds(110, 130, 110, 25);
        add(pswd);

        loginButton = new JButton("Sign up");
        loginButton.setBounds(80, 170, 100, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = fn.getText();
                String lastName = ln.getText();
                String userEmail = em.getText();
                char[] passwordChars = pswd.getPassword();
                String password = new String(passwordChars);

                User newUser = new User(firstName, lastName, userEmail, password);
                userController.registerUser(newUser);

                // Close the registration frame and show the login frame
                RegistrationFrame.this.setVisible(false);
                userController.showLoginFrame();
            }
        });
        add(loginButton);

        signUpButton = new JButton("Sign up");
        signUpButton.setBounds(80, 210, 100, 25);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = fn.getText();
                String lastName = ln.getText();
                String userEmail = em.getText();
                char[] passwordChars = pswd.getPassword();
                String password = new String(passwordChars);

                // Create a new user object
                User newUser = new User(firstName, lastName, userEmail, password);
                // Register the user with the UserController
                userController.registerUser(newUser);

                // Clear the input fields
                fn.setText("");
                ln.setText("");
                em.setText("");
                pswd.setText("");
            }
        });
        add(signUpButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}