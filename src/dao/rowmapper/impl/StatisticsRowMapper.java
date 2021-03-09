package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.result.Statistics;
import model.test.Question;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDateTime;
import java.util.UUID;

public class StatisticsRowMapper implements RowMapper<Statistics> {

    @Override
    public Statistics mapRowFromRecord(CSVRecord record) {
        Statistics statistics = new Statistics();
        statistics.setId(UUID.fromString(record.get("UUID")));
        statistics.setDateOfStart(LocalDateTime.parse(record.get("dateOfStart")));
        statistics.setDateOfFinish(LocalDateTime.parse(record.get("dateOfFinish")));
        statistics.setTotalPoints(Double.parseDouble(record.get("totalPoints")));
        return statistics;
    }
}
