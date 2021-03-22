package front.validation.impl;

import front.validation.ConstraintViolation;
import front.validation.Validator;
import model.Model;
import model.user.User;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class UserValidator implements Validator<User> {
    @Override
    public Set<ConstraintViolation> validate(User model) {
        Set<ConstraintViolation> violations = new HashSet<>();
        checkName(model,violations);
        checkPassword(model,violations);
        checkSurname(model,violations);
        checkLogin(model,violations);
        checkRole(model,violations);

        return violations;
    }

    private void checkRole(User model, Set<ConstraintViolation> violations) {
        if (model.getRole() == null) {
            violations.add(new ConstraintViolation("Роль"));
        }
    }

    private void checkLogin(User model, Set<ConstraintViolation> violations) {
        if (model.getLogin() == null || model.getLogin().isEmpty()) {
            violations.add(new ConstraintViolation("Логін"));
        }
    }

    private void checkSurname(User model, Set<ConstraintViolation> violations) {
        if (model.getSurname() == null || model.getSurname().isEmpty()) {
            violations.add(new ConstraintViolation("Прізвище"));
        }
    }

    private void checkPassword(User model, Set<ConstraintViolation> violations) {
        if (model.getPassword() == null || model.getPassword().isEmpty()
        || model.getPassword().length()<8) {
            violations.add(new ConstraintViolation("Пароль"));
        }
    }

    private void checkName(User model, Set<ConstraintViolation> violations) {
        if (model.getName() == null || model.getName().isEmpty()) {
            violations.add(new ConstraintViolation("Ім'я"));
        }

    }
}
