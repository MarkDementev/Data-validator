package hexlet.code.schemas;

import hexlet.code.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest {
    private static final String EMPTY_STRING = "";
    private static final String FIRST_VALIDATING_STRING = "Mark";
    private static final String SECOND_VALIDATING_STRING = "Dementev";
    private static final String THIRD_VALIDATING_STRING = "Valentinovich";
    private static final String FIRST_CONTAINS_STRING = "De";
    private static final String SECOND_CONTAINS_STRING = "Dem";
    private static final String THIRD_CONTAINS_STRING = "Deme";
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
    public void testIsValidAfterBeforeRequired() {
        assertThat(testSchema.isValid(null)).isEqualTo(true);
        assertThat(testSchema.isValid(EMPTY_STRING)).isEqualTo(true);
        testSchema.required();
        assertThat(testSchema.isValid(EMPTY_STRING)).isEqualTo(false);
        assertThat(testSchema.isValid(null)).isEqualTo(false);
    }

    @Test
    public void testIsValidStringArgument() {
        assertThat(testSchema.minLength(MIN_LENGTH).isValid(FIRST_VALIDATING_STRING)).isEqualTo(false);
        assertThat(testSchema.isValid(SECOND_VALIDATING_STRING)).isEqualTo(true);
        assertThat(testSchema.isValid(THIRD_VALIDATING_STRING)).isEqualTo(true);
    }

    @Test
    public void testIsValidNotStringArgument() {
        assertThat(testSchema.isValid(BOOLEAN_VALIDATING_OBJECT)).isEqualTo(false);
        assertThat(testSchema.isValid(INT_VALIDATING_OBJECT)).isEqualTo(false);
        assertThat(testSchema.isValid(DOUBLE_VALIDATING_OBJECT)).isEqualTo(false);
    }

    @Test
    public void testIsValidWithContains() {
        assertThat(testSchema.contains(FIRST_CONTAINS_STRING)
                .isValid(SECOND_VALIDATING_STRING))
                .isEqualTo(true);
        assertThat(testSchema.contains(SECOND_CONTAINS_STRING)
                .isValid(SECOND_VALIDATING_STRING))
                .isEqualTo(true);
        assertThat(testSchema.contains(THIRD_CONTAINS_STRING)
                .isValid(FIRST_VALIDATING_STRING))
                .isEqualTo(false);
        assertThat(testSchema.isValid(SECOND_CONTAINS_STRING)).isEqualTo(false);
    }
}
