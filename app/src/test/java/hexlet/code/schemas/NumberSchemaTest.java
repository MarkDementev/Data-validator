package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberSchemaTest {
    private static final int ZERO = 0;
    private static final int FIRST_VALIDATING_NEGATIVE_INT = -100;
    private static final int SECOND_VALIDATING_NEGATIVE_INT = -50;
    private static final int THIRD_VALIDATING_NEGATIVE_INT = -1;
    private static final int FIRST_VALIDATING_INT = 100;
    private static final int SECOND_VALIDATING_INT = 3;
    private static final int THIRD_VALIDATING_INT = 6;
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
    public void testIsValidAfterBeforeRequired() {
        assertThat(testSchema.isValid(null)).isEqualTo(true);
        assertThat(testSchema.positive().isValid(null)).isEqualTo(true);
        testSchema.required();
        assertThat(testSchema.positive().isValid(null)).isEqualTo(false);
        assertThat(testSchema.isValid(null)).isEqualTo(false);
    }

    @Test
    public void testIsValidAfterBeforePositive() {
        assertThat(testSchema.isValid(FIRST_VALIDATING_NEGATIVE_INT)).isEqualTo(true);
        testSchema.positive();
        assertThat(testSchema.isValid(FIRST_VALIDATING_NEGATIVE_INT)).isEqualTo(false);
    }

    @Test
    public void testIsValidIntArgument() {
        assertThat(testSchema.isValid(FIRST_VALIDATING_INT)).isEqualTo(true);
        assertThat(testSchema.isValid(SECOND_VALIDATING_NEGATIVE_INT)).isEqualTo(true);
        testSchema.positive();
        assertThat(testSchema.isValid(FIRST_VALIDATING_INT)).isEqualTo(true);
        assertThat(testSchema.isValid(SECOND_VALIDATING_NEGATIVE_INT)).isEqualTo(false);
        assertThat(testSchema.isValid(ZERO)).isEqualTo(false);
    }

    @Test
    public void testIsValidNotIntArgument() {
        assertThat(testSchema.isValid(BOOLEAN_NUMBER_VALIDATING_OBJECT)).isEqualTo(false);
        assertThat(testSchema.isValid(STRING_NUMBER_VALIDATING_OBJECT)).isEqualTo(false);
        assertThat(testSchema.isValid(DOUBLE_NUMBER_VALIDATING_OBJECT)).isEqualTo(false);
    }

    @Test
    public void testIsValidWithRange() {
        testSchema.range(FIRST_RANGE_BORDER, SECOND_RANGE_BORDER);
        assertThat(testSchema.isValid(SECOND_VALIDATING_INT)).isEqualTo(true);
        assertThat(testSchema.isValid(THIRD_VALIDATING_INT)).isEqualTo(false);
        testSchema.positive();
        testSchema.range(THIRD_RANGE_BORDER, SECOND_RANGE_BORDER);
        assertThat(testSchema.isValid(THIRD_VALIDATING_NEGATIVE_INT)).isEqualTo(false);
    }
}
