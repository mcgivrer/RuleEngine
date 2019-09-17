package com.ge.healthcare.dose.services.ruleengine;

import com.google.gson.JsonObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonDataOutputWriter implements OutputDataWriter {
    private static int index = 1;


    Map<Integer, JsonObject> store = new ConcurrentHashMap<>();

    @Override
    public synchronized Object writeData(JsonObject object) {
        int idx;
        if(object.get("id")!=null || !object.get("id").isJsonNull()) {
            idx = object.get("id").getAsInt();
        }else{
            idx = index ++;
            object.addProperty("id", idx);
        }
        store.put(idx, object);
        return object;
    }

    public Map<Integer, JsonObject> getData() {
        return store;
    }

    public JsonObject getData(Object key) {
        return store.get(key);
    }

}

