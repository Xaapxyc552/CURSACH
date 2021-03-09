package service.impl;

import dao.DaoFactory;
import dao.TopicDao;
import model.test.Test;
import model.test.Topic;
import service.TopicService;

import java.util.List;
import java.util.UUID;

public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao = DaoFactory.getInstance().getTopicDao();

    @Override
    public Topic getTopicById(String id) {
        return topicDao.findById(UUID.fromString(id));
    }

    @Override
    public List<Topic> findAllTopics() {
        return topicDao.findAll();
    }

    @Override
    public Topic createNewTopic(Topic topic) {
        return topicDao.save(topic);
    }

}
