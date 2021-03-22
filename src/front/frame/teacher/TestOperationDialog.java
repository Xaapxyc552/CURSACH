package front.frame.teacher;

import model.test.Test;
import service.ServiceFactory;
import service.TestService;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class TestOperationDialog extends JDialog {

    private JPanel contentPane;
    private JButton modifyTestButton;
    private JButton newTestButton;
    private JList<Test> testList;

    private TestService testService = ServiceFactory.getInstance().getTestService();

    public TestOperationDialog() {
        createLayout();

        modifyTestButton.addActionListener(e -> createTestChangeDialog());
        newTestButton.addActionListener(e -> createTestFrame());

        setCloseOperations();
    }

    private void setCloseOperations() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,
                        0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void createTestFrame() {
        new CreateTestDialog().setVisible(true);
        fillListWithTests();
    }

    private void createLayout() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(newTestButton);
        setModal(true);
        setSize(600, 400);
        setResizable(false);

        setListSelectionModel();
        fillListWithTests();
    }

    private void setListSelectionModel() {
        ListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testList.setSelectionModel(selectionModel);
    }

    private void createTestChangeDialog() {
        Test selectedValue = testList.getSelectedValue();
        if (selectedValue==null) {
            return;
        }
        new ChangeTestDialog(selectedValue).setVisible(true);
    }

    private void fillListWithTests() {
        Vector<Test> vector = new Vector<>(testService.getAllTests());
        testList.setListData(vector);
    }





}
