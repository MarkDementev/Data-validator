package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    private static final String ONLY_MAPS_CHECK_NAME = "onlyMapsCheck";
    private static final String SIZE_OF_CHECK_NAME = "sizeofCheck";
    private static final String SHAPE_CHECK_NAME = "shapeCheck";
    Map<String, BaseSchema> schemas = new HashMap<>();
//    Map<String, BaseSchema> schemas = new HashMap<>();

    public MapSchema() {
        addCheck(ONLY_MAPS_CHECK_NAME, onlyMapsCheck());
    }

    public MapSchema(boolean isRequired, Map<String, Predicate<Object>> checks,
                     String checkToAddName, Predicate<Object> checkToAdd) {
        super(isRequired, checks);
        addCheck(checkToAddName, checkToAdd);
    }

    public MapSchema(boolean isRequired, Map<String, Predicate<Object>> checks,
                     String checkToAddName, Predicate<Object> checkToAdd,
                     Map<String, BaseSchema> schemas) {
        super(isRequired, checks);
        this.schemas = schemas;
        addCheck(checkToAddName, checkToAdd);
    }

//    public MapSchema(boolean isRequired, Map<String, Predicate<Object>> checks,
//                     Map<String, BaseSchema> schemas) {
//        super(isRequired, checks);
//        this.schemas = schemas;
//    }

//    public boolean isValid(Map<String, Object> mapToCheck) {
//        if (mapToCheck == null) {
//            return !isRequired;
//        }
//
//        for (Map.Entry<String, BaseSchema> schema : schemas.entrySet()) {
//            String schemaKey = schema.getKey();
//            Object mapToCheckValue = mapToCheck.get(schemaKey);
//
//            if (!schema.getValue().isValid(mapToCheckValue)) {
//                return false;
//            }
//        }
//        return true;
//    }

    public MapSchema required() {
        setIsRequiredTrue();
        return new MapSchema(isRequired, checks,
                ONLY_MAPS_CHECK_NAME, onlyMapsCheck());
    }

    public MapSchema sizeof(int neededSize) {
        return new MapSchema(isRequired, checks,
                SIZE_OF_CHECK_NAME, sizeOfCheck(neededSize));
    }

    public MapSchema shape(Map<String, BaseSchema> newSchemas) {
        this.schemas = newSchemas;
        return new MapSchema(isRequired, checks,
                SHAPE_CHECK_NAME, shapeCheck(newSchemas),
                schemas);
    }

//    public MapSchema shape(Map<String, BaseSchema> newSchemas) {
//        this.schemas = newSchemas;
//        return new MapSchema(isRequired, checks, schemas);
//    }

    private Predicate<Object> onlyMapsCheck() {
        return p -> (p instanceof Map);
    }

    private Predicate<Object> sizeOfCheck(int neededSize) {
        return p -> {
            ObjectMapper mapper = new ObjectMapper();
            Map<?, ?> pMap = mapper.convertValue(p, Map.class);
            return pMap.size() == neededSize;
        };
    }

    private Predicate<Object> shapeCheck(Map<String, BaseSchema> inputSchemas) {
        //p - видимо, это вводимая снаружи Map<String, Object> mapToCheck
        return p -> {
            ObjectMapper mapper = new ObjectMapper();
            Map<?, ?> pMap = mapper.convertValue(p, Map.class);

            for (Map.Entry<String, BaseSchema> schema : inputSchemas.entrySet()) {
                String schemaKey = schema.getKey();
                Object mapToCheckValue = pMap.get(schemaKey);

                if (!schema.getValue().isValid(mapToCheckValue)) {
                    return false;
                }
            }
            return true;
        };
    }
}
