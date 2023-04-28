package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    private static final String ONLY_STRINGS_CHECK_NAME = "onlyStringsCheck";
    private static final String MIN_LENGTH_CHECK_NAME = "minLengthCheck";
    private static final String CONTAINS_CHECK_NAME = "containsCheck";

    public StringSchema() {
        addCheck(ONLY_STRINGS_CHECK_NAME, onlyStringsCheck());
    }

    public StringSchema(boolean isRequired, Map<String, Predicate<Object>> checks,
                        String checkToAddName, Predicate<Object> checkToAdd) {
        super(isRequired, checks);
        addCheck(checkToAddName, checkToAdd);
    }

    public StringSchema required() {
        return new StringSchema(true, checks, MIN_LENGTH_CHECK_NAME, minLengthCheck(1));
    }

    public StringSchema minLength(int minLength) {
        return new StringSchema(isRequired, checks, MIN_LENGTH_CHECK_NAME, minLengthCheck(minLength));
    }

    public StringSchema contains(String textToContains) {
        return new StringSchema(isRequired, checks, CONTAINS_CHECK_NAME, containsCheck(textToContains));
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
