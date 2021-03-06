package front.frame;

import dao.AnswerDao;
import dao.DaoFactory;
import dao.DataStorageInitializer;
import dao.QuestionDao;
import exceptions.ModelNotFoundException;
import model.test.Answer;
import model.test.Question;
import model.test.Test;
import model.user.Role;
import model.user.User;
import service.LoginService;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.event.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoginFrame extends JFrame {
    private JPanel contentPane;
    private JButton loginButton;
    private JTextField loginTextField;
    private JPasswordField passwordPasswordField;
    private final LoginService loginService = ServiceFactory.getInstance().getLoginService();

    public LoginFrame() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(loginButton);

        loginButton.addActionListener(e -> {
            String login = loginTextField.getText();
            String password = String.valueOf(passwordPasswordField.getPassword());
            User user = null;
            try {
                user = loginService.findUser(login, password);
            } catch (ModelNotFoundException exception) {
                showWringCredentialsDialog();
                return;
            }
            if (user.getRole().equals(Role.TEACHER)) {
                dispose();
                new TeacherMainPanel().setVisible(true);
                return;
            }
            if (user.getRole().equals(Role.STUDENT)) {

            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

    }

    private void showWringCredentialsDialog() {
        JOptionPane.showMessageDialog(null,
                "Вы ввели неправильный логин или пароль!",
                "Неправильные данные входа",
                JOptionPane.ERROR_MESSAGE);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        DataStorageInitializer.initializeDataStorage();
//        User user = new User();
//        user.setId(UUID.randomUUID());
//        user.setName("name1");
//        user.setSurname("surname");
//        user.setPassword("password");
//        user.setLogin("login");
//        user.setRole(Role.STUDENT);
//        DaoFactory.getInstance().getUserDao().save(user);
//        User user2 = new User();
//        user2.setId(UUID.randomUUID());
//        user2.setName("name2");
//        user2.setSurname("surname");
//        user2.setPassword("password");
//        user2.setLogin("login");
//        user2.setRole(Role.STUDENT);
//        DaoFactory.getInstance().getUserDao().save(user2);
//        User user3 = new User();
//        user3.setId(UUID.randomUUID());
//        user3.setName("name3");
//        user3.setSurname("surname");
//        user3.setPassword("password");
//        user3.setLogin("login");
//        user3.setRole(Role.STUDENT);
//        DaoFactory.getInstance().getUserDao().save(user3);
//
//        DaoFactory.getInstance().getUserDao().delete(user2);
//        user.setSurname("changedSurname");
//        DaoFactory.getInstance().getUserDao().update(user);
//        System.out.println(DaoFactory.getInstance().getUserDao().findAll());
//        System.out.println(DaoFactory.getInstance().getUserDao().findById(user.getId()));
//
//        System.out.println(DaoFactory.getInstance().getUserDao().getUserByLogin("login"));
//        System.out.println(DaoFactory.getInstance().getUserDao().getStudentByLogin("login"));
//        System.out.println(DaoFactory.getInstance().getUserDao().getTeacherByLogin("login"));
//
//        Test test = new Test();
//        test.setId(UUID.randomUUID());
//        test.setName("name1");
//        test.setTopic("topic1");
//        test.setTimeForTest(Duration.ofMinutes(10L));
//        test.setMaximumPoints(5);
//        List<Question> questionList = new ArrayList<>();
//        test.setQuestions(questionList);
//
//        Question question = new Question();
//        question.setTest(test);
//        question.setId(UUID.randomUUID());
//        question.setAmountOfPoints(2);
//        question.setName("question name 1");
//        question.setQuestionText("question text 1");
//        questionList.add(question);
//        List<Answer> answerList = new ArrayList<>();
//        question.setAnswerList(answerList);
//
//        Answer answer = new Answer();
//        answer.setQuestion(question);
//        answer.setId(UUID.randomUUID());
//        answer.setAnswerText("answer text 1");
//        answer.setCorrect(true);
//        Answer answer2 = new Answer();
//        answer2.setQuestion(question);
//        answer2.setId(UUID.randomUUID());
//        answer2.setAnswerText("answer text 2");
//        answer2.setCorrect(false);
//        Answer answer3 = new Answer();
//        answer3.setQuestion(question);
//        answer3.setId(UUID.randomUUID());
//        answer3.setAnswerText("answer text 3");
//        answer3.setCorrect(false);
//        answerList.add(answer);
//        answerList.add(answer2);
//        answerList.add(answer3);
//
//        Question question2 = new Question();
//        question2.setTest(test);
//        question2.setId(UUID.randomUUID());
//        question2.setAmountOfPoints(2);
//        question2.setName("question name 2");
//        question2.setQuestionText("question text 2");
//        questionList.add(question2);
//        List<Answer> answerList2 = new ArrayList<>();
//        question2.setAnswerList(answerList);
//
//        Answer answer21 = new Answer();
//        answer21.setQuestion(question);
//        answer21.setId(UUID.randomUUID());
//        answer21.setAnswerText("answer text 1");
//        answer21.setCorrect(true);
//        Answer answer22 = new Answer();
//        answer22.setQuestion(question);
//        answer22.setId(UUID.randomUUID());
//        answer22.setAnswerText("answer text 2");
//        answer22.setCorrect(false);
//        Answer answer23 = new Answer();
//        answer23.setQuestion(question);
//        answer23.setId(UUID.randomUUID());
//        answer23.setAnswerText("answer text 3");
//        answer23.setCorrect(false);
//
//        answerList2.add(answer21);
//        answerList2.add(answer22);
//        answerList2.add(answer23);
//        answerList2.add(answer23);
//
//
//        question.setId(UUID.fromString("8bdb012b-f281-428b-b0c6-56523853bbf6"));
//        AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();
//        System.out.println(answerDao.findAnswersForQuestion(question));
//////
//        System.out.println(DaoFactory.getInstance().getTestDao().save(test));
//        QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao();
//        questionList.forEach(questionDao::save);
//        questionList.stream()
//                .flatMap(n -> n.getAnswerList().stream())
//                .forEach(answerDao::save);


//
        LoginFrame dialog = new LoginFrame();
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }
}
