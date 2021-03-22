package front.validation;

import model.Model;

import java.util.Set;

public interface Validator<T extends Model> {
    Set<ConstraintViolation> validate(T model);
}
