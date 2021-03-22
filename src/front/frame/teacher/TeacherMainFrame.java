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


        setSize(400, 400);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

    }

    private void createLayout() {
        setContentPane(contentPane);
        setResizable(false);
    }

    private void openStudentStatisticsFrame() {
        new BrowseStatisticsTeacherDialog().setVisible(true);
    }

    private void openTestOperationDialog() {
        new TestOperationDialog().setVisible(true);

    }

}
