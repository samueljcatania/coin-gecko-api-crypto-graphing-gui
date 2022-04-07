import com.google.gson.*;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * Login class shows a UI that takes user input for their username and password. If credentials are valid, then
 * MainUI is launched. Otherwise, the program exits.
 *
 * @author Samuel Catania
 */

public class Login {
    private final JTextField usernameField;
    private final JTextField passwordField;
    private final JFrame loginFrame;

    /**
     * Constructor for Login, which takes and checks user credentials by creating and adding necessary Swing
     * components to a JFrame, including text and password fields, and a Submit button.
     */
    public Login() {
        //Setup small centered frame for the username and password fields
        loginFrame = new JFrame("Login");
        loginFrame.setSize(350, 125);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a new JPanel to add functionality to
        JPanel panel = new JPanel();

        //Create fields
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        //Create labels for fields
        JLabel username = new JLabel("Username:");
        JLabel password = new JLabel("Password:");

        //Create submit button
        JButton submit = new JButton("Submit");

        //Add ActionListener to button for button presses, call helper method upon button press
        submit.addActionListener(l -> {
            checkPassword();
        });

        //Add the username field to the JPanel with the correct label
        panel.add(username);
        username.setLabelFor(usernameField);
        panel.add(usernameField);

        //Add the password field to the JPanel with the correct label
        panel.add(password);
        password.setLabelFor(passwordField);
        panel.add(passwordField);

        //Add the button to the JPanel
        panel.add(submit);

        //Display the window
        loginFrame.setContentPane(panel);
        loginFrame.setVisible(true);
    }

    /**
     * Helper method to Login constructor is called whenever the Submit button is pressed by the user. This method
     * checks the entered username and password against valid credentials and either instantiates a new instance
     * of the MainUI if the user entered correct credentials or terminates the program.
     */
    private void checkPassword() {
        //Get input for username and password
        String username = usernameField.getText();
        String password = passwordField.getText();

        //Track if a valid username and password was entered
        boolean validPassword = false;

        JsonParser jsonParser = new JsonParser();

        //Open the credentials.json file to parse
        try (FileReader reader = new FileReader("src/main/text/credentials.json")) {

            Object object = jsonParser.parse(reader);

            //Open as a JsonArray
            JsonArray jsonArray = (JsonArray) object;

            for (int i = 0; i < jsonArray.size(); i++) {

                //Set the current JsonArray index to a JsonObject to parse through
                JsonObject jsonObject = (JsonObject) jsonArray.get(i);

                //If both username and password exist in one index, correct credentials have been entered (case-sensitive)
                if (jsonObject.get("username").getAsString().equals(username) && jsonObject.get("password").getAsString().equals(password)) {
                    validPassword = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //If username and password are valid
        if (validPassword) {

            //Hide the login GUI
            usernameField.setText("");
            passwordField.setText("");
            loginFrame.setVisible(false);

            //Call new instance of MainUI GUI and show it
            JFrame mainUIFrame = MainUI.getInstance();
            mainUIFrame.setSize(900, 600);
            mainUIFrame.pack();
            mainUIFrame.setLocationRelativeTo(null);
            mainUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainUIFrame.setVisible(true);

        } else { //Otherwise, terminate program
            JOptionPane.showMessageDialog(loginFrame, "Invalid Username or Password. The application will now terminate.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Main is the entrypoint to the LemonMist cryptotrading program. It first asks for credentials,
     * and if they are valid, then it launches MainUI.
     *
     * @param args command line arguments (none needed to launch the program normally.)
     */
    public static void main(String[] args) {
        new Login();
    }
}
