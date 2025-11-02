package com.vmlens.trace.agent.bootstrap.fieldrepository;

import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;
import org.junit.jupiter.api.Test;


import static com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl.NORMAL_FIELD_STRATEGY;
import static com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl.NO_OP_FIELD_STRATEGY;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldRepositoryImplTest {

    private static final FieldOwnerAndName fieldOwnerAndName  =
            new FieldOwnerAndName("com.vmlens.test.Test",  "benignDataRace");

    /**
     * fieldRepositoryImpl.getIdAndSetFieldIsNormal is writing
     * fieldRepositoryImpl.get is reading
     */
    @Test
    public void testGetIdAndSetFieldIsNormalAndGet() throws InterruptedException {
        try(AllInterleavings allInterleavings =
                    new AllInterleavings("testGetIdAndSetFieldIsNormalAndGet")) {
            while (allInterleavings.hasNext()) {
                FieldRepositoryImpl fieldRepositoryImpl = new FieldRepositoryImpl();
                int id = fieldRepositoryImpl.asInt(fieldOwnerAndName);
                Thread first = new Thread(() ->{
                    int secondId = fieldRepositoryImpl
                            .getIdAndSetFieldIsNormal(fieldOwnerAndName);
                    assertThat(secondId,is(id));
                });
                first.start();
                FieldStrategy strategy = fieldRepositoryImpl.get(id);
                assertThat(strategy,
                        either(is(NORMAL_FIELD_STRATEGY)).or(is(NO_OP_FIELD_STRATEGY)));
                first.join();
            }
        }
    }

    /**
     * fieldRepositoryImpl.getIdAndSetFieldIsNormal is writing
     * fieldRepositoryImpl.asInt is writing
     */
    @Test
    public void testAsIntAndGetIdAndSetFieldIsNormal() throws InterruptedException {
        try(AllInterleavings allInterleavings =
                    new AllInterleavings("testAsIntAndGetIdAndSetFieldIsNormal")) {
            while (allInterleavings.hasNext()) {
                FieldRepositoryImpl fieldRepositoryImpl = new FieldRepositoryImpl();
                Thread first = new Thread(() ->{
                    fieldRepositoryImpl
                            .getIdAndSetFieldIsNormal(fieldOwnerAndName);
                });
                first.start();
                int id = fieldRepositoryImpl.asInt(fieldOwnerAndName);
                first.join();
                FieldStrategy strategy = fieldRepositoryImpl.get(id);
                assertThat(strategy,is(NORMAL_FIELD_STRATEGY));
            }
        }
    }

}
