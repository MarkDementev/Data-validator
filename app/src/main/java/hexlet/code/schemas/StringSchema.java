package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String containsText;

    public StringSchema() {
        super();
    }

    public StringSchema(boolean isRequired, String containsText) {
        this.isRequired = isRequired;
        this.containsText = containsText;
    }

    public StringSchema contains(String newContainsText) {
        this.containsText = newContainsText;
        return new StringSchema(isRequired, containsText);
    }

    @Override
    public boolean isValidStringSchema(Object validatingObject) {
        if (containsText != null) {
            return validatingObject.toString().contains(containsText);
        }
        return validatingObject.toString().length() > 0;
    }
}
