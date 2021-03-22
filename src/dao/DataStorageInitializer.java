package dao;

import exceptions.SystemInitializationError;
import model.test.Answer;
import model.test.Question;
import model.test.Test;
import model.test.Topic;
import model.user.Role;
import model.user.User;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DataStorageInitializer {
    private static final List<File> dataFiles = new ArrayList<>(Arrays.asList(
            new File("data", "users.csv"),
            new File("data", "tests.csv"),
            new File("data", "questions.csv"),
            new File("data", "answers.csv"),
            new File("data", "statistics.csv"),
            new File("data", "topics.csv")
    ));

    private DataStorageInitializer() {
    }

    public static void clearData() {
        dataFiles.forEach(File::delete);
    }

    public static void initializeDataStorage() {
        dataFiles.stream()
                .filter(DataStorageInitializer::checkIsAvailable)
                .forEach(DataStorageInitializer::createFile);
    }

    public static boolean checkIsStorageInitialized() {
        return dataFiles.stream()
                .map(File::exists)
                .reduce((acc, el) -> acc && el)
                .orElseThrow(IllegalStateException::new);
    }

    private static boolean checkIsAvailable(File file) {
        return !(file.getParentFile().exists() && file.exists());
    }

    private static void createFile(File file) {
        File directory = file.getParentFile();
        directory.mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SystemInitializationError("Initialization error");
        }
    }

    public static void fillSampleData() {
        createDemoUsers();
        createDemoTest();
    }

    private static void createDemoTest() {
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

    private static void createDemoUsers() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("teacherDemo");
        user.setSurname("teacherSurname");
        user.setPassword("password");
        user.setLogin("teacherDemo");
        user.setRole(Role.TEACHER);
        DaoFactory.getInstance().getUserDao().save(user);
        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setName("studentDemo");
        user2.setSurname("studentName");
        user2.setPassword("password");
        user2.setLogin("login2");
        user2.setRole(Role.STUDENT);
        DaoFactory.getInstance().getUserDao().save(user2);
        User user3 = new User();
        user3.setId(UUID.randomUUID());
        user3.setName("admin");
        user3.setSurname("admin");
        user3.setPassword("nimda");
        user3.setLogin("admin");
        user3.setRole(Role.ADMIN);
        DaoFactory.getInstance().getUserDao().save(user3);
    }

}
