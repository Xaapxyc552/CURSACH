package dao.impl;

import dao.*;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.StatisticsRowMapper;
import model.result.Statistics;
import model.user.User;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StatisticsDaoImpl extends AbstractDao<Statistics> implements StatisticsDao {
private File dataFile = new File("data", "statistics.csv");
    @Override
    public List<Statistics> findAllForUser(User user) {
        Predicate<CSVRecord> predicate = n -> n.get("userId").equals(user.getId().toString());
        return findMultipleModelsByPredicate(predicate);
    }

    @Override
    protected RowMapper<Statistics> getRowMapper() {
        return new StatisticsRowMapper();
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[]{"UUID","userId","testId","dateOfStart","dateOfFinish","totalPoints"};
    }

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected List<String> getModelData(Statistics model) {
        ArrayList<String> data = new ArrayList<>();
        data.add(model.getId().toString());
        data.add(model.getUser().getId().toString());
        data.add(model.getTest().getId().toString());
        data.add(model.getDateOfStart().toString());
        data.add(model.getDateOfFinish().toString());
        data.add(String.valueOf(model.getTotalPoints()));
        return data;
    }
}
