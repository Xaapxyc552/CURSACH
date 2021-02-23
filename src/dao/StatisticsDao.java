package dao;

import model.result.Statistics;
import model.user.User;

import java.util.List;

public interface StatisticsDao extends Dao<Statistics>{

    public List<Statistics> findAllForUser(User user);
}
