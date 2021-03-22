package front.frame.teacher;

import front.validation.ConstraintViolation;
import front.validation.ValidationViolationDialog;
import front.validation.impl.TestValidator;
import front.validation.Validator;
import model.test.Test;
import model.test.Topic;
import service.ServiceFactory;
import service.TestService;
import service.TopicService;

import javax.swing.*;
import java.awt.event.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateTestDialog extends JDialog {
    private JPanel contentPane;
    private JButton saveTestButton;
    private JButton buttonCancel;
    private JTextField testNameField;
    private JComboBox<Topic> topicBox;
    private JTextField testTimeField;
    private JButton createTopicButton;
    private TestService testService = ServiceFactory.getInstance().getTestService();
    private TopicService topicService = ServiceFactory.getInstance().getTopicService();

    public CreateTestDialog() {
        createLayout();

        fillTopicBox();

        saveTestButton.addActionListener(e -> saveNewTest());
        buttonCancel.addActionListener(e -> dispose());
        createTopicButton.addActionListener(e -> createTopicDialog());

        setCloseOperations();
    }

    private void createLayout() {
        setSize(500, 400);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(saveTestButton);
        setResizable(false);
    }

    private void setCloseOperations() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void createTopicDialog() {
        new CreateTopicDialog().setVisible(true);
        topicBox.removeAllItems();
        fillTopicBox();
    }

    private void fillTopicBox() {
        topicService.findAllTopics().forEach(topicBox::addItem);
    }

    private void saveNewTest() {
        Test test = new Test();
        test.setId(UUID.randomUUID());
        bindTestFromFields(test);
        Validator<Test> validator = new TestValidator();
        Set<ConstraintViolation> validate = validator.validate(test);
        if (!validate.isEmpty()) {
            new ValidationViolationDialog(validate).setVisible(true);
            return;
        }
        testService.createTest(test);
        new ChangeTestDialog(test).setVisible(true);
        dispose();
    }

    private void bindTestFromFields(Test test) {
        String text = testTimeField.getText();
        try {
            Duration duration = getAndParseDuration(text);
            test.setTimeForTest(duration);
        } catch (Exception e) {
            //in case of unsuccessful parsing validator will show a warning
        }
        test.setTopic((Topic) topicBox.getSelectedItem());
        test.setName(testNameField.getText());
    }

    private Duration getAndParseDuration(String text) {
        List<Long> collect = Stream.of(text)
                .flatMap(e -> Arrays.stream(e.split(":")))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return Duration.ofHours(collect.get(0)).plusMinutes(collect.get(1)).plusSeconds(collect.get(2));
    }

}
