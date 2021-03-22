package front.frame.teacher;

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
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateTestDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField testNameField;
    private JComboBox<Topic> topicBox;
    private JTextField testTimeField;
    private JButton createTopicButton;
    private TestService testService = ServiceFactory.getInstance().getTestService();
    private TopicService topicService = ServiceFactory.getInstance().getTopicService();

    public CreateTestDialog() {
        setSize(500, 400);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);

        fillTopicBox();

        buttonOK.addActionListener(e -> saveNewTest());
        buttonCancel.addActionListener(e -> dispose());
        createTopicButton.addActionListener(e -> createTopicDialog());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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
        String text = testTimeField.getText();
        if (text == null || text.isEmpty()) {
            return;
        }
        try {
            Duration duration = getAndParseDuration(text);
            test.setTimeForTest(duration);
        } catch (Exception e) {
            return;
        }
        test.setTopic((Topic) topicBox.getSelectedItem());
        test.setName(testNameField.getText());
        Validator<Test> validator= new TestValidator();
        if (!validator.validate(test)) {
            return;
        }
        testService.createTest(test);
        dispose();
    }

    private Duration getAndParseDuration(String text) {
        List<Long> collect = Stream.of(text)
                .flatMap(e -> Arrays.stream(e.split(":")))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        Duration testDuration = Duration.ofHours(collect.get(0))
                .plusMinutes(collect.get(1))
                .plusSeconds(collect.get(2));
        if (testDuration.toMinutes()<1) {
            throw new IllegalArgumentException("Time for test cannot be less than 1 minute.");
        }
        return testDuration;
    }

}
