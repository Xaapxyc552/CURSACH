package front.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TeacherMainForm extends JDialog {
    private JPanel contentPane;
    private JButton testsListButton;
    private JButton createTestButton;


    public TeacherMainForm() {
        createLayout();

        testsListButton.addActionListener(e -> openTestListFrame());
        createTestButton.addActionListener(e -> openCreateTestFrame());


        setSize(400, 400);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

    }

    private void createLayout() {
        setContentPane(contentPane);
        getContentPane().setLayout(new GridLayout(2, 2, 2, 2));
        getContentPane().add(testsListButton);
        getContentPane().add(createTestButton);
    }

    private void openCreateTestFrame() {
//        new CreateTestFrame();
    }

    private void openTestListFrame() {
//        JFrame frame = FrameRegistry.getInstance().getFrame(TestsListFrame.class);
        new TestsListFrame().setVisible(true);

    }

}
