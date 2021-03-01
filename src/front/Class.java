package front;

import dao.DaoFactory;
import dao.DataStorageInitializer;
import model.user.Role;
import model.user.User;

import javax.swing.*;
import java.awt.event.*;
import java.util.UUID;

public class Class extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Class() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        DataStorageInitializer.initializeDataStorage();
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("name1");
        user.setSurname("surname");
        user.setPassword("password");
        user.setLogin("login");
        user.setRole(Role.STUDENT);
        DaoFactory.getInstance().getUserDao().save(user);
        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setName("name2");
        user2.setSurname("surname");
        user2.setPassword("password");
        user2.setLogin("login");
        user2.setRole(Role.STUDENT);
        DaoFactory.getInstance().getUserDao().save(user2);
        User user3 = new User();
        user3.setId(UUID.randomUUID());
        user3.setName("name3");
        user3.setSurname("surname");
        user3.setPassword("password");
        user3.setLogin("login");
        user3.setRole(Role.STUDENT);
        DaoFactory.getInstance().getUserDao().save(user3);

        DaoFactory.getInstance().getUserDao().delete(user2);
        user.setSurname("changedSurname");
        DaoFactory.getInstance().getUserDao().update(user);
        System.out.println(DaoFactory.getInstance().getUserDao().findAll());
        System.out.println(DaoFactory.getInstance().getUserDao().findById(user.getId()));


//
//        Class dialog = new Class();
//        dialog.pack();
//        dialog.setVisible(true);
        System.exit(0);
    }
}
