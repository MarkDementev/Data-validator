package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private String containsText;
    private int minLength;

    public StringSchema() {
        super();
        this.minLength = 0;
    }

    public StringSchema(boolean newIsRequired, String newContainsText, int newMinLength, boolean newIsHasShape) {
        this.isRequired = newIsRequired;
        this.containsText = newContainsText;
        this.minLength = newMinLength;
        this.isHasShape = newIsHasShape;
    }

    public StringSchema contains(String newContainsText) {
        containsText = newContainsText;
        return new StringSchema(isRequired, containsText, minLength, isHasShape);
    }

    public StringSchema minLength(int newMinLength) {
        minLength = newMinLength;
        return new StringSchema(isRequired, containsText, minLength, isHasShape);
    }

    public boolean isValidStringSchema(String validatingString) {
        if (containsText != null) {
            return validatingString.contains(containsText) & validatingString.length() >= minLength;
        }
        return validatingString.length() >= minLength;
    }

    @Override
    public StringSchema required() {
        isRequired = true;
        minLength = 1;
        return new StringSchema(true, containsText, minLength, isHasShape);
    }
}
