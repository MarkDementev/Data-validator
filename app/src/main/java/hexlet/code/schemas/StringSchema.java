package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String containsText;
    private int minLength;

    public StringSchema() {
        super();
        this.minLength = 0;
    }

    public StringSchema(boolean isRequired, String containsText, int minLength) {
        this.isRequired = isRequired;
        this.containsText = containsText;
        this.minLength = minLength;
    }

    public StringSchema(boolean isRequired, String containsText, int minLength, boolean isHasShape) {
        this.isRequired = isRequired;
        this.containsText = containsText;
        this.minLength = minLength;
        this.isHasShape = isHasShape;
    }

    public StringSchema contains(String newContainsText) {
        this.containsText = newContainsText;
        return new StringSchema(isRequired, containsText, minLength);
    }

    public StringSchema minLength(int newMinLength) {
        this.minLength = newMinLength;
        return new StringSchema(isRequired, containsText, minLength);
    }

    @Override
    public boolean isValidStringSchema(String validatingString) {
        if (containsText != null) {
            return validatingString.contains(containsText);
        }
        return validatingString.length() > minLength;
    }

    @Override
    public StringSchema required() {
        isRequired = true;
        return new StringSchema(true, containsText, minLength, isHasShape);
    }
}
