package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    private static final String ONLY_MAPS_CHECK_NAME = "onlyMapsCheck";
    private static final String SIZE_OF_CHECK_NAME = "sizeofCheck";
    private static final String SHAPE_CHECK_NAME = "shapeCheck";
    private Map<String, BaseSchema> schemas = new HashMap<>();

    public MapSchema() {
        addCheck(ONLY_MAPS_CHECK_NAME, onlyMapsCheck());
    }

    public MapSchema required() {
        setIsRequiredTrue();
        return this;
    }

    public MapSchema sizeof(int neededSize) {
        addCheck(SIZE_OF_CHECK_NAME, sizeOfCheck(neededSize));
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> newSchemas) {
        this.schemas = newSchemas;
        addCheck(SHAPE_CHECK_NAME, shapeCheck(newSchemas));
        return this;
    }

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
