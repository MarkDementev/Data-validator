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
    private static final String FIRST_KEY = "name";
    private static final String SECOND_KEY = "age";
    private static final String FIRST_STRING_VALUE = "Kolya";
    private static final String SECOND_STRING_VALUE = "Valya";
    private static final String EMPTY_STRING = "";
    private static final int FIRST_INT_VALUE = 100;
    private static final int SECOND_INT_VALUE = -5;
    private static final int MAP_ELEMENTS_COUNT = 2;
    private Validator testValidator = new Validator();
    private MapSchema testSchema;

    @BeforeEach
    public void makeMapSchema() {
        testSchema = testValidator.map();
    }

    @Test
    public void testWithoutRequired() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(null);
        assertThat(mapSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testAfterRequired() {
        boolean mapSchemaValidationResult = testSchema
                .required()
                .isValid(null);
        assertThat(mapSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testHashMapArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new HashMap<>());
        assertThat(mapSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testLinkedHashMapArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new LinkedHashMap<>());
        assertThat(mapSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testHashtableArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new Hashtable<>());
        assertThat(mapSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testTreeMapArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new TreeMap<>());
        assertThat(mapSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testIntArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(INT_ARRAY_VALIDATING_OBJECT);
        assertThat(mapSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testStringArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(STRING_LIST_VALIDATING_OBJECT);
        assertThat(mapSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testBooleanArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(BOOLEAN_SET_VALIDATING_OBJECT);
        assertThat(mapSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testSizeOfWhenTrueSize() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put(FIRST_STRING, SECOND_STRING);
        testMap.put(SECOND_STRING, THIRD_STRING);

        boolean mapSchemaValidationResult = testSchema
                .sizeof(MAP_ELEMENTS_COUNT)
                .isValid(testMap);
        assertThat(mapSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testSizeOfWhenFalseSize() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put(FIRST_STRING, SECOND_STRING);

        boolean mapSchemaValidationResult = testSchema
                .sizeof(MAP_ELEMENTS_COUNT)
                .isValid(testMap);
        assertThat(mapSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testShapeTrueFirst() {
        Map<String, BaseSchema> shapeValidations = new HashMap<>();
        shapeValidations.put(FIRST_KEY, testValidator.string().required());
        shapeValidations.put(SECOND_KEY, testValidator.number().positive());

        Map<String, Object> mapToValidate = new HashMap<>();
        mapToValidate.put(FIRST_KEY, FIRST_STRING_VALUE);
        mapToValidate.put(SECOND_KEY, FIRST_INT_VALUE);

        boolean mapSchemaValidationResult = testSchema
                .shape(shapeValidations)
                .isValid(mapToValidate);
        assertThat(mapSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testShapeTrueSecond() {
        Map<String, BaseSchema> shapeValidations = new HashMap<>();
        shapeValidations.put(FIRST_KEY, testValidator.string().required());
        shapeValidations.put(SECOND_KEY, testValidator.number().positive());

        Map<String, Object> mapToValidate = new HashMap<>();
        mapToValidate.put(FIRST_KEY, SECOND_STRING_VALUE);
        mapToValidate.put(SECOND_KEY, null);

        boolean mapSchemaValidationResult = testSchema
                .shape(shapeValidations)
                .isValid(mapToValidate);
        assertThat(mapSchemaValidationResult).isEqualTo(true);
    }

    @Test
    public void testShapeFalseFirst() {
        Map<String, BaseSchema> shapeValidations = new HashMap<>();
        shapeValidations.put(FIRST_KEY, testValidator.string().required());
        shapeValidations.put(SECOND_KEY, testValidator.number().positive());

        Map<String, Object> mapToValidate = new HashMap<>();
        mapToValidate.put(FIRST_KEY, EMPTY_STRING);
        mapToValidate.put(SECOND_KEY, null);

        boolean mapSchemaValidationResult = testSchema
                .shape(shapeValidations)
                .isValid(mapToValidate);
        assertThat(mapSchemaValidationResult).isEqualTo(false);
    }

    @Test
    public void testShapeFalseSecond() {
        Map<String, BaseSchema> shapeValidations = new HashMap<>();
        shapeValidations.put(FIRST_KEY, testValidator.string().required());
        shapeValidations.put(SECOND_KEY, testValidator.number().positive());

        Map<String, Object> mapToValidate = new HashMap<>();
        mapToValidate.put(FIRST_KEY, SECOND_STRING_VALUE);
        mapToValidate.put(SECOND_KEY, SECOND_INT_VALUE);

        boolean mapSchemaValidationResult = testSchema
                .shape(shapeValidations)
                .isValid(mapToValidate);
        assertThat(mapSchemaValidationResult).isEqualTo(false);
    }
}
