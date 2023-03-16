package hexlet.code.schemas;

import java.util.Map;

public class BaseSchema {
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

    boolean isValidStringSchema(String validatingString) {
        return false;
    }

    boolean isValidNumberSchema(int validatingNumber) {
        return false;
    }

    boolean isValidWithShape(Map<?, ?> validatingMap) {
        return false;
    }

    boolean isValidMapSchema(Map<?, ?> validatingMap) {
        return false;
    }
}
