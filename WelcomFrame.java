import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WelcomeFrame extends JFrame {
    private JLabel welcomeLabel;
    private JButton logoutButton;

    private UserController userController;
    private User currentUser;

    public WelcomeFrame(User user, UserController userController) {
        this.currentUser = user;
        this.userController = userController;
        initComponents();
    }

    private void initComponents() {
        setTitle("Welcome");
        setSize(300, 150);
        setLayout(new FlowLayout());

        welcomeLabel = new JLabel("Welcome, " + currentUser.getFirstName() + "!");
        add(welcomeLabel);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the welcome frame and show the login frame
                WelcomeFrame.this.setVisible(false);
                userController.showLoginFrame();
            }
        });
        add(logoutButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}