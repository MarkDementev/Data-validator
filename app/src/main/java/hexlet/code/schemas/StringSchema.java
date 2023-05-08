package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    private static final String ONLY_STRINGS_CHECK_NAME = "onlyStringsCheck";
    private static final String MIN_LENGTH_CHECK_NAME = "minLengthCheck";
    private static final String CONTAINS_CHECK_NAME = "containsCheck";

    public StringSchema() {
        addCheck(ONLY_STRINGS_CHECK_NAME, onlyStringsCheck());
    }

    public StringSchema required() {
        setIsRequiredTrue();
        addCheck(ONLY_STRINGS_CHECK_NAME, minLengthCheck(1));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCheck(MIN_LENGTH_CHECK_NAME, minLengthCheck(minLength));
        return this;
    }

    public StringSchema contains(String textToContains) {
        addCheck(CONTAINS_CHECK_NAME, containsCheck(textToContains));
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
