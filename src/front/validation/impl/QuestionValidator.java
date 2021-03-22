package front.validation.impl;

import front.validation.Validator;
import model.test.Question;

public class QuestionValidator implements Validator<Question> {
    @Override
    public boolean validate(Question model) {
        return model.getName() != null && !model.getName().isEmpty() &&
                model.getQuestionText() != null && !model.getQuestionText().isEmpty() &&
                model.getAmountOfPoints() > 0;
    }
}
