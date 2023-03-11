package hexlet.code.schemas;

public class StringSchema {
    private boolean isRequired;
    private String containsText;

    public StringSchema() {
        this.isRequired = false;
    }

    public StringSchema(String containsText, boolean isRequired) {
        this.containsText = containsText;
        this.isRequired = isRequired;
    }

    public void required() {
        isRequired = true;
    }

    public StringSchema contains(String newContainsText) {
        this.containsText = newContainsText;
        return new StringSchema(containsText, isRequired);
    }

    public boolean isValid(Object validatingObject) {
        if (validatingObject == null) {
            return isValidValidatingObjectNull(isRequired);
        }

        if (containsText != null) {
            return validatingObject.toString().contains(containsText);
        }

        if (validatingObject.getClass() == String.class && validatingObject.toString().length() > 0) {
            return true;
        }
        return false;
    }

    private boolean isValidValidatingObjectNull(boolean isRequiredNull) {
        return !isRequiredNull;
    }
}
