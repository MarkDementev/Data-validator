package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    private Map<String, BaseSchema> schemas = new HashMap<>();

    public MapSchema() {
        addCheck("onlyMapsCheck", onlyMapsCheck());
    }

    public MapSchema required() {
        setIsRequiredTrue();
        return this;
    }

    public MapSchema sizeof(int neededSize) {
        addCheck("sizeofCheck", sizeOfCheck(neededSize));
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> newSchemas) {
        this.schemas = newSchemas;
        addCheck("shapeCheck", shapeCheck(newSchemas));
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
