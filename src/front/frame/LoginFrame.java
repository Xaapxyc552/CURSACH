package front.frame;

import dao.AnswerDao;
import dao.DaoFactory;
import dao.DataStorageInitializer;
import dao.QuestionDao;
import exceptions.ModelNotFoundException;
import model.test.Answer;
import model.test.Question;
import model.test.Test;
import model.user.Role;
import model.user.User;
import service.LoginService;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.event.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            new TeacherMainForm().setVisible(true);
            return;
        }
        if (user.getRole().equals(Role.STUDENT)) {

        }
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
