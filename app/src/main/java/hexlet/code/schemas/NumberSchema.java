package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        addCheck("onlyNumbersCheck", onlyNumbersCheck());
    }

    public NumberSchema required() {
        setIsRequiredTrue();
        return this;
    }

    public NumberSchema positive() {
        addCheck("positiveCheck", positiveCheck());
        return this;
    }

    public NumberSchema range(int newStartRange, int newEndRange) {
        addCheck("rangeCheck", rangeCheck(newStartRange, newEndRange));
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
