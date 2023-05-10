package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        addCheck("onlyStringsCheck", onlyStringsCheck());
    }

    public StringSchema required() {
        setIsRequiredTrue();
        addCheck("onlyStringsCheck", minLengthCheck(1));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCheck("minLengthCheck", minLengthCheck(minLength));
        return this;
    }

    public StringSchema contains(String textToContains) {
        addCheck("containsCheck", containsCheck(textToContains));
        return this;
    }

    private Predicate<Object> onlyStringsCheck() {
        return p -> (p.getClass() == String.class);
    }

    private Predicate<Object> minLengthCheck(int minLength) {
        return p -> (p.toString().length() >= minLength);
    }

    private Predicate<Object> containsCheck(String textToContains) {
        return p -> (p.toString().contains(textToContains));
    }
}
