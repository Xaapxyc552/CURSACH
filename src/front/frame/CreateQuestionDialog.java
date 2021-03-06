package front.frame;

import model.test.Question;
import service.QuestionService;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.event.*;

public class CreateQuestionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JTextArea questionTextField;
    private JTextField questionNameField;
    private JTextField amountOfPointsField;
    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();

    public CreateQuestionDialog() {
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
//        question.
//        questionService.createNewQuestion()
        dispose();
    }

    private void onCancel() {
        dispose();
    }

}
