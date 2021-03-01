package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.result.Statistics;
import model.test.Question;
import org.apache.commons.csv.CSVRecord;

public class StatisticsRowMapper implements RowMapper<Statistics> {

    @Override
    public Statistics mapRowFromRecord(CSVRecord record) {
        return null;
    }
}
