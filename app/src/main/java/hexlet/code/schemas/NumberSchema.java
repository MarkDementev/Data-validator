package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    private static final String ONLY_NUMBERS_CHECK_NAME = "onlyNumbersCheck";
    private static final String POSITIVE_CHECK_NAME = "positiveCheck";
    private static final String RANGE_CHECK_NAME = "rangeCheck";

    public NumberSchema() {
        addCheck(ONLY_NUMBERS_CHECK_NAME, onlyNumbersCheck());
    }

    public NumberSchema required() {
        setIsRequiredTrue();
        return this;
    }

    public NumberSchema positive() {
        addCheck(POSITIVE_CHECK_NAME, positiveCheck());
        return this;
    }

    public NumberSchema range(int newStartRange, int newEndRange) {
        addCheck(RANGE_CHECK_NAME, rangeCheck(newStartRange, newEndRange));
        return this;
    }

    private Predicate<Object> onlyNumbersCheck() {
        return p -> (p.getClass() == Integer.class);
    }

    private Predicate<Object> positiveCheck() {
        return p -> (p == null || (int) p > 0);
    }

    private Predicate<Object> rangeCheck(int startRange, int endRange) {
        return p -> ((int) p >= startRange & (int) p <= endRange);
    }
}
