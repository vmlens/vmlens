package com.vmlens.trace.agent.bootstrap.fieldrepository;

import com.vmlens.trace.agent.bootstrap.strategy.FieldStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategyimpl.NormalFieldStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategyimpl.StaticFieldStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategyimpl.StaticVolatileFieldStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategyimpl.VolatileFieldStrategy;
import gnu.trove.map.hash.THashMap;

public class FieldRepository implements FieldIdToStrategy, FieldOwnerAndNameToIntMap, FieldOwnerAndNameToStrategyStorage {

    static final FieldStrategy VOLATILE_FIELD_STRATEGY = new VolatileFieldStrategy();
    static final FieldStrategy STATIC_VOLATILE_FIELD_STRATEGY = new StaticVolatileFieldStrategy();
    static final FieldStrategy NORMAL_FIELD_STRATEGY = new NormalFieldStrategy();
    static final FieldStrategy STATIC_FIELD_STRATEGY = new StaticFieldStrategy();

    private final THashMap<FieldOwnerAndName, Integer> fieldIdIdToInt = new THashMap<>();
    private final THashMap<Integer, FieldStrategy> idToStrategy = new THashMap<>();

    private int maxIndex = 0;

    public int asInt(FieldOwnerAndName fieldId) {
        if (fieldIdIdToInt.contains(fieldId)) {
            return fieldIdIdToInt.get(fieldId);
        }
        int temp = maxIndex;
        maxIndex++;
        fieldIdIdToInt.put(fieldId, temp);
        return temp;
    }


    @Override
    public FieldStrategy get(int fieldId) {
        return idToStrategy.get(fieldId);
    }

    @Override
    public void setFieldIsVolatile(FieldOwnerAndName fieldOwnerAndName) {
        setStrategy(fieldOwnerAndName, VOLATILE_FIELD_STRATEGY);
    }

    @Override
    public void setFieldIsVolatileStatic(FieldOwnerAndName fieldOwnerAndName) {
        setStrategy(fieldOwnerAndName, STATIC_VOLATILE_FIELD_STRATEGY);
    }

    @Override
    public void setFieldIsNormal(FieldOwnerAndName fieldOwnerAndName) {
        setStrategy(fieldOwnerAndName, NORMAL_FIELD_STRATEGY);
    }

    @Override
    public void setFieldIsStatic(FieldOwnerAndName fieldOwnerAndName) {
        setStrategy(fieldOwnerAndName, STATIC_FIELD_STRATEGY);
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
