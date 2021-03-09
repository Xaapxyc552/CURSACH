package front.frame.student;

import model.result.TestResult;
import model.test.Answer;
import model.test.Question;
import model.test.Test;
import model.user.User;
import service.AnswerCheckerService;
import service.AnswerService;
import service.QuestionService;
import service.ServiceFactory;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TestPassingDialog extends JDialog {
    private JPanel contentPane;
    private JButton finishTestButton;
    private JButton chooseAnswerButton;
    private JLabel timeLeftForTest;
    private JList<Question> questionsList;
    private JList<Answer> answersList;

    private final QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private final AnswerService answerService = ServiceFactory.getInstance().getAnswerService();
    private final AnswerCheckerService answerCheckerService = ServiceFactory.getInstance().getAnswerCheckerService();
    private final AnswerListFiller answerListFiller;

    private static final String TIME_REMAIN_PATTERN = "Часу залишилось: %02d:%02d:%02d.";
    private LocalDateTime finishTime;
    private LocalDateTime startTime;
    private final Timer timer;


    private final Test testToPass;
    private final TestResult testResult;
    private User loggedInUser;


    public TestPassingDialog(Test testToPass, User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.testToPass = testToPass;
        answerListFiller = new AnswerListFiller();
        testResult = new TestResult(testToPass,LocalDateTime.now());
        setContentPane(contentPane);
        setModal(true);
        setSize(600, 400);


        initializeButtons();
        setListSelectionModel();
        fillQuestionsList(testToPass);
        answerListFiller.fillNextAnswerList();
        timer = createAndStartTimer();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void initializeButtons() {
        finishTestButton.addActionListener(e -> finishTestButton());
        chooseAnswerButton.addActionListener(e -> choseAnswerButton());
    }

    private void finishTestButton() {
        testResult.setFinishTime(LocalDateTime.now());
        answerCheckerService.getStatFromTestResult(testResult, loggedInUser);
        dispose();
    }

    private void choseAnswerButton() {
        Question question = questionsList.getSelectedValue();
        Answer answer = answersList.getSelectedValue();
        testResult.addQuestionAnswer(question, answer);
        if (answerListFiller.isHasNextQuestion()) {
            answerListFiller.fillNextAnswerList();
        } else {
            finishTestButton();
        }
    }

    private void fillQuestionsList(Test testToPass) {
        List<Question> allQuestionsForTest = questionService.getAllQuestionsForTest(testToPass);
        questionsList.setListData(new Vector<>(allQuestionsForTest));
    }

    private void setListSelectionModel() {
        ListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel2 = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionsList.setSelectionModel(selectionModel);
        questionsList.setEnabled(false);
        answersList.setSelectionModel(selectionModel2);
    }


    private Timer createAndStartTimer() {
        startTime = LocalDateTime.now();
        finishTime = startTime.plus(testToPass.getTimeForTest());
        timeLeftForTest.setText(getRemainTimeString());
        final Timer timer = new Timer(1000,
                e -> {
                    if (LocalDateTime.now().isAfter(finishTime)) {
                        finishTestButton();
                    }
                    timeLeftForTest.setText(getRemainTimeString());
                });
        timer.start();
        return timer;
    }

    private String getRemainTimeString() {
        LocalDateTime now = LocalDateTime.now();
        long seconds = ChronoUnit.SECONDS.between(now, finishTime);
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return String.format(TIME_REMAIN_PATTERN, hours, minutes % 60, seconds % 60);
    }

    private class AnswerListFiller {
        private final List<Question> questionList = questionService.getAllQuestionsForTest(testToPass);
        private final Iterator<Question> iterator = questionList.iterator();
        private int currentSelected = 0;

        public void fillNextAnswerList() {
            List<Answer> allAnswersForQuestion = answerService.getAllAnswersForQuestion(iterator.next());
            answersList.setListData(new Vector<>(allAnswersForQuestion));
            questionsList.setSelectedIndex(currentSelected++);
        }

        public boolean isHasNextQuestion() {
            return iterator.hasNext();
        }
    }

}
