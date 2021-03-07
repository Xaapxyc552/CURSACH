import dao.AnswerDao;
import dao.DaoFactory;
import dao.DataStorageInitializer;
import dao.QuestionDao;
import front.frame.LoginFrame;
import model.test.Answer;
import model.test.Question;
import model.test.Test;
import model.test.Topic;
import model.user.Role;
import model.user.User;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApplicationRunner {

    public static void main(String[] args) {
//        DataStorageInitializer.clearData();

        DataStorageInitializer.initializeDataStorage();
//        filldb();


//
        LoginFrame dialog = new LoginFrame();
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }

    private static void filldb() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("name1");
        user.setSurname("surname");
        user.setPassword("password");
        user.setLogin("login");
        user.setRole(Role.TEACHER);
        DaoFactory.getInstance().getUserDao().save(user);
        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setName("name2");
        user2.setSurname("surname");
        user2.setPassword("password");
        user2.setLogin("login2");
        user2.setRole(Role.STUDENT);
        DaoFactory.getInstance().getUserDao().save(user2);
        User user3 = new User();
        user3.setId(UUID.randomUUID());
        user3.setName("name3");
        user3.setSurname("surname");
        user3.setPassword("password");
        user3.setLogin("login3");
        user3.setRole(Role.STUDENT);
        DaoFactory.getInstance().getUserDao().save(user3);

        DaoFactory.getInstance().getUserDao().delete(user2);
        user.setSurname("changedSurname");
        DaoFactory.getInstance().getUserDao().update(user);

//        System.out.println(DaoFactory.getInstance().getUserDao().findAll());
//        System.out.println(DaoFactory.getInstance().getUserDao().findById(user.getId()));
//
//        System.out.println(DaoFactory.getInstance().getUserDao().getUserByLogin("login"));
//        System.out.println(DaoFactory.getInstance().getUserDao().getStudentByLogin("login"));
//        System.out.println(DaoFactory.getInstance().getUserDao().getTeacherByLogin("login"));

        Topic topic = new Topic();
        topic.setId(UUID.randomUUID());
        topic.setName("Приладобудування");

        Topic topic1 = new Topic();
        topic1.setId(UUID.randomUUID());
        topic1.setName("Програмування");

        DaoFactory.getInstance().getTopicDao().save(topic1);
        DaoFactory.getInstance().getTopicDao().save(topic);

        Test test = new Test();
        test.setId(UUID.randomUUID());
        test.setName("name1");
        test.setTopic(topic);
        test.setTimeForTest(Duration.ofMinutes(10L));
        test.setMaximumPoints(5);
        List<Question> questionList = new ArrayList<>();
        test.setQuestions(questionList);

        Question question = new Question();
        question.setTest(test);
        question.setId(UUID.randomUUID());
        question.setAmountOfPoints(2);
        question.setName("question name 1");
        question.setQuestionText("question text 1");
        questionList.add(question);
        List<Answer> answerList = new ArrayList<>();
        question.setAnswerList(answerList);

        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setId(UUID.randomUUID());
        answer.setAnswerText("answer text 1");
        answer.setCorrect(true);
        Answer answer2 = new Answer();
        answer2.setQuestion(question);
        answer2.setId(UUID.randomUUID());
        answer2.setAnswerText("answer text 2");
        answer2.setCorrect(false);
        Answer answer3 = new Answer();
        answer3.setQuestion(question);
        answer3.setId(UUID.randomUUID());
        answer3.setAnswerText("answer text 3");
        answer3.setCorrect(false);
        answerList.add(answer);
        answerList.add(answer2);
        answerList.add(answer3);

        Question question2 = new Question();
        question2.setTest(test);
        question2.setId(UUID.randomUUID());
        question2.setAmountOfPoints(2);
        question2.setName("question name 2");
        question2.setQuestionText("question text 2");
        questionList.add(question2);
        List<Answer> answerList2 = new ArrayList<>();
        question2.setAnswerList(answerList2);

        Answer answer21 = new Answer();
        answer21.setQuestion(question2);
        answer21.setId(UUID.randomUUID());
        answer21.setAnswerText("answer text 1");
        answer21.setCorrect(true);
        Answer answer22 = new Answer();
        answer22.setQuestion(question2);
        answer22.setId(UUID.randomUUID());
        answer22.setAnswerText("answer text 2");
        answer22.setCorrect(false);
        Answer answer23 = new Answer();
        answer23.setQuestion(question2);
        answer23.setId(UUID.randomUUID());
        answer23.setAnswerText("answer text 3");
        answer23.setCorrect(false);

        answerList2.add(answer21);
        answerList2.add(answer22);
        answerList2.add(answer23);


        DaoFactory.getInstance().getTestDao().save(test);
        AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();
        QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao();
        questionList.forEach(questionDao::save);
        questionList.stream()
                .flatMap(n -> n.getAnswerList().stream())
                .forEach(answerDao::save);
    }
}
