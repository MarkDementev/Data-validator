package hexlet.code.schemas;

import hexlet.code.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest {
    private static final String EMPTY_STRING = "";
    private static final String FIRST_VALIDATING_STRING = "Mark";
    private static final String SECOND_VALIDATING_STRING = "Dementev";
    private static final String FIRST_CONTAINS_STRING = "De";
    private static final String SECOND_CONTAINS_STRING = "Deme";
    private static final int INT_VALIDATING_OBJECT = 10;
    private static final int MIN_LENGTH = 5;
    private static final boolean BOOLEAN_VALIDATING_OBJECT = true;
    private static final double DOUBLE_VALIDATING_OBJECT = 7.0;
    private Validator testValidator = new Validator();
    private StringSchema testSchema;

    @BeforeEach
    public void makeStringSchema() {
        testSchema = testValidator.string();
    }

    @Test
    public void testWithoutRequired() {
        boolean stringSchemaValidationResult = testSchema
                .isValid(null);
        assertThat(stringSchemaValidationResult).isTrue();
    }

    @Test
    public void testAfterRequired() {
        boolean stringSchemaValidationResult = testSchema
                .required()
                .isValid(null);
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testEmptyStringWithoutRequired() {
        boolean stringSchemaValidationResult = testSchema
                .isValid(EMPTY_STRING);
        assertThat(stringSchemaValidationResult).isTrue();
    }

    @Test
    public void testEmptyStringAfterRequired() {
        boolean stringSchemaValidationResult = testSchema
                .required()
                .isValid(EMPTY_STRING);
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testBooleanArgument() {
        boolean stringSchemaValidationResult = testSchema
                .isValid(BOOLEAN_VALIDATING_OBJECT);
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testIntArgument() {
        boolean stringSchemaValidationResult = testSchema
                .isValid(INT_VALIDATING_OBJECT);
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testDoubleArgument() {
        boolean stringSchemaValidationResult = testSchema
                .isValid(DOUBLE_VALIDATING_OBJECT);
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testMinLengthFalse() {
        boolean stringSchemaValidationResult = testSchema
                .minLength(MIN_LENGTH)
                .isValid(FIRST_VALIDATING_STRING);
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testMinLengthTrue() {
        boolean stringSchemaValidationResult = testSchema
                .minLength(MIN_LENGTH)
                .isValid(SECOND_VALIDATING_STRING);
        assertThat(stringSchemaValidationResult).isTrue();
    }

    @Test
    public void testContainsTrue() {
        boolean stringSchemaValidationResult = testSchema
                .contains(FIRST_CONTAINS_STRING)
                .isValid(SECOND_VALIDATING_STRING);
        assertThat(stringSchemaValidationResult).isTrue();
    }

    @Test
    public void testContainsFalse() {
        boolean stringSchemaValidationResult = testSchema
                .contains(SECOND_CONTAINS_STRING)
                .isValid(FIRST_VALIDATING_STRING);
        assertThat(stringSchemaValidationResult).isFalse();
    }
}
