package front.frame.teacher;

import front.validation.ConstraintViolation;
import front.validation.ValidationViolationDialog;
import front.validation.impl.QuestionValidator;
import front.validation.Validator;
import model.test.Question;
import model.test.Test;
import service.QuestionService;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.event.*;
import java.util.Set;
import java.util.UUID;

public class CreateQuestionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JTextArea questionTextField;
    private JTextField questionNameField;
    private JTextField amountOfPointsField;
    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private Test attachedTest;

    public CreateQuestionDialog(Test test) {
        attachedTest = test;
        createLayout();

        buttonSave.addActionListener(e -> saveNewQuestion());
        buttonCancel.addActionListener(e -> onCancel());

        setCloseOperations();
    }

    private void createLayout() {
        setSize(600, 400);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSave);
        setResizable(false);
    }

    private void setCloseOperations() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void saveNewQuestion() {
        Question question = new Question();
        bindQuestionFromFields(question);
        Validator<Question> validator = new QuestionValidator();
        Set<ConstraintViolation> validate = validator.validate(question);
        if (!validate.isEmpty()) {
            new ValidationViolationDialog(validate).setVisible(true);
            return;
        }
        question.setTest(attachedTest);
        question.setId(UUID.randomUUID());
        questionService.createNewQuestion(question);
        new ModifyQuestionDialog(question).setVisible(true);
        dispose();
    }

    private void bindQuestionFromFields(Question question) {
        question.setQuestionText(questionTextField.getText());
        question.setName(questionNameField.getText());
        try {
            question.setAmountOfPoints(Double.parseDouble(amountOfPointsField.getText()));
        } catch (NumberFormatException e) {
            question.setAmountOfPoints(0);
        }
    }

    private void onCancel() {
        dispose();
    }

}
