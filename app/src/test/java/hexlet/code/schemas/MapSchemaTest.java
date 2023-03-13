package hexlet.code.schemas;

import hexlet.code.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MapSchemaTest {
    private static final int[] INT_ARRAY_VALIDATING_OBJECT = {};
    private static final List<String> STRING_LIST_VALIDATING_OBJECT = new ArrayList<>();
    private static final Set<Boolean> BOOLEAN_SET_VALIDATING_OBJECT = new HashSet<>();
    private static final String FIRST_STRING = "First";
    private static final String SECOND_STRING = "Second";
    private static final String THIRD_STRING = "Third";
    private static final int MAP_ELEMENTS_COUNT = 2;
    private Validator testValidator = new Validator();
    private MapSchema testSchema;

    @BeforeEach
    public void makeMapSchema() {
        testSchema = testValidator.map();
    }

    @Test
    public void testIsValidAfterBeforeRequired() {
        assertThat(testSchema.isValid(null)).isEqualTo(true);
        testSchema.required();
        assertThat(testSchema.isValid(null)).isEqualTo(false);
    }

    @Test
    public void testIsValidMapArgument() {
        assertThat(testSchema.isValid(new HashMap<>())).isEqualTo(true);
        assertThat(testSchema.isValid(new LinkedHashMap<>())).isEqualTo(true);
        assertThat(testSchema.isValid(new Hashtable<>())).isEqualTo(true);
        assertThat(testSchema.isValid(new TreeMap<>())).isEqualTo(true);
    }

    @Test
    public void testIsValidNotMapArgument() {
        assertThat(testSchema.isValid(INT_ARRAY_VALIDATING_OBJECT)).isEqualTo(false);
        assertThat(testSchema.isValid(STRING_LIST_VALIDATING_OBJECT)).isEqualTo(false);
        assertThat(testSchema.isValid(BOOLEAN_SET_VALIDATING_OBJECT)).isEqualTo(false);
    }

    @Test
    public void testIsValidSizeOf() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put(FIRST_STRING, SECOND_STRING);
        assertThat(testSchema.isValid(testMap)).isEqualTo(true);
        testMap.sizeof(MAP_ELEMENTS_COUNT);
        assertThat(testSchema.isValid(testMap)).isEqualTo(false);
        testMap.put(SECOND_STRING, THIRD_STRING);
        assertThat(testSchema.isValid(testMap)).isEqualTo(true);
    }
}
