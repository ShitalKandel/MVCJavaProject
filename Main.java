import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserController userController = new UserController();
            userController.showLoginFrame();

        });
    }
}