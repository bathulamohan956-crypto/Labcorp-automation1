package testUtils;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private Map<String, Object> contextMap = new HashMap<>();

    public void set(String key, Object value) {
        contextMap.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) contextMap.get(key);
    }
}