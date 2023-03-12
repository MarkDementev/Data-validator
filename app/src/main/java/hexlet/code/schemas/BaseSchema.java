package hexlet.code.schemas;

public class BaseSchema {
    protected boolean isRequired;

    public BaseSchema() {
        this.isRequired = false;
    }

    public void required() {
        isRequired = true;
    }

    public boolean isValid(Object validatingObject) {
        if (validatingObject == null) {
            return isValidWhenNull(isRequired);
        } else if (validatingObject.getClass() == String.class) {
            return isValidStringSchema(validatingObject);
        } else if (validatingObject.getClass() == Integer.class) {
            return isValidNumberSchema(validatingObject);
        }
        return false;
    }

    private boolean isValidWhenNull(boolean isRequiredNull) {
        return !isRequiredNull;
    }

    public boolean isValidStringSchema(Object validatingObject) {
        return false;
    }

    public boolean isValidNumberSchema(Object validatingObject) {
        return false;
    }
}
