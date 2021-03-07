package service;

import model.test.Topic;

import java.util.List;

public interface TopicService extends Service{
    Topic getTopicById(String id);
    List<Topic> findAllTopics();
}
