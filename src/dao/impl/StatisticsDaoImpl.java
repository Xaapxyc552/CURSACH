package dao.impl;

import dao.DaoFactory;
import dao.StatisticsDao;
import dao.TestDao;
import dao.UserDao;
import model.result.Statistics;
import model.user.User;

import java.util.List;

public class StatisticsDaoImpl implements StatisticsDao {

    private TestDao testDao = DaoFactory.getInstance().getTestDao();
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public Statistics save(Statistics model) {
        return null;
    }

    @Override
    public Statistics update(Statistics model) {
        return null;
    }

    @Override
    public void delete(Statistics model) {

    }

    @Override
    public List<Statistics> findAll() {
        return null;
    }

    @Override
    public Statistics findById(long id) {
        return null;
    }


    @Override
    public List<Statistics> findAllForUser(User user) {
        return null;
    }
}
