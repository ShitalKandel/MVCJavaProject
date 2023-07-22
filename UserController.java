import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class UserController {
    private List<User> users;
    private FileWriter fileWriter;
    private LoginFrame loginFrame;
    private WelcomeFrame welcomeFrame;
    private RegistrationFrame registrationFrame;

    public UserController() {
        users = new ArrayList<>();
        try {
            // Initialize file writer to append data to a file
            fileWriter = new FileWriter("user_data.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerUser(User newUser) {
        // Hash the password
        String hashedPassword = hashPassword(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        // Store the user in the users list
        users.add(newUser);

        // Persist the user data to the file
        try {
            fileWriter.write(newUser.getFirstName() + "," +
                    newUser.getLastName() + "," +
                    newUser.getEmail() + "," +
                    newUser.getPassword() + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User loginUser(String username, String password) {
        // Implement code to authenticate the user
        String hashedPassword = hashPassword(password);
        for (User user : users) {
            if (user.getFirstName().equals(username) && user.getPassword().equals(hashedPassword)) {
                return user;
            }
        }
        return null;
    }
    public void showRegistrationFrame() {
        registrationFrame = new RegistrationFrame(this);
        registrationFrame.setVisible(true);
    }



    public void showLoginFrame() {
        if (loginFrame != null) {
            loginFrame.dispose();
        }
        loginFrame = new LoginFrame(this);
        loginFrame.setVisible(true);
    }

    public void showWelcomeFrame(User user) {
        if (welcomeFrame != null) {
            welcomeFrame.dispose();
        }
        welcomeFrame = new WelcomeFrame(user, this);
        welcomeFrame.setVisible(true);
    }
    public void closeFileWriter() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}