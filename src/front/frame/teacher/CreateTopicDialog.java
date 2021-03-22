package front.frame.teacher;

import front.validation.ConstraintViolation;
import front.validation.ValidationViolationDialog;
import front.validation.impl.TopicValidator;
import front.validation.Validator;
import model.test.Topic;
import service.ServiceFactory;
import service.TopicService;

import javax.swing.*;
import java.awt.event.*;
import java.util.Set;
import java.util.UUID;

public class CreateTopicDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField topicNameField;
    private TopicService topicService = ServiceFactory.getInstance().getTopicService();

    public CreateTopicDialog() {
        setLayout();

        buttonOK.addActionListener(e -> saveTopic());
        buttonCancel.addActionListener(e -> onCancel());

        setCloseOperations();
    }

    private void setLayout() {
        setSize(300,200);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
    }

    private void setCloseOperations() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void saveTopic() {
        Topic topic = new Topic();
        bindTopicFromFields(topic);
        Validator<Topic> validator = new TopicValidator();
        Set<ConstraintViolation> validate = validator.validate(topic);
        if (!validate.isEmpty()) {
            new ValidationViolationDialog(validate).setVisible(true);
            return;
        }
        topic.setId(UUID.randomUUID());
        topicService.createNewTopic(topic);
        dispose();
    }

    private void bindTopicFromFields(Topic topic) {
        topic.setName(topicNameField.getText());
    }

    private void onCancel() {
        dispose();
    }

}
