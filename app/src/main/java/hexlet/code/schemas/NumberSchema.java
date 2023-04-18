package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    private static final String ONLY_NUMBERS_CHECK_NAME = "onlyNumbersCheck";
    private static final String POSITIVE_CHECK_NAME = "positiveCheck";
    private static final String RANGE_CHECK_NAME = "rangeCheck";

    public NumberSchema() {
        addCheck(ONLY_NUMBERS_CHECK_NAME, onlyNumbersCheck());
    }

    public NumberSchema(boolean isRequired, Map<String, Predicate<Object>> checks,
                        String checkToAddName, Predicate<Object> checkToAdd) {
        super(isRequired, checks);
        addCheck(checkToAddName, checkToAdd);
    }

    NumberSchema required() {
        setIsRequiredTrue();
        return new NumberSchema(isRequired, new LinkedHashMap<>(),
                ONLY_NUMBERS_CHECK_NAME, onlyNumbersCheck());
    }

    NumberSchema positive() {
        return new NumberSchema(isRequired, checks,
                POSITIVE_CHECK_NAME, positiveCheck());
    }

    NumberSchema range(int newStartRange, int newEndRange) {
        return new NumberSchema(isRequired, checks,
                RANGE_CHECK_NAME, rangeCheck(newStartRange, newEndRange));
    }

    private Predicate<Object> onlyNumbersCheck() {
        return p -> (p.getClass() == Integer.class);
    }

    private Predicate<Object> positiveCheck() {
        return p -> ((int) p > 0);
    }

    private Predicate<Object> rangeCheck(int startRange, int endRange) {
        return p -> ((int) p >= startRange & (int) p <= endRange);
    }
}
