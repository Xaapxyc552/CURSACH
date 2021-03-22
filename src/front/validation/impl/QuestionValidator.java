package front.validation.impl;

import front.validation.ConstraintViolation;
import front.validation.Validator;
import model.test.Answer;
import model.test.Question;

import java.util.HashSet;
import java.util.Set;

public class QuestionValidator implements Validator<Question> {
    @Override
    public Set<ConstraintViolation> validate(Question model) {
        Set<ConstraintViolation> violations = new HashSet<>();
        checkQuestionText(model, violations);
        checkQuestionName(model, violations);
        checkAmountOfPoints(model, violations);
        return violations;
    }

    private void checkAmountOfPoints(Question model, Set<ConstraintViolation> violations) {
        if (model.getAmountOfPoints() <= 0) {
            violations.add(new ConstraintViolation("Кількість балів"));
        }
    }

    private void checkQuestionName(Question model, Set<ConstraintViolation> violations) {
        if (model.getName() == null || model.getName().isEmpty()) {
            violations.add(new ConstraintViolation("Назва питання"));
        }
    }

    private void checkQuestionText(Question model, Set<ConstraintViolation> violations) {
        if (model.getQuestionText() == null || model.getQuestionText().isEmpty()) {
            violations.add(new ConstraintViolation("Текст питання"));
        }
    }

}
