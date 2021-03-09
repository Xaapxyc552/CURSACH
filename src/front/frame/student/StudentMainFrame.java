package front.frame.student;

import front.frame.teacher.TestOperationsFrame;
import model.user.User;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentMainFrame extends JFrame {
    private JPanel contentPane;
    private JButton testListButton;
    private JButton studentStatisticsButton;

    private User loggedInUser;

    public StudentMainFrame(User user) {
        loggedInUser = user;
        createLayout();

        testListButton.addActionListener(e -> openTestChoseDialog());
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

    private void openTestChoseDialog() {
        new ChoseTestDialog(loggedInUser).setVisible(true);

    }

}