package front.frame.teacher;

import model.Model;
import model.result.Statistics;
import model.test.Test;
import model.user.User;
import service.ServiceFactory;
import service.StatisticService;
import service.TestService;
import service.UserService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BrowseStatisticsTeacherDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox<SearchModel> searchModel;
    private JList<? extends Model> searchResultList;
    private JButton updateButton;
    private JList<Statistics> statisticsList;
    private JTextArea testResultArea;
    private JPanel resultList;

    private final TestService testService = ServiceFactory.getInstance().getTestService();
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final StatisticService statisticService = ServiceFactory.getInstance().getStatisticService();

    public BrowseStatisticsTeacherDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(800,400);
        setResizable(false);


        buttonOK.addActionListener(e -> dispose());
        updateButton.addActionListener(e -> updateSearchResultList());

        searchModel.addItem(SearchModel.TEST);
        searchModel.addItem(SearchModel.STUDENT);
        searchModel.setSelectedItem(SearchModel.TEST);

        searchResultList.addListSelectionListener(new SearchResultSelectionListener());
        statisticsList.addListSelectionListener(new StatisticsSelectionListener());

        setListSelectionModel();
        fillSearchResultListWithTests();


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void fillSearchResultListWithTests() {
        List<Test> allTests = testService.getAllTests();
        searchResultList.setListData(new Vector(allTests));
    }

    private void fillSearchResultListWithUsers() {
        List<User> allStudents = userService.findAllStudents();
        searchResultList.setListData(new Vector(allStudents));
    }

    private void setListSelectionModel() {
        ListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel2 = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        statisticsList.setSelectionModel(selectionModel);
        searchResultList.setSelectionModel(selectionModel2);
    }

    private void updateSearchResultList() {
        List<? extends Model> results = new ArrayList();
        if (SearchModel.TEST.equals(searchModel.getSelectedItem())) {
            results = testService.getAllTests();
        } else if (SearchModel.STUDENT.equals(searchModel.getSelectedItem())) {
            results = userService.findAllStudents();
        }
        searchResultList.setListData(new Vector(results));
    }

    enum SearchModel {
        STUDENT, TEST
    }


    class SearchResultSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            Model selectedValue = searchResultList.getSelectedValue();
            if (selectedValue instanceof User) {
                User selectedUser = (User) selectedValue;
                List<Statistics> statisticsForTest = statisticService.getStatisticsForUser(selectedUser);
                statisticsList.setListData(new Vector<>(statisticsForTest));
            } else if (selectedValue instanceof Test) {
                Test selectedTest = (Test) selectedValue;
                List<Statistics> statisticsForTest = statisticService.getStatisticsForTest(selectedTest);
                statisticsList.setListData(new Vector<>(statisticsForTest));
            }
        }
    }

    class StatisticsSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            Statistics statistics = statisticsList.getSelectedValue();
            testResultArea.setText(statistics.getTestResultString());
        }
    }
}
