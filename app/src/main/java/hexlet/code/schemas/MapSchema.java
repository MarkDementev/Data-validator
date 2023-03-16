package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean isSizeOf;
    private int mapSize;
    private Map<String, BaseSchema> shapesMap = new HashMap<>();

    public MapSchema() {
        super();
        this.isSizeOf = false;
        this.mapSize = 0;
    }

    public void sizeof(int newMapSize) {
        isSizeOf = true;
        mapSize = newMapSize;
    }

    @Override
    public boolean isValidMapSchema(Map<?, ?> validatingMap) {
        if (isSizeOf) {
            return validatingMap.size() == mapSize;
        }
        return true;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        isHasShape = true;
        shapesMap.putAll(schemas);
    }

    @Override
    public boolean isValidWithShape(Map<?, ?> validatingMap) {
        for (Map.Entry<String, BaseSchema> shapesMapElement : shapesMap.entrySet()) {
            if (shapesMapElement.toString().contains("Number")) {
                NumberSchema numberSchema = (NumberSchema) shapesMapElement.getValue();

                if (!numberSchema.isValid(validatingMap.get(shapesMapElement.getKey()))) {
                    return false;
                }
            } else {
                StringSchema stringSchema = (StringSchema) shapesMapElement.getValue();

                if (!stringSchema.isValid(validatingMap.get(shapesMapElement.getKey()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
