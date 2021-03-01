package dao.impl;

import dao.AbstractDao;
import dao.TestDao;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.TestRowMapper;
import model.test.Test;
import model.user.User;

import java.io.File;
import java.util.List;

public class TestDaoImpl extends AbstractDao<Test> implements TestDao {
    private File dataFile = new File("data", "tests.csv");

    @Override
    public Test findTestByName(String name) {
        return null;
    }

    @Override
    protected RowMapper<Test> getRowMapper() {
        return new TestRowMapper();
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
    protected List<String> getModelData(Test model) {
        return null;
    }

}
