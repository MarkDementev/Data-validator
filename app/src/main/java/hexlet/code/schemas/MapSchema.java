package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean isSizeOf;
    private int mapSize;

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
    public boolean isValidMapSchema(Map validatingMap) {
        if (isSizeOf) {
            return validatingMap.size() == mapSize;
        }
        return true;
    }
}
