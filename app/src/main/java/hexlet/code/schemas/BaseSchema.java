package hexlet.code.schemas;

import java.util.Map;

public class BaseSchema extends SchemasMethods {
    protected boolean isRequired;
    protected boolean isHasShape;

    public BaseSchema() {
        this.isRequired = false;
        this.isHasShape = false;
    }

    public BaseSchema(boolean newIsRequired, boolean newIsHasShape) {
        this.isRequired = newIsRequired;
        this.isHasShape = newIsHasShape;
    }

    public BaseSchema required() {
        isRequired = true;
        return new BaseSchema(true, isHasShape);
    }

    public final boolean isValid(Object validatingObject) {
        if (validatingObject == null) {
            return isValidWhenNull(isRequired);
        } else if (validatingObject.getClass() == String.class) {
            return isValidStringSchema(validatingObject.toString());
        } else if (validatingObject.getClass() == Integer.class) {
            return isValidNumberSchema((int) validatingObject);
        } else if (validatingObject instanceof Map & isHasShape) {
            return isValidWithShape((Map<?, ?>) validatingObject);
        } else if (validatingObject instanceof Map & !isHasShape) {
            return isValidMapSchema((Map<?, ?>) validatingObject);
        }
        return false;
    }

    private boolean isValidWhenNull(boolean isRequiredNull) {
        return !isRequiredNull;
    }

    @Override
    protected boolean isValidStringSchema(String validatingString) {
        return false;
    }

    @Override
    protected boolean isValidNumberSchema(int validatingNumber) {
        return false;
    }

    @Override
    protected boolean isValidWithShape(Map<?, ?> validatingMap) {
        return false;
    }

    @Override
    protected boolean isValidMapSchema(Map<?, ?> validatingMap) {
        return false;
    }
}
