import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {

    JTextField usernameField, passwordField;

    public static void main(String[] args){
         new Login();
    }

    public Login(){
        JFrame frame = new JFrame("Login");
        frame.setSize(350,125);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JLabel username = new JLabel("Username:");
        JLabel password = new JLabel("Password:");
        JButton submit = new JButton("Submit!");

        panel.add(username);
        username.setLabelFor(usernameField);
        panel.add(usernameField);

        panel.add(password);
        password.setLabelFor(passwordField);
        panel.add(passwordField);

        panel.add(submit);

        //Display
        frame.setContentPane(panel);
        frame.setVisible(true);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println(username);
        System.out.println(password);

    }


}
