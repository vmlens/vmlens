package com.vmlens.trace.agent.bootstrap.fieldrepository;

import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.*;
import gnu.trove.map.hash.THashMap;

public class FieldRepositoryImpl implements FieldRepositoryForCallback, FieldRepositoryForTransform {

    static final FieldStrategy VOLATILE_FIELD_STRATEGY = new VolatileFieldStrategy();
    static final FieldStrategy NORMAL_FIELD_STRATEGY = new NormalFieldStrategy();
    static final FieldStrategy FINAL_FIELD_STRATEGY = new FinalFieldStrategy();

    private final THashMap<FieldOwnerAndName, Integer> fieldIdIdToInt = new THashMap<>();
    private final THashMap<Integer, FieldStrategy> idToStrategy = new THashMap<>();

    private int maxIndex = 0;

    public synchronized int asInt(FieldOwnerAndName fieldId) {
        if (fieldIdIdToInt.contains(fieldId)) {
            return fieldIdIdToInt.get(fieldId);
        }
        int temp = maxIndex;
        maxIndex++;
        fieldIdIdToInt.put(fieldId, temp);
        return temp;
    }

    @Override
    public synchronized FieldStrategy get(int fieldId) {
        FieldStrategy strategy = idToStrategy.get(fieldId);
        // sometimes there is no strategy set
        // I think the reason is that a static field is accessed and
        // the corresponding classes was not loaded yet
        if (strategy == null) {
            return new FieldStrategyNoOp();
        }
        return strategy;
    }

    @Override
    public synchronized int getIdAndSetFieldIsVolatile(FieldOwnerAndName fieldOwnerAndName) {
        int temp = asInt(fieldOwnerAndName);
        idToStrategy.put(temp, VOLATILE_FIELD_STRATEGY);
        return temp;
    }

    @Override
    public synchronized int getIdAndSetFieldIsNormal(FieldOwnerAndName fieldOwnerAndName) {
        int temp = asInt(fieldOwnerAndName);
        idToStrategy.put(temp, NORMAL_FIELD_STRATEGY);
        return temp;
    }

    @Override
    public synchronized int getIdAndSetFieldIsFinal(FieldOwnerAndName fieldOwnerAndName) {
        int temp = asInt(fieldOwnerAndName);
        idToStrategy.put(temp, FINAL_FIELD_STRATEGY);
        return temp;
    }

}
