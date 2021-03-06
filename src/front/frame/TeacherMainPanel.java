package front.frame;

import front.frame.registry.FrameRegistry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TeacherMainPanel extends JFrame {
    private JPanel contentPane;
    private JButton testsListButton;
    private JButton createTestButton;

    public TeacherMainPanel() throws HeadlessException {
        setContentPane(contentPane);
        getContentPane().setLayout(new GridLayout(3, 2, 2, 2));
        getContentPane().add(testsListButton);
        getContentPane().add(createTestButton);

        testsListButton.addActionListener(e -> openTestListFrame());
        createTestButton.addActionListener(e -> openCreateTestFrame());


        setSize(400, 400);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

    }

    private void openCreateTestFrame() {
//        new CreateTestFrame();
    }

    private void openTestListFrame() {
        dispose();
//        JFrame frame = FrameRegistry.getInstance().getFrame(TestsListFrame.class);
        new TestsListFrame().setVisible(true);

    }

}
