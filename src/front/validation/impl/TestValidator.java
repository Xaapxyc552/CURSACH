package front.validation.impl;

import front.validation.Validator;
import model.test.Test;

public class TestValidator implements Validator<Test> {
    @Override
    public boolean validate(Test model) {
        return model.getName() != null && !model.getName().isEmpty() &&
                 model.getTopic() != null &&
                 model.getTimeForTest() != null && !model.getTimeForTest().isNegative();
    }
}
