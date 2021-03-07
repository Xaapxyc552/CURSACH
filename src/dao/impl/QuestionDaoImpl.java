package dao.impl;

import dao.*;
import dao.rowmapper.RowMapper;
import dao.rowmapper.impl.QuestionRowMapper;
import model.test.Question;
import model.test.Test;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class QuestionDaoImpl extends AbstractDao <Question> implements QuestionDao {
    private File dataFile = new File("data", "questions.csv");

    @Override
    public List<Question> getAllQuestionsForTest(Test test) {
        Predicate<CSVRecord> predicate = n -> n.get("testId").equals(test.getId().toString());
        return findMultipleModelsByPredicate(predicate);
    }

    @Override
    public Question getQuestionByName(String name) {
        Predicate<CSVRecord> predicate = n -> n.get("name").equals(name);
        return findSingleModelByPredicate(predicate);
    }

    @Override
    protected RowMapper<Question> getRowMapper() {
        return new QuestionRowMapper();
    }

    @Override
    protected boolean checkEqualsByUniqueFields(Question existingModel, Question modelToCheck) {
        return existingModel.getId().equals(modelToCheck.getId());
    }

    @Override
    protected String[] getModelHeaders() {
        return new String[]{"UUID","testId","name","questionText","amountOfPoints"};
    }

    @Override
    protected File getDataFile() {
        return dataFile;
    }

    @Override
    protected List<String> getModelData(Question model) {
        ArrayList<String> data = new ArrayList<>();
        data.add(model.getId().toString());
        data.add(model.getTest().getId().toString());
        data.add(model.getName());
        data.add(model.getQuestionText());
        data.add(String.valueOf(model.getAmountOfPoints()));
        return data;
    }
}
