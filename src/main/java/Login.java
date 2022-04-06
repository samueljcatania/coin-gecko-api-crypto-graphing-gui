import com.google.gson.*;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

public class Login {

    private final JTextField usernameField;
    private final JTextField passwordField;
    private final JFrame loginFrame;

    public Login() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(350, 125);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JLabel username = new JLabel("Username:");
        JLabel password = new JLabel("Password:");

        JButton submit = new JButton("Submit");

        submit.addActionListener(l -> {
            checkPassword();
        });

        panel.add(username);
        username.setLabelFor(usernameField);
        panel.add(usernameField);

        panel.add(password);
        password.setLabelFor(passwordField);
        panel.add(passwordField);

        panel.add(submit);

        //Display the window
        loginFrame.setContentPane(panel);
        loginFrame.setVisible(true);
    }

    private void checkPassword() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean validPassword = false;

        JsonParser jsonParser = new JsonParser();

        try (FileReader reader = new FileReader("src/main/text/credentials.json")) {

            Object object = jsonParser.parse(reader);

            JsonArray jsonArray = (JsonArray) object;

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = (JsonObject) jsonArray.get(i);

                if (jsonObject.get("username").getAsString().equals(username) && jsonObject.get("password").getAsString().equals(password)) {
                    validPassword = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (validPassword) {
            usernameField.setText("");
            passwordField.setText("");
            loginFrame.setVisible(false);

            JFrame mainUIFrame = MainUI.getInstance();
            mainUIFrame.setSize(900, 600);
            mainUIFrame.pack();
            mainUIFrame.setLocationRelativeTo(null);
            mainUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainUIFrame.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(loginFrame, "Invalid Username or Password. The application will now terminate.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
