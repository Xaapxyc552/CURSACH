package front.frame;

import front.ChangeCreateAnswerDialog;
import front.validation.QuestionValidator;
import front.validation.Validator;
import model.test.Answer;
import model.test.Question;
import service.AnswerService;
import service.QuestionService;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class ModifyQuestionDialog extends JDialog {
    private JPanel contentPane;
    private JButton changeAnswerButton;
    private JButton createAnswerButton;
    private JTextField amountOfPointsField;
    private JTextField questionNameField;
    private JList<Answer> answersList;
    private JTextArea questionTextArea;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton deleteAnswerButton;
    private Question attachedQuestion;
    private AnswerService answerService = ServiceFactory.getInstance().getAnswerService();
    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();

    public ModifyQuestionDialog(Question question) {
        setSize(600, 400);
        attachedQuestion = question;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(changeAnswerButton);
        setListSelectionModel();
        fillFieldsWthData();

        changeAnswerButton.addActionListener(e -> changeAnswerDialog());
        createAnswerButton.addActionListener(e -> createAnswerDialog());
        saveButton.addActionListener(e -> saveModifiedQuestion());
        cancelButton.addActionListener(e -> dispose());
        deleteAnswerButton.addActionListener(e -> deleteAnswer());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

    private void deleteAnswer() {
        Answer selectedValue = answersList.getSelectedValue();
        if (selectedValue == null) {
            return;
        }
        answerService.deleteAnswer(selectedValue);
        fillListWithAnswers();
    }

    private void fillFieldsWthData() {
        questionTextArea.setText(attachedQuestion.getQuestionText());
        questionNameField.setText(attachedQuestion.getName());
        amountOfPointsField.setText(String.valueOf(attachedQuestion.getAmountOfPoints()));
        fillListWithAnswers();

    }

    private void changeAnswerDialog() {
        Answer selectedAnswer = answersList.getSelectedValue();
        if (selectedAnswer == null) {
            return;
        }
        new ChangeCreateAnswerDialog(attachedQuestion, selectedAnswer).setVisible(true);
        fillListWithAnswers();
    }

    private void createAnswerDialog() {
        new ChangeCreateAnswerDialog(attachedQuestion).setVisible(true);
        fillListWithAnswers();
    }

    private void saveModifiedQuestion() {
        Question question = new Question();
        try {
            question.setAmountOfPoints(Double.parseDouble(amountOfPointsField.getText()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            //TODO приписать отображение
            return;
        }
        question.setQuestionText(questionTextArea.getText());
        question.setName(questionNameField.getText());
        question.setTest(attachedQuestion.getTest());
        question.setAnswerList(attachedQuestion.getAnswerList());
        Validator<Question> validator = new QuestionValidator();
        if (!validator.validate(question)) {
            return;
        }
        question.setId(attachedQuestion.getId());
        questionService.updateQuestionData(question);
        dispose();
    }

    private void setListSelectionModel() {
        ListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        answersList.setSelectionModel(selectionModel);
    }

    private void fillListWithAnswers() {
        Vector<Answer> vector = new Vector<>(answerService.getAllAnswersForQuestion(attachedQuestion));
        answersList.setListData(vector);
    }
}
