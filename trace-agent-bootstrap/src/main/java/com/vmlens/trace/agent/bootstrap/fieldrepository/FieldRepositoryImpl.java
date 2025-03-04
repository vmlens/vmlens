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
        setStrategy(fieldOwnerAndName, VOLATILE_FIELD_STRATEGY);
        return asInt(fieldOwnerAndName);
    }

    @Override
    public synchronized int getIdAndSetFieldIsNormal(FieldOwnerAndName fieldOwnerAndName) {
        setStrategy(fieldOwnerAndName, NORMAL_FIELD_STRATEGY);
        return asInt(fieldOwnerAndName);
    }

    @Override
    public synchronized int getIdAndSetFieldIsFinal(FieldOwnerAndName fieldOwnerAndName) {
        setStrategy(fieldOwnerAndName, FINAL_FIELD_STRATEGY);
        return asInt(fieldOwnerAndName);
    }

    private void setStrategy(FieldOwnerAndName fieldId, FieldStrategy fieldStrategy) {
        if (fieldIdIdToInt.contains(fieldId)) {
            int id = fieldIdIdToInt.get(fieldId);
            idToStrategy.put(id, fieldStrategy);
        }
        int temp = maxIndex;
        maxIndex++;
        fieldIdIdToInt.put(fieldId, temp);
        idToStrategy.put(temp, fieldStrategy);
    }

}
