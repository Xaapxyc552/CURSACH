package dao.rowmapper.impl;

import dao.rowmapper.RowMapper;
import model.test.Topic;
import org.apache.commons.csv.CSVRecord;

import java.util.UUID;

public class TopicRowMapper implements RowMapper<Topic> {
    @Override
    public Topic mapRowFromRecord(CSVRecord record) {
        Topic topic = new Topic();
        topic.setId(UUID.fromString(record.get("UUID")));
        topic.setName(record.get("name"));
        return topic;
    }
}
