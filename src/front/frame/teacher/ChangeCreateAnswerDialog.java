package front.frame.teacher;

import dao.AnswerDao;
import dao.DaoFactory;
import front.validation.ConstraintViolation;
import front.validation.ValidationViolationDialog;
import front.validation.impl.AnswerValidator;
import front.validation.Validator;
import model.test.Answer;
import model.test.Question;

import javax.swing.*;
import java.awt.event.*;
import java.util.Set;
import java.util.UUID;

public class ChangeCreateAnswerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox isCorrect;
    private JTextArea answerText;
    private Question attachedQuestion;
    private Answer attachedAnswer;
    private AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();

    public ChangeCreateAnswerDialog(Question question, Answer answer) {
        this(question);
        if (question == null && answer == null) {
            throw new IllegalArgumentException("Passed models cannot be null");
        }
        attachedAnswer = answer;
        answerText.setText(attachedAnswer.getAnswerText());
        isCorrect.setSelected(attachedAnswer.isCorrect());
    }

    public ChangeCreateAnswerDialog(Question question) {
        attachedQuestion = question;
        createLayout();

        buttonOK.addActionListener(e -> saveAnswer());
        buttonCancel.addActionListener(e -> dispose());

        setCloseOperations();
    }

    private void setCloseOperations() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void createLayout() {
        setSize(300, 250);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
    }

    private void saveAnswer() {
        if (attachedAnswer==null) {
            saveNewAnswer();
        } else {
            saveEditedAnswer();
        }
    }

    private void saveNewAnswer() {
        Answer answer = new Answer();
        answer.setId(UUID.randomUUID());
        bindAnswerFromFields(answer);
        Validator<Answer> validator = new AnswerValidator();
        Set<ConstraintViolation> validate = validator.validate(answer);
        if (!validate.isEmpty()) {
            new ValidationViolationDialog(validate).setVisible(true);
            return;
        }
        answerDao.save(answer);
        dispose();
    }

    private void bindAnswerFromFields(Answer answer) {
        answer.setAnswerText(answerText.getText());
        answer.setCorrect(isCorrect.isSelected());
        answer.setQuestion(attachedQuestion);
    }

    private void saveEditedAnswer() {
        setSize(300, 200);
        Answer answerToSave = new Answer();
        bindAnswerFromFields(answerToSave);
        Validator<Answer> validator = new AnswerValidator();
        Set<ConstraintViolation> validate = validator.validate(answerToSave);
        if (!validate.isEmpty()) {
            new ValidationViolationDialog(validate).setVisible(true);
            return;
        }
        answerToSave.setId(attachedAnswer.getId());
        answerDao.update(answerToSave);
        dispose();
    }
}
