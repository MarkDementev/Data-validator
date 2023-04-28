package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected Map<String, Predicate<Object>> checks = new LinkedHashMap<>();
    protected boolean isRequired;

    public abstract BaseSchema required();

    public final void setIsRequiredTrue() {
        isRequired = true;
    }

    public final boolean isValid(Object validatingObject) {
        if (validatingObject == null) {
            return !isRequired;
        }

        for (Map.Entry<String, Predicate<Object>> check : checks.entrySet()) {
            if (!check.getValue().test(validatingObject)) {
                return false;
            }
        }
        return true;
    }

    protected final void addCheck(String checkToAddName, Predicate<Object> checkToAdd) {
        checks.put(checkToAddName, checkToAdd);
    }
}
