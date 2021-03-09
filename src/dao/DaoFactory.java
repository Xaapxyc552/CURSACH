package dao;

import dao.impl.*;

public class DaoFactory {

    private static DaoFactory daoFactory;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public TestDao getTestDao() {
        return new TestDaoImpl();
    }

    public AnswerDao getAnswerDao() {
        return new AnswerDaoImpl();
    }

    public QuestionDao getQuestionDao() {
        return new QuestionDaoImpl();
    }

    public TopicDao getTopicDao() {
        return new TopicDaoImpl();
    }

    public StatisticsDao getStatisticDao() {
        return new StatisticsDaoImpl();
    }

}


