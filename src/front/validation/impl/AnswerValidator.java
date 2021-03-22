package front.validation.impl;

import front.validation.ConstraintViolation;
import front.validation.Validator;
import model.test.Answer;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;

public class AnswerValidator implements Validator<Answer> {
    @Override
    public Set<ConstraintViolation> validate(Answer model) {
        Set<ConstraintViolation> violations = new HashSet<>();
        checkAnswerText(model,violations);
        checkQuestion(model,violations);
        return violations;
    }

    private void checkQuestion(Answer model, Set<ConstraintViolation> violations) {
        if (model.getQuestion() == null) {
            violations.add(new ConstraintViolation("Питання"));
        }
    }

    private void checkAnswerText(Answer model, Set<ConstraintViolation> violations) {
        if (model.getAnswerText() == null || model.getAnswerText().isEmpty()) {
            violations.add(new ConstraintViolation("Текст відповіді"));
        }
    }
}
