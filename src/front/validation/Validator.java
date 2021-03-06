package front.validation;

import model.Model;

public interface Validator<T extends Model> {
    boolean validate(T model);
}
