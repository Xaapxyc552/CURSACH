package front.frame;

import front.validation.TopicValidator;
import front.validation.Validator;
import model.test.Topic;
import service.ServiceFactory;
import service.TopicService;

import javax.swing.*;
import java.awt.event.*;
import java.util.UUID;

public class CreateTopicDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField topicNameField;
    private TopicService topicService = ServiceFactory.getInstance().getTopicService();

    public CreateTopicDialog() {
        setSize(300,200);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> saveTopic());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void saveTopic() {
        Topic topic = new Topic();
        topic.setName(topicNameField.getText());
        Validator<Topic> validator = new TopicValidator();
        if (!validator.validate(topic)){
            //TODO отображние
            return;
        }
        topic.setId(UUID.randomUUID());
        topicService.createNewTopic(topic);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CreateTopicDialog dialog = new CreateTopicDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
