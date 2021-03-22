package front.validation.impl;

import front.validation.ConstraintViolation;
import front.validation.Validator;
import model.test.Topic;

import java.util.HashSet;
import java.util.Set;

public class TopicValidator implements Validator<Topic> {
    @Override
    public Set<ConstraintViolation> validate(Topic model) {
        Set<ConstraintViolation> violations = new HashSet<>();
        checkName(model, violations);
        return violations;
    }

    private void checkName(Topic model, Set<ConstraintViolation> violations) {
        if ( model.getName() == null || model.getName().isEmpty()) {
            violations.add(new ConstraintViolation("Назва"));
        }
    }


}
