package front.frame.admin;

import exceptions.UniqueConstraintViolationException;
import front.validation.ConstraintViolation;
import front.validation.ValidationViolationDialog;
import front.validation.Validator;
import front.validation.impl.UserValidator;
import model.user.Role;
import model.user.User;
import service.ServiceFactory;
import service.UserService;

import javax.swing.*;
import java.awt.event.*;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

public class AdminMainFrame extends JDialog {
    private JPanel contentPane;
    private JButton saveButton;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField loginField;
    private JComboBox<String> roleBox;
    private JTextField passwordField;

    private UserService userService = ServiceFactory.getInstance().getUserService();


    public AdminMainFrame() {
        createLayout();

        saveButton.addActionListener(e -> saveNewUser());
        buttonCancel.addActionListener(e -> dispose());

        setCloseOperations();
    }

    private void setCloseOperations() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void saveNewUser() {
        User user = getNewUserFromFields();

        Validator<User> validator = new UserValidator();
        Set<ConstraintViolation> validate = validator.validate(user);
        if (!validate.isEmpty()) {
            new ValidationViolationDialog(validate).setVisible(true);
            return;
        }
        try {
            userService.registerNewUser(user);
        } catch (UniqueConstraintViolationException e) {
            showNotUniqueLoginMessage();
            return;
        }
        showSuccessfulUserSaveMessage();
        clearAllFields();
    }

    private User getNewUserFromFields() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(nameField.getText());
        user.setSurname(surnameField.getText());
        user.setPassword(passwordField.getText());
        user.setLogin(loginField.getText());
        user.setRole(Role.getRoleForUkrValue((String) roleBox.getSelectedItem()));
        return user;
    }

    private void showNotUniqueLoginMessage() {
        JOptionPane.showMessageDialog(null,
                "Користувач з таким логіном вже зареєстрований!",
                "Помилка!",
                JOptionPane.ERROR_MESSAGE);
    }

    private void clearAllFields() {
        loginField.setText("");
        passwordField.setText("");
        surnameField.setText("");
        nameField.setText("");
        roleBox.setSelectedIndex(0);
    }

    private void showSuccessfulUserSaveMessage() {
        JOptionPane.showMessageDialog(null,
                "Користувач успішно збережений!",
                "Успіх!",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void createLayout() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(saveButton);
        setSize(450, 300);

        fillDropBoxWithRoles();
    }

    private void fillDropBoxWithRoles() {
        EnumSet.allOf(Role.class).stream().map(Role::getUkrValue).forEach(roleBox::addItem);
    }
}
