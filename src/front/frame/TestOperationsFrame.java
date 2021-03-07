package front.frame;

import model.test.Test;
import service.ServiceFactory;
import service.TestService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class TestOperationsFrame extends JFrame{
    private JPanel contentPane;
    private JList<Test> testsList = new JList<>();
    private JButton modifyTestButton;
    private JPanel listPanel;
    private JButton newTestButton;
    private JPanel buttonPanel;
    private TestService testService = ServiceFactory.getInstance().getTestService();

    public TestOperationsFrame() {
        createLayout();

        modifyTestButton.addActionListener(e -> createTestChangeDialog());
        newTestButton.addActionListener(e -> createTestFrame());

        fillListWithTests();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void createTestFrame() {
        new CreateTestFrame().setVisible(true);
        fillListWithTests();
    }

    private void createLayout() {
        setSize(400, 400);
        setContentPane(contentPane);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(listPanel,BorderLayout.NORTH);
        listPanel.add(testsList);
        getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(modifyTestButton);
        buttonPanel.add(newTestButton);
        setListSelectionModel();
    }

    private void setListSelectionModel() {
        ListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testsList.setSelectionModel(selectionModel);
    }

    private void createTestChangeDialog() {
        Test selectedValue = testsList.getSelectedValue();
        if (selectedValue==null) {
            return;
        }
        new ChangeTestDialog(selectedValue).setVisible(true);
    }

    private void fillListWithTests() {
        Vector<Test> vector = new Vector<>(testService.getAllTests());
        testsList.setListData(vector);
    }
}
