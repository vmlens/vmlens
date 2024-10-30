package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.state.FieldId2Element;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.state.FieldId2ElementListBased;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.state.ObjectIdAndOrder;

public class ObjectState {

    private final Object LOCK = new Object();
    private FieldId2Element<ObjectIdAndOrderValue> fieldId2Order = null;
    public ObjectState() {
        super();
    }

    public ObjectIdAndOrder getAndIncrementOrder(int fieldId) {
        synchronized (LOCK) {
            ObjectIdAndOrderValue objectIdAndOrderValue = null;
            if (fieldId2Order == null) {
                objectIdAndOrderValue = new ObjectIdAndOrderValue();
                fieldId2Order = new FieldId2ElementListBased<ObjectIdAndOrderValue>(objectIdAndOrderValue, fieldId);
            } else {
                objectIdAndOrderValue = fieldId2Order.get(fieldId);
                if (objectIdAndOrderValue == null) {
                    objectIdAndOrderValue = new ObjectIdAndOrderValue();
                    fieldId2Order = fieldId2Order.put(objectIdAndOrderValue, fieldId);
                }
            }

            ObjectIdAndOrder result = new ObjectIdAndOrder(objectIdAndOrderValue.id, objectIdAndOrderValue.order);
            objectIdAndOrderValue.order++;
            return result;
        }
    }

    private static class ObjectIdAndOrderValue {
        private static long maxId = 0;
        public final long id;
        public int order;

        public ObjectIdAndOrderValue() {
            super();
            id = getNewId();
        }

        private synchronized static long getNewId() {
            maxId++;
            return maxId;
        }
    }


}
