package dao.impl;

import dao.*;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.StatisticsRowMapper;
import model.result.Statistics;
import model.user.User;

import java.io.File;
import java.util.List;

public class StatisticsDaoImpl extends AbstractDao<Statistics> implements StatisticsDao {
private File dataFile = new File("data", "statistics.csv");

    private TestDao testDao = DaoFactory.getInstance().getTestDao();
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public List<Statistics> findAllForUser(User user) {
        return null;
    }

    @Override
    protected RowMapper<Statistics> getRowMapper() {
        return new StatisticsRowMapper();
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[0];
    }

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected List<String> getModelData(Statistics model) {
        return null;
    }
}
