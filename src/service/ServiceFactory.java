package service;

import service.impl.*;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public LoginService getLoginService() {
        return new LoginServiceImpl();
    }

    public UserService getUserService() {
        return new UserServiceImpl();
    }

    public TestService getTestService() {
        return new TestServiceImpl();
    }

    public QuestionService getQuestionService() {
        return new QuestionServiceImpl();
    }

    public AnswerService getAnswerService() {
        return new AnswerServiceImpl();
    }

    public TopicService getTopicService() {
     return new TopicServiceImpl();
    }
}
