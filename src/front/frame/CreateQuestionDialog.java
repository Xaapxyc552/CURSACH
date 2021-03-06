package front.frame;

import front.validation.QuestionValidator;
import front.validation.Validator;
import model.test.Question;
import model.test.Test;
import service.QuestionService;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.event.*;
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
        setSize(600, 400);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSave);

        buttonSave.addActionListener(e -> saveNewQuestion());

        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void saveNewQuestion() {
        Question question = new Question();
        Validator<Question> validator = new QuestionValidator();

        question.setQuestionText(questionTextField.getText());
        question.setName(questionNameField.getText());
        try {
            question.setAmountOfPoints(Double.parseDouble(amountOfPointsField.getText()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        if (!validator.validate(question)) {
            //TODO добавить отображение чё не так
            return;
        }
        question.setTest(attachedTest);
        question.setId(UUID.randomUUID());
        questionService.createNewQuestion(question);
//        getParent()
        dispose();
    }

    private void onCancel() {
        dispose();
    }

}
