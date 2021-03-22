package front.validation.impl;

import front.validation.ConstraintViolation;
import front.validation.Validator;
import model.test.Question;
import model.test.Test;

import java.util.HashSet;
import java.util.Set;

public class TestValidator implements Validator<Test> {
    @Override
    public Set<ConstraintViolation> validate(Test model) {
        Set<ConstraintViolation> violations = new HashSet<>();
        checkName(model, violations);
        checkTopic(model, violations);
        checkTimeForTest(model, violations);
        return violations;
    }

    private void checkName(Test model, Set<ConstraintViolation> violations) {
        if (model.getName() == null || model.getName().isEmpty()) {
            violations.add(new ConstraintViolation("Назва тесту"));
        }
    }

    private void checkTimeForTest(Test model, Set<ConstraintViolation> violations) {
        if (model.getTimeForTest() == null || model.getTimeForTest().isNegative() ||
                model.getTimeForTest().toMinutes() < 1) {
            violations.add(new ConstraintViolation("Час для тесту"));
        }
    }

    private void checkTopic(Test model, Set<ConstraintViolation> violations) {
        if (model.getTopic() == null) {
            violations.add(new ConstraintViolation("Тема"));
        }

    }
}
