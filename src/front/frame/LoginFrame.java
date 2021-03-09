package front.frame;

import exceptions.ModelNotFoundException;
import front.frame.student.StudentMainFrame;
import front.frame.teacher.TeacherMainFrame;
import model.user.Role;
import model.user.User;
import service.LoginService;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JPanel contentPane;
    private JButton loginButton;
    private JTextField loginTextField;
    private JPasswordField passwordPasswordField;
    private final LoginService loginService = ServiceFactory.getInstance().getLoginService();

    public LoginFrame() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(loginButton);

        loginButton.addActionListener(e -> {
            authorizeUser();
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

    }

    private void authorizeUser() {
        String login = loginTextField.getText();
        String password = String.valueOf(passwordPasswordField.getPassword());
        User user = null;
        try {
            user = loginService.findUser(login, password);
        } catch (ModelNotFoundException exception) {
            showWrongCredentialsDialog();
            return;
        }
        if (user.getRole().equals(Role.TEACHER)) {
            dispose();
            new TeacherMainFrame().setVisible(true);
            return;
        }
        if (user.getRole().equals(Role.STUDENT)) {
            dispose();
            new StudentMainFrame(user).setVisible(true);
            return;
        }
        //TODO admin
    }

    private void showWrongCredentialsDialog() {
        JOptionPane.showMessageDialog(null,
                "Вы ввели неправильный логин или пароль!",
                "Неправильные данные входа",
                JOptionPane.ERROR_MESSAGE);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
