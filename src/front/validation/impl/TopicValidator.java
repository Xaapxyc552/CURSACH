package front.validation.impl;

import front.validation.Validator;
import model.test.Topic;

public class TopicValidator implements Validator<Topic> {
    @Override
    public boolean validate(Topic model) {
        return model.getName() != null && !model.getName().isEmpty();
    }
}
