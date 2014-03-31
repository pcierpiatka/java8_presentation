package edu.java8.presentation.builder;

import java.util.HashMap;
import java.util.Map;

public class MapTestBuilder {

    private Map<String, String> map;
    private MapTestBuilder(Map<String, String> map) {
        this.map = map;
    }

    public static MapTestBuilder map() {
        return new MapTestBuilder(new HashMap<String, String>());
    }

    public MapTestBuilder with(String key, String value) {
        map.put(key, value);
        return this;
    }

    public Map<String, String> build() {
        return map;
    }

}
