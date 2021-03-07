package front.frame;

import model.test.Question;
import model.test.Test;
import service.QuestionService;
import service.ServiceFactory;
import service.TestService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ChangeTestDialog extends JDialog {
    private JPanel contentPane;
    private JButton deleteQuestionButton;
    private JButton modifyQuestionButton;
    private JButton createQuestionButton;
    private JList<Question> questionList = new JList<>();
    private JPanel buttonPanel;
    private JPanel listPanel;
    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private TestService testService = ServiceFactory.getInstance().getTestService();
    private Test attachedTest;

    public ChangeTestDialog(Test test) {
        attachedTest = test;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(deleteQuestionButton);

        createLayout();

        deleteQuestionButton.addActionListener(e -> deleteSelectedQuestion());
        modifyQuestionButton.addActionListener(e -> modifyQuestionDialog());
        createQuestionButton.addActionListener(e -> createQuestionDialog());

        fillListWithQuestions();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeDialog();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeDialog();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void createQuestionDialog() {
        new CreateQuestionDialog(attachedTest).setVisible(true);
        fillListWithQuestions();
        testService.recalculateAndUpdateTest(attachedTest);

    }

    private void deleteSelectedQuestion() {
        Question questionToDelete = questionList.getSelectedValue();
        if (questionToDelete == null) {
            return;
        }
        questionService.deleteQuestion(questionToDelete);
        fillListWithQuestions();
        testService.recalculateAndUpdateTest(attachedTest);
    }

    private void modifyQuestionDialog() {
        Question selectedValue = questionList.getSelectedValue();
        if (selectedValue == null) {
            return;
        }
        new ModifyQuestionDialog(selectedValue).setVisible(true);
        fillListWithQuestions();
        testService.recalculateAndUpdateTest(attachedTest);
    }

    private void closeDialog() {

        dispose();
    }

    private void createLayout() {
        setSize(600, 400);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(deleteQuestionButton);
        buttonPanel.add(modifyQuestionButton);
        buttonPanel.add(createQuestionButton);
        listPanel.add(questionList);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(listPanel, BorderLayout.NORTH);
        setListSelectionModel();
    }

    private void setListSelectionModel() {
        ListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionList.setSelectionModel(selectionModel);
    }

    private void fillListWithQuestions() {
        Vector<Question> vector = new Vector<>(questionService.getAllQuestionsForTest(attachedTest));
        questionList.setListData(vector);
    }

}
