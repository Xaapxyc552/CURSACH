package front.frame;

import model.test.Question;
import model.test.Test;
import service.QuestionService;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ChangeTestDialog extends JDialog {
    private JPanel contentPane;
    private JButton deleteQuestionButton;
    private JButton modifyQuestionButton;
    private JButton createQuestionButton;
    private JList<Question> questionList  = new JList<>();;
    private JPanel buttonPanel = new JPanel();
    private JPanel listPanel = new JPanel();
    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private Test attachedTest;

    public ChangeTestDialog(Test test) {
        attachedTest = test;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(deleteQuestionButton);

        createLayout();

        deleteQuestionButton.addActionListener(e -> deleteSelectedQuestion());
        modifyQuestionButton.addActionListener(e -> createModifyingDialog());
        createQuestionButton.addActionListener(e -> createQuestionDialog());

        fillListWithQuestions();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void createModifyingDialog() {

    }

    private void createLayout() {
        setSize(400, 400);
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

    private void createQuestionDialog() {

    }

    private void setListSelectionModel() {
        ListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionList.setSelectionModel(selectionModel);
    }

    private void deleteSelectedQuestion() {
        Question questionToDelete = questionList.getSelectedValue();
        questionService.deleteQuestion(questionToDelete);
        fillListWithQuestions();
    }

    private void fillListWithQuestions() {
        Vector<Question> vector = new Vector<>(questionService.getAllQuestionsForTest(attachedTest));
        questionList.setListData(vector);
    }

}
