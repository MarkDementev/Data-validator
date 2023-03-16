package hexlet.code.schemas;

import java.util.Map;

public abstract class SchemasMethods {
    protected abstract boolean isValidStringSchema(String validatingString);
    protected abstract boolean isValidNumberSchema(int validatingNumber);
    protected abstract boolean isValidWithShape(Map<?, ?> validatingMap);
    protected abstract boolean isValidMapSchema(Map<?, ?> validatingMap);
}
