package hexlet.code.schemas;

import hexlet.code.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {
    private static final int FIRST_VALIDATING_NEGATIVE_INT = -100;
    private static final int SECOND_VALIDATING_NEGATIVE_INT = -1;
    private static final int VALIDATING_INT = 3;
    private static final int FIRST_RANGE_BORDER = 0;
    private static final int SECOND_RANGE_BORDER = 5;
    private static final int THIRD_RANGE_BORDER = -2;
    private static final boolean BOOLEAN_NUMBER_VALIDATING_OBJECT = false;
    private static final String STRING_NUMBER_VALIDATING_OBJECT = "100";
    private static final double DOUBLE_NUMBER_VALIDATING_OBJECT = 100.0;
    private Validator testValidator = new Validator();
    private NumberSchema testSchema;

    @BeforeEach
    public void makeNumberSchema() {
        testSchema = testValidator.number();
    }

    @Test
    public void testWithoutRequired() {
        boolean numberSchemaValidationResult = testSchema
                .isValid(null);
        assertThat(numberSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testAfterRequired() {
        boolean numberSchemaValidationResult = testSchema
                .required()
                .isValid(null);
        assertThat(numberSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testWithoutPositive() {
        boolean numberSchemaValidationResult = testSchema
                .isValid(FIRST_VALIDATING_NEGATIVE_INT);
        assertThat(numberSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testPositive() {
        boolean numberSchemaValidationResult = testSchema
                .positive()
                .isValid(FIRST_VALIDATING_NEGATIVE_INT);
        assertThat(numberSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testBooleanArgument() {
        boolean numberSchemaValidationResult = testSchema
                .isValid(BOOLEAN_NUMBER_VALIDATING_OBJECT);
        assertThat(numberSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testStringArgument() {
        boolean numberSchemaValidationResult = testSchema
                .isValid(STRING_NUMBER_VALIDATING_OBJECT);
        assertThat(numberSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testDoubleArgument() {
        boolean numberSchemaValidationResult = testSchema
                .isValid(DOUBLE_NUMBER_VALIDATING_OBJECT);
        assertThat(numberSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testWithRangeTrue() {
        boolean numberSchemaValidationResult = testSchema
                .range(FIRST_RANGE_BORDER, SECOND_RANGE_BORDER)
                .isValid(VALIDATING_INT);
        assertThat(numberSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testWithRangeFalse() {
        boolean numberSchemaValidationResult = testSchema
                .positive()
                .range(THIRD_RANGE_BORDER, SECOND_RANGE_BORDER)
                .isValid(SECOND_VALIDATING_NEGATIVE_INT);
        assertThat(numberSchemaValidationResult).isEqualTo(false);
    }
}
