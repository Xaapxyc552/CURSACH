package dao.impl;

import dao.AbstractDao;
import dao.TestDao;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.TestRowMapper;
import model.test.Test;
import model.user.User;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TestDaoImpl extends AbstractDao<Test> implements TestDao {
    private File dataFile = new File("data", "tests.csv");

    @Override
    public Test findTestByName(String name) {
        Predicate<CSVRecord> predicate = n -> n.get("name").equals(name);
        return findSingleModelByPredicate(predicate);
    }

    @Override
    protected RowMapper<Test> getRowMapper() {
        return new TestRowMapper();
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[]{"UUID","name","topic","timeForTest","maximumPoints"};
    }

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected List<String> getModelData(Test model) {
        List<String> data = new ArrayList<>();
        data.add(model.getId().toString());
        data.add(model.getName());
        data.add(model.getTopic());
        data.add(model.getTimeForTest().toString());
        data.add(String.valueOf(model.getMaximumPoints()));
        return data;
    }

}
