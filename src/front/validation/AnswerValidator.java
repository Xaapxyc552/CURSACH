package front.validation;

import front.validation.Validator;
import model.test.Answer;

public class AnswerValidator implements Validator<Answer> {
    @Override
    public boolean validate(Answer model) {
        return model.getAnswerText() != null && !model.getAnswerText().isEmpty()
                && model.getQuestion() != null;
    }
}
