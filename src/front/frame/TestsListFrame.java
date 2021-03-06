package front.frame;

import model.test.Test;
import service.ServiceFactory;
import service.TestService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class TestsListFrame extends JFrame{
    private JPanel contentPane;
    private JList<Test> testsList = new JList<>();
    private JButton modifyTestButton;
    private JPanel listPanel;
    private TestService testService = ServiceFactory.getInstance().getTestService();

    public TestsListFrame() {
        createLayout();

        modifyTestButton.addActionListener(e -> createTestChangeDialog());

        fillListWithTests();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void createLayout() {
        setSize(400, 400);
        setContentPane(contentPane);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(listPanel,BorderLayout.NORTH);
        listPanel.add(testsList);
        getContentPane().add(modifyTestButton,BorderLayout.SOUTH);
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
