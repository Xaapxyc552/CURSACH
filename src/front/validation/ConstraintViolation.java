package front.validation;

public class ConstraintViolation {

    public ConstraintViolation(String field) {
        this.field = field;
    }

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}
