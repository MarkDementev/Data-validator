package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    protected Map<String, Predicate<Object>> checks = new LinkedHashMap<>();
    protected boolean isRequired;

    public BaseSchema() {
        this.isRequired = false;
    }

    public BaseSchema(boolean isRequired, Map<String, Predicate<Object>> checks) {
        this.isRequired = isRequired;
        this.checks = checks;
    }

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
