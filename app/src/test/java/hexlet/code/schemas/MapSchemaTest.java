package hexlet.code.schemas;

import hexlet.code.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Hashtable;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

public final class MapSchemaTest {
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
        assertThat(mapSchemaValidationResult).isTrue();
    }

    @Test
    public void testAfterRequired() {
        boolean mapSchemaValidationResult = testSchema
                .required()
                .isValid(null);
        assertThat(mapSchemaValidationResult).isFalse();
    }

    @Test
    public void testHashMapArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new HashMap<>());
        assertThat(mapSchemaValidationResult).isTrue();
    }

    @Test
    public void testLinkedHashMapArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new LinkedHashMap<>());
        assertThat(mapSchemaValidationResult).isTrue();
    }

    @Test
    public void testHashtableArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new Hashtable<>());
        assertThat(mapSchemaValidationResult).isTrue();
    }

    @Test
    public void testTreeMapArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new TreeMap<>());
        assertThat(mapSchemaValidationResult).isTrue();
    }

    @Test
    public void testIntArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new int[0]);
        assertThat(mapSchemaValidationResult).isFalse();
    }

    @Test
    public void testStringArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new ArrayList<>());
        assertThat(mapSchemaValidationResult).isFalse();
    }

    @Test
    public void testBooleanArgument() {
        boolean mapSchemaValidationResult = testSchema
                .isValid(new HashSet<Boolean>());
        assertThat(mapSchemaValidationResult).isFalse();
    }

    @Test
    public void testSizeOfWhenTrueSize() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("First", "Second");
        testMap.put("Second", "Third");

        boolean mapSchemaValidationResult = testSchema
                .sizeof(2)
                .isValid(testMap);
        assertThat(mapSchemaValidationResult).isTrue();
    }

    @Test
    public void testSizeOfWhenFalseSize() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("First", "Second");

        boolean mapSchemaValidationResult = testSchema
                .sizeof(2)
                .isValid(testMap);
        assertThat(mapSchemaValidationResult).isFalse();
    }

    @Test
    public void testShapeTrueFirst() {
        Map<String, BaseSchema> shapeValidations = new HashMap<>();
        shapeValidations.put("name", testValidator.string().required());
        shapeValidations.put("age", testValidator.number().positive());

        Map<String, Object> mapToValidate = new HashMap<>();
        mapToValidate.put("name", "Kolya");
        mapToValidate.put("age", 100);

        boolean mapSchemaValidationResult = testSchema
                .shape(shapeValidations)
                .isValid(mapToValidate);
        assertThat(mapSchemaValidationResult).isTrue();
    }

    @Test
    public void testShapeTrueSecond() {
        Map<String, BaseSchema> shapeValidations = new HashMap<>();
        shapeValidations.put("name", testValidator.string().required());
        shapeValidations.put("age", testValidator.number().positive());

        Map<String, Object> mapToValidate = new HashMap<>();
        mapToValidate.put("name", "Valya");
        mapToValidate.put("age", null);

        boolean mapSchemaValidationResult = testSchema
                .shape(shapeValidations)
                .isValid(mapToValidate);
        assertThat(mapSchemaValidationResult).isTrue();
    }

    @Test
    public void testShapeFalseFirst() {
        Map<String, BaseSchema> shapeValidations = new HashMap<>();
        shapeValidations.put("name", testValidator.string().required());
        shapeValidations.put("age", testValidator.number().positive());

        Map<String, Object> mapToValidate = new HashMap<>();
        mapToValidate.put("name", "");
        mapToValidate.put("age", null);

        boolean mapSchemaValidationResult = testSchema
                .shape(shapeValidations)
                .isValid(mapToValidate);
        assertThat(mapSchemaValidationResult).isFalse();
    }

    @Test
    public void testShapeFalseSecond() {
        Map<String, BaseSchema> shapeValidations = new HashMap<>();
        shapeValidations.put("name", testValidator.string().required());
        shapeValidations.put("age", testValidator.number().positive());

        Map<String, Object> mapToValidate = new HashMap<>();
        mapToValidate.put("name", "Valya");
        mapToValidate.put("age", -5);

        boolean mapSchemaValidationResult = testSchema
                .shape(shapeValidations)
                .isValid(mapToValidate);
        assertThat(mapSchemaValidationResult).isFalse();
    }
}
