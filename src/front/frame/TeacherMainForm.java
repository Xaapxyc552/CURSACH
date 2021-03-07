package front.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TeacherMainForm extends JDialog {
    private JPanel contentPane;
    private JButton testOperationButton;
    private JButton studentStatisticsButton;


    public TeacherMainForm() {
        createLayout();

        testOperationButton.addActionListener(e -> openTestOperationsFrame());
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
    }

    private void openStudentStatisticsFrame() {
//        new CreateTestFrame().setVisible(true);
    }

    private void openTestOperationsFrame() {
//        JFrame frame = FrameRegistry.getInstance().getFrame(TestsListFrame.class);
        new TestOperationsFrame().setVisible(true);

    }

}
