package com.ge.healthcare.dose.services.ruleengine;

import com.google.gson.JsonObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This OutputDataWriter implementation is for test only purpose.
 *
 * @author Frédéric Delorme
 * @see OutputDataWriter
 * @since 2019
 */
public class JsonDataOutputWriter implements OutputDataWriter {
    /**
     * internal index counter
     */
    private static int index = 1;

    /**
     * internal in memory store
     */
    Map<Integer, JsonObject> store = new ConcurrentHashMap<>();

    /**
     * write data to the internal memory store.
     *
     * @param object the object to be stored.
     * @return object with id created/updated.
     */
    @Override
    public synchronized Object writeData(JsonObject object) {
        int idx;
        if (object.get("id") != null || !object.get("id").isJsonNull()) {
            idx = object.get("id").getAsInt();
        } else {
            idx = index++;
            object.addProperty("id", idx);
        }
        store.put(idx, object);
        return object;
    }

    /**
     * Get access to the data store.
     *
     * @return
     */
    public Map<Integer, JsonObject> getById() {
        return store;
    }

    /**
     * retrieve an object on its id in the store.
     * @param key
     * @return
     */
    public JsonObject getById(Object key) {
        return store.get(key);
    }

}
