package service;

import model.test.Test;
import model.test.Topic;

import java.util.List;

public interface TopicService extends Service{
    Topic getTopicById(String id);
    List<Topic> findAllTopics();
    Topic createNewTopic(Topic topic);
}
