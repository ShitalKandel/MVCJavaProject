import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserProfileFrame extends JFrame {
    private JLabel userProfileLabel;
    private JButton logoutFromProfileButton;

    private UserController userController;
    private User currentUser;

    public UserProfileFrame(User user, UserController userController) {
        this.currentUser = user;
        this.userController = userController;
        initComponents();
    }

    private void initComponents() {
        setTitle("User Profile");
        setSize(300, 150);
        setLayout(new FlowLayout());

        userProfileLabel = new JLabel("Welcome to your profile, " + currentUser.getFirstName() + "!");
        add(userProfileLabel);

        logoutFromProfileButton = new JButton("Logout");
        logoutFromProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the user profile frame and show the login frame
                UserProfileFrame.this.setVisible(false);
                userController.showLoginFrame();
            }
        });
        add(logoutFromProfileButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
