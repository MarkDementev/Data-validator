package hexlet.code.schemas;

import hexlet.code.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Hashtable;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

public final class MapSchemaTest {
    private static final int[] INT_ARRAY_VALIDATING_OBJECT = {};
    private static final List<String> STRING_LIST_VALIDATING_OBJECT = new ArrayList<>();
    private static final Set<Boolean> BOOLEAN_SET_VALIDATING_OBJECT = new HashSet<>();
    private static final String FIRST_STRING = "First";
    private static final String SECOND_STRING = "Second";
    private static final String THIRD_STRING = "Third";
    private static final int MAP_ELEMENTS_COUNT = 2;
    private static final String FIRST_KEY = "name";
    private static final String SECOND_KEY = "age";
    private static final String FIRST_VALUE = "Kolya";
    private static final int SECOND_VALUE = 100;
    private static final String THIRD_VALUE = "Maya";
    private static final String FOURTH_VALUE = "Valya";
    private static final int FIFTH_VALUE = -5;
    private static final String EMPTY_STRING = "";

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
        testSchema.sizeof(MAP_ELEMENTS_COUNT);
        assertThat(testSchema.isValid(testMap)).isEqualTo(false);
        testMap.put(SECOND_STRING, THIRD_STRING);
        assertThat(testSchema.isValid(testMap)).isEqualTo(true);
    }

    @Test
    public void testIsValidWithShape() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put(FIRST_KEY, testValidator.string().required());
        schemas.put(SECOND_KEY, testValidator.number().positive());
        testSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put(FIRST_KEY, FIRST_VALUE);
        human1.put(SECOND_KEY, SECOND_VALUE);
        assertThat(testSchema.isValid(human1)).isEqualTo(true);

        Map<String, Object> human2 = new HashMap<>();
        human2.put(FIRST_KEY, THIRD_VALUE);
        human2.put(SECOND_KEY, null);
        assertThat(testSchema.isValid(human2)).isEqualTo(true);

        Map<String, Object> human3 = new HashMap<>();
        human3.put(FIRST_KEY, EMPTY_STRING);
        human3.put(SECOND_KEY, null);
        assertThat(testSchema.isValid(human3)).isEqualTo(false);

        Map<String, Object> human4 = new HashMap<>();
        human4.put(FIRST_KEY, FOURTH_VALUE);
        human4.put(SECOND_KEY, FIFTH_VALUE);
        assertThat(testSchema.isValid(human4)).isEqualTo(false);
    }
}
