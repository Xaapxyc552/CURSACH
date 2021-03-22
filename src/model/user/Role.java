package model.user;

import java.util.EnumSet;

public enum Role {
    STUDENT("Студент"),TEACHER("Викладач"),ADMIN("Адмін");

    private String ukrValue;

    Role(String ukrValue) {
        this.ukrValue = ukrValue;
    }

    public String getUkrValue() {
        return ukrValue;
    }

    public static Role getRoleForUkrValue(String ukrValue) {
        return EnumSet.allOf(Role.class).stream()
                .filter(n -> n.getUkrValue().equals(ukrValue))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There cannot be other roles than provided"));
    }

}
