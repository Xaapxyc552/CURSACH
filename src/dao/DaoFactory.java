package dao;

import dao.impl.AnswerDaoImpl;
import dao.impl.QuestionDaoImpl;
import dao.impl.TestDaoImpl;
import dao.impl.UserDaoImpl;

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

}


