package hexlet.code.schemas;

import hexlet.code.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest {
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
                .isValid("");
        assertThat(stringSchemaValidationResult).isTrue();
    }

    @Test
    public void testEmptyStringAfterRequired() {
        boolean stringSchemaValidationResult = testSchema
                .required()
                .isValid("");
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testBooleanArgument() {
        boolean stringSchemaValidationResult = testSchema
                .isValid(true);
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testIntArgument() {
        boolean stringSchemaValidationResult = testSchema
                .isValid(10);
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testDoubleArgument() {
        boolean stringSchemaValidationResult = testSchema
                .isValid(7.0);
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testMinLengthFalse() {
        boolean stringSchemaValidationResult = testSchema
                .minLength(5)
                .isValid("Mark");
        assertThat(stringSchemaValidationResult).isFalse();
    }

    @Test
    public void testMinLengthTrue() {
        boolean stringSchemaValidationResult = testSchema
                .minLength(5)
                .isValid("Dementev");
        assertThat(stringSchemaValidationResult).isTrue();
    }

    @Test
    public void testContainsTrue() {
        boolean stringSchemaValidationResult = testSchema
                .contains("De")
                .isValid("Dementev");
        assertThat(stringSchemaValidationResult).isTrue();
    }

    @Test
    public void testContainsFalse() {
        boolean stringSchemaValidationResult = testSchema
                .contains("Deme")
                .isValid("Mark");
        assertThat(stringSchemaValidationResult).isFalse();
    }
}
