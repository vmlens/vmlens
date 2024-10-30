package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import java.util.HashMap;
import java.util.Map;

public class GetOrCreateObjectStateMockMapBased extends GetOrCreateObjectState {
    private Map<Object, ObjectState> objectToObjectState = new HashMap<Object, ObjectState>();

    @Override
    public ObjectState getOrCreate(Object obj) {
        if (objectToObjectState.containsKey(obj)) {
            return objectToObjectState.get(obj);
        }
        ObjectState created = new ObjectState();
        objectToObjectState.put(obj, created);
        return created;
    }
}
