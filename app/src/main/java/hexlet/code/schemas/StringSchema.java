package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String containsText;
    private int minLength;

    public StringSchema() {
        super();
        this.minLength = 0;
    }

    public StringSchema(boolean isRequired, String containsText, int minLength, boolean isHasShape) {
        this.isRequired = isRequired;
        this.containsText = containsText;
        this.minLength = minLength;
        this.isHasShape = isHasShape;
    }

    public StringSchema contains(String newContainsText) {
        containsText = newContainsText;
        return new StringSchema(isRequired, containsText, minLength, isHasShape);
    }

    public StringSchema minLength(int newMinLength) {
        minLength = newMinLength;
        return new StringSchema(isRequired, containsText, minLength, isHasShape);
    }

    @Override
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
