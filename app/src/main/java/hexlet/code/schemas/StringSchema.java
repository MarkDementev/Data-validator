package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        addCheck("onlyStringsCheck", onlyStringsCheck());
    }

    public StringSchema(boolean isRequired, Map<String, Predicate<Object>> checks,
                        String checkToAddName, Predicate<Object> checkToAdd) {
        this.isRequired = isRequired;
        this.checks = checks;
        addCheck(checkToAddName, checkToAdd);
    }

    public StringSchema required() {
        setIsRequiredTrue();
        return new StringSchema(isRequired, checks, "minLengthCheck", minLengthCheck(1));
    }

    public StringSchema minLength(int minLength) {
        return new StringSchema(isRequired, checks, "minLengthCheck", minLengthCheck(minLength));
    }

    public StringSchema contains(String textToContains) {
        return new StringSchema(isRequired, checks, "containsCheck", containsCheck(textToContains));
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
