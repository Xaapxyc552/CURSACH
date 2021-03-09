package dao.impl;

import dao.AbstractDao;
import dao.rowmapper.RowMapper;
import dao.TopicDao;
import dao.rowmapper.impl.TopicRowMapper;
import model.test.Test;
import model.test.Topic;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TopicDaoImpl extends AbstractDao<Topic> implements TopicDao {
    private File dataFile = new File("data", "topics.csv");

    @Override
    protected RowMapper<Topic> getRowMapper() {
        return new TopicRowMapper();
    }

    @Override
    protected boolean checkEqualsByUniqueFields(Topic existingModel, Topic modelToCheck) {
        return existingModel.getId().equals(modelToCheck.getId()) ||
                existingModel.getName().equals(modelToCheck.getName());
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[]{"UUID","name"};
    }

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected List<String> getModelData(Topic model) {
        ArrayList<String> data = new ArrayList<>();
        data.add(model.getId().toString());
        data.add(model.getName());
        return data;
    }


}
