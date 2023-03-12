package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean isOnlyPositive;
    private boolean isHasRange;
    private int startRange;
    private int endRange;

    public NumberSchema() {
        super();
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

    public NumberSchema positive() {
        this.isOnlyPositive = true;
        return new NumberSchema(isRequired, true, isHasRange, startRange, endRange);
    }

    public void range(int newStartRange, int newEndRange) {
        isHasRange = true;
        startRange = newStartRange;
        endRange = newEndRange;
    }

    @Override
    public boolean isValidNumberSchema(Object validatingObject) {
        if (isOnlyPositive && isHasRange) {
            return (int) validatingObject >= startRange
                    & (int) validatingObject <= endRange
                    & (int) validatingObject > 0;
        } else if (isHasRange) {
            return (int) validatingObject >= startRange
                    && (int) validatingObject <= endRange;
        } else if (isOnlyPositive) {
            return (int) validatingObject > 0;
        }
        return true;
    }
}
