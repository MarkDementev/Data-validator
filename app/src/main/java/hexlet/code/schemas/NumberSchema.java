package hexlet.code.schemas;

public class NumberSchema {
    private boolean isRequired;
    private boolean isOnlyPositive;
    private boolean isHasRange;
    private int startRange;
    private int endRange;

    public NumberSchema() {
        this.isRequired = false;
        this.isOnlyPositive = false;
        this.isHasRange = false;
        this.startRange = 0;
        this.endRange = 0;
    }

    public NumberSchema(boolean isRequired, boolean isOnlyPositive, boolean isHasRange, int startRange, int endRange) {
        this.isRequired = isRequired;
        this.isOnlyPositive = isOnlyPositive;
        this.isHasRange = isHasRange;
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public void required() {
        isRequired = true;
    }

    public NumberSchema positive() {
        this.isOnlyPositive = true;
        return new NumberSchema(isRequired, true, isHasRange, startRange, endRange);
    }

    public void range(int newStartRange, int newEndRange) {
        isHasRange = true;
        startRange = newStartRange;
        endRange = newEndRange;
    }

    public boolean isValid(Object validatingObject) {
        if (validatingObject == null) {
            return isValidValidatingObjectNull(isRequired);
        }

        if (isOnlyPositive && isHasRange) {
            return validatingObject.getClass() == Integer.class
                    && (int) validatingObject >= startRange
                    && (int) validatingObject <= endRange
                    && (int) validatingObject > 0;
        } else if (isHasRange) {
            return validatingObject.getClass() == Integer.class
                    & (int) validatingObject >= startRange
                    & (int) validatingObject <= endRange;
        } else if (isOnlyPositive) {
            return validatingObject.getClass() == Integer.class
                    && (int) validatingObject > 0;
        }
        return validatingObject.getClass() == Integer.class;
    }

    private boolean isValidValidatingObjectNull(boolean isRequiredNull) {
        return !isRequiredNull;
    }
}
