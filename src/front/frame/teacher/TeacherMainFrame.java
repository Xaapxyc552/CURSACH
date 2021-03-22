package front.frame.teacher;

import javax.swing.*;
import java.awt.event.*;

public class TeacherMainFrame extends JDialog {
    private JPanel contentPane;
    private JButton testOperationButton;
    private JButton studentStatisticsButton;


    public TeacherMainFrame() {
        createLayout();

        testOperationButton.addActionListener(e -> openTestOperationDialog());
        studentStatisticsButton.addActionListener(e -> openStudentStatisticsFrame());

        setCloseOperations();
    }

    private void setCloseOperations() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    private void createLayout() {
        setContentPane(contentPane);
        setResizable(false);
        setSize(400, 400);
    }

    private void openStudentStatisticsFrame() {
        new BrowseStatisticsTeacherDialog().setVisible(true);
    }

    private void openTestOperationDialog() {
        new TestOperationDialog().setVisible(true);

    }

}
