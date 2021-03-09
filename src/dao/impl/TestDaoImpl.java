package dao.impl;

import dao.AbstractDao;
import dao.DaoFactory;
import dao.TestDao;
import dao.TopicDao;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.TestRowMapper;
import exceptions.ModelNotFoundException;
import model.result.Statistics;
import model.test.Test;
import model.test.Topic;
import model.user.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestDaoImpl extends AbstractDao<Test> implements TestDao {
    private File dataFile = new File("data", "tests.csv");
    private TopicDao topicDao = DaoFactory.getInstance().getTopicDao();

    @Override
    public Test findTestByName(String name) {
        Predicate<CSVRecord> predicate = n -> n.get("name").equals(name);
        return findSingleModelByPredicate(predicate);
    }

    @Override
    public Topic findTopicForTest(Test test) {
        File f = getDataFile();
        String topicId;
        try (CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.DEFAULT.withHeader(getModelHeaders()))) {
            List<CSVRecord> list = parser.getRecords();
            topicId = list.stream()
                    .filter(n -> n.get("UUID").equals(test.getId().toString()))
                    .map(n -> n.get("topicId"))
                    .findFirst()
                    .orElseThrow(ModelNotFoundException::new);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected exception");
        }
        return topicDao.findById(UUID.fromString(topicId));
    }



    @Override
    protected RowMapper<Test> getRowMapper() {
        return new TestRowMapper();
    }

    @Override
    protected boolean checkEqualsByUniqueFields(Test existingModel, Test modelToCheck) {
        return existingModel.getId().equals(modelToCheck.getId());
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[]{"UUID","name","topicId","timeForTest","maximumPoints"};
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
        data.add(String.valueOf(model.getTopic().getId()));
        data.add(model.getTimeForTest().toString());
        data.add(String.valueOf(model.getMaximumPoints()));
        return data;
    }

}
