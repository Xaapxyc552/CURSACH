package front.frame;

import exceptions.ModelNotFoundException;
import front.frame.admin.AdminMainFrame;
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
        createLayout();

        loginButton.addActionListener(e -> authorizeUser());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void createLayout() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(loginButton);
        setSize(300, 200);
        setResizable(false);
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
        if (user.getRole().equals(Role.ADMIN)) {
            dispose();
            new AdminMainFrame().setVisible(true);
        }
    }

    private void showWrongCredentialsDialog() {
        JOptionPane.showMessageDialog(null,
                "Ви ввели невірний логін або пароль!",
                "Невірні данні входу",
                JOptionPane.ERROR_MESSAGE);
    }


}
