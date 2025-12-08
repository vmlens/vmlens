package com.vmlens.trace.agent.bootstrap.fieldrepository;

import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;
import gnu.trove.map.hash.THashMap;

import static com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl.NO_OP_FIELD_STRATEGY;

public class FieldUpdaterRepository {

    private final Object LOCK = new Object();
    private final THashMap<Object, FieldOwnerAndName> updaterToFieldOwnerAndName = new THashMap<>();
    private final FieldRepositoryForCallback fieldRepositoryForCallback;

    public FieldUpdaterRepository(FieldRepositoryForCallback fieldRepositoryForCallback) {
        this.fieldRepositoryForCallback = fieldRepositoryForCallback;
    }

    public void set(Object theUpdater, FieldOwnerAndName fieldOwnerAndName) {
        synchronized (LOCK) {
            updaterToFieldOwnerAndName.put(theUpdater,fieldOwnerAndName);
        }
    }

    public FieldStrategy get(Object theUpdater) {
        FieldOwnerAndName fieldOwnerAndName = getFieldOwnerAndName(theUpdater);
        if(fieldOwnerAndName == null) {
            return NO_OP_FIELD_STRATEGY;
        }
        return fieldRepositoryForCallback.get(fieldOwnerAndName);
    }

    public FieldStrategy get(FieldOwnerAndName fieldOwnerAndName) {
        return fieldRepositoryForCallback.get(fieldOwnerAndName);
    }

    private FieldOwnerAndName getFieldOwnerAndName(Object theUpdater) {
        synchronized (LOCK) {
            return updaterToFieldOwnerAndName.get(theUpdater);
        }
    }

}
