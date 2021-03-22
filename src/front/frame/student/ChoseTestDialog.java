package front.frame.student;

import model.test.Test;
import model.user.User;
import service.ServiceFactory;
import service.TestService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

public class ChoseTestDialog extends JDialog {
    private JPanel contentPane;
    private JButton startTestButton;
    private JButton buttonCancel;
    private JList<Test> testList;

    private TestService testService = ServiceFactory.getInstance().getTestService();
    private User loggedInUser;

    public ChoseTestDialog(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        createLayout();

        startTestButton.addActionListener(e -> startTest());
        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void createLayout() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(startTestButton);
        setSize(600, 400);
        setResizable(false);

        setListSelectionModel();
        fillListWithTests();
    }

    private void fillListWithTests() {
        List<Test> allTests = testService.getAllTestsForStudents();
        testList.setListData(new Vector<>(allTests));
        testList.revalidate();
    }

    private void setListSelectionModel() {
        ListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testList.setSelectionModel(selectionModel);
    }

    private void startTest() {
        Test testToPass = testList.getSelectedValue();
        if (testToPass == null) {
            return;
        }
        new TestPassingDialog(testToPass, loggedInUser).setVisible(true);
    }

    private void onCancel() {
        dispose();
    }

}
