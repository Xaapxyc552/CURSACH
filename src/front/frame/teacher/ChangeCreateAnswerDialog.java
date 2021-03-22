package front.frame.teacher;

import dao.AnswerDao;
import dao.DaoFactory;
import front.validation.impl.AnswerValidator;
import front.validation.Validator;
import model.test.Answer;
import model.test.Question;

import javax.swing.*;
import java.awt.event.*;
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
        setSize(300, 250);
        attachedQuestion = question;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);


        buttonOK.addActionListener(e -> saveAnswer());
        buttonCancel.addActionListener(e -> dispose());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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
        answer.setAnswerText(answerText.getText());
        answer.setCorrect(isCorrect.isSelected());
        answer.setQuestion(attachedQuestion);
        Validator<Answer> validator = new AnswerValidator();
        if (!validator.validate(answer)) {
            //TODO сделать валидацию
            return;
        }
        answerDao.save(answer);
        dispose();
    }

    private void saveEditedAnswer() {
        setSize(300, 200);
        Answer answerToSave = new Answer();
        answerToSave.setAnswerText(answerText.getText());
        answerToSave.setCorrect(isCorrect.isSelected());
        answerToSave.setQuestion(attachedQuestion);
        Validator<Answer> validator = new AnswerValidator();
        if (!validator.validate(answerToSave)) {
            //TODO сделать валидацию
            return;
        }
        answerToSave.setId(attachedAnswer.getId());
        answerDao.update(answerToSave);
        dispose();
    }
}
