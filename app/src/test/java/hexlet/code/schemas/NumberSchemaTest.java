package hexlet.code.schemas;

import hexlet.code.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {
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
        assertThat(numberSchemaValidationResult).isTrue();
    }

    @Test
    public void testAfterRequired() {
        boolean numberSchemaValidationResult = testSchema
                .required()
                .isValid(null);
        assertThat(numberSchemaValidationResult).isFalse();
    }

    @Test
    public void testWithoutPositive() {
        boolean numberSchemaValidationResult = testSchema
                .isValid(-100);
        assertThat(numberSchemaValidationResult).isTrue();
    }

    @Test
    public void testPositive() {
        boolean numberSchemaValidationResult = testSchema
                .positive()
                .isValid(-100);
        assertThat(numberSchemaValidationResult).isFalse();
    }

    @Test
    public void testBooleanArgument() {
        boolean numberSchemaValidationResult = testSchema
                .isValid(false);
        assertThat(numberSchemaValidationResult).isFalse();
    }

    @Test
    public void testStringArgument() {
        boolean numberSchemaValidationResult = testSchema
                .isValid("100");
        assertThat(numberSchemaValidationResult).isFalse();
    }

    @Test
    public void testDoubleArgument() {
        boolean numberSchemaValidationResult = testSchema
                .isValid(100.0);
        assertThat(numberSchemaValidationResult).isFalse();
    }

    @Test
    public void testWithRangeTrue() {
        boolean numberSchemaValidationResult = testSchema
                .range(0, 5)
                .isValid(3);
        assertThat(numberSchemaValidationResult).isTrue();
    }

    @Test
    public void testWithRangeFalse() {
        boolean numberSchemaValidationResult = testSchema
                .positive()
                .range(-2, 5)
                .isValid(-1);
        assertThat(numberSchemaValidationResult).isFalse();
    }
}
