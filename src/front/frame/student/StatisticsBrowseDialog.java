package front.frame.student;

import model.result.Statistics;
import model.user.User;
import service.ServiceFactory;
import service.StatisticService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

public class StatisticsBrowseDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JList<Statistics> testResultsList;
    private JTextArea testResultArea;

    private final StatisticService statisticService = ServiceFactory.getInstance().getStatisticService();

    private User loggedInUser;

    public StatisticsBrowseDialog(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        createLayout();

        buttonOK.addActionListener(e -> onOK());

        setListSelectionModel();
        testResultsList.addListSelectionListener(new ListSelectionListenerImpl());
        fillListWithStatistics();


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
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(800, 400);
        setResizable(false);
    }

    private void fillListWithStatistics() {
        List<Statistics> statisticsForUser = statisticService.getStatisticsForUser(loggedInUser);
        testResultsList.setListData(new Vector<>(statisticsForUser));
        testResultsList.revalidate();
    }

    private void setListSelectionModel() {
        ListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testResultsList.setSelectionModel(selectionModel);
    }

    private void onOK() {
        dispose();
    }

    class ListSelectionListenerImpl implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            Statistics selectedValue = testResultsList.getSelectedValue();
            testResultArea.setText(selectedValue.getTestResultString());
        }
    }
}
