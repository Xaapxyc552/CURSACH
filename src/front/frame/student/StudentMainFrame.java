package front.frame.student;

import model.user.User;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentMainFrame extends JFrame {
    private JPanel contentPane;
    private JButton testListButton;
    private JButton studentStatisticsButton;

    private final User loggedInUser;

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
        setResizable(false);
    }

    private void openStudentStatisticsFrame() {
        new StatisticsBrowseDialog(loggedInUser).setVisible(true);
    }

    private void openTestChoseDialog() {
        new ChoseTestDialog(loggedInUser).setVisible(true);
    }

}
