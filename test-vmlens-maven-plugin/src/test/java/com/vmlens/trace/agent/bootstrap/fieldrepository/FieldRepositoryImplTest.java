package com.vmlens.trace.agent.bootstrap.fieldrepository;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.Runner;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;
import org.junit.jupiter.api.Test;


import static com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl.NORMAL_FIELD_STRATEGY;
import static com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl.NO_OP_FIELD_STRATEGY;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldRepositoryImplTest {


    private static final FieldOwnerAndName PREVIOUS_FIELD_OWNER_AND_NAME =
            new FieldOwnerAndName("com.vmlens.test.Test",  "previousField");
    private static final FieldOwnerAndName FIELD_OWNER_AND_NAME =
            new FieldOwnerAndName("com.vmlens.test.Test",  "normalField");

    @Test
    public void testAsIntAndGetIdAndSetFieldIsNormal() {
        try(AllInterleavings allInterleavings =
                    new AllInterleavings("testAsIntAndGetIdAndSetFieldIsNormal")) {
            while (allInterleavings.hasNext()) {
                FieldRepositoryImpl fieldRepositoryImpl = new FieldRepositoryImpl();
                int previousId = fieldRepositoryImpl.asInt(PREVIOUS_FIELD_OWNER_AND_NAME);
                Runner.runParallel(
                        () -> {
                            int id = fieldRepositoryImpl
                                    .getIdAndSetFieldIsNormal(FIELD_OWNER_AND_NAME);
                            assertThat(id,is(previousId+1));
                        },
                        () -> {
                            int id = fieldRepositoryImpl.asInt(FIELD_OWNER_AND_NAME);
                            assertThat(id,is(previousId+1));
                        });
                int id = fieldRepositoryImpl.asInt(FIELD_OWNER_AND_NAME);
                FieldStrategy strategy = fieldRepositoryImpl.get(id);
                assertThat(strategy, is(NORMAL_FIELD_STRATEGY));
            }
        }
    }

    @Test
    public void testGetIdAndSetFieldIsNormalAndGet() throws InterruptedException {
        try(AllInterleavings allInterleavings =
                    new AllInterleavings("testGetIdAndSetFieldIsNormalAndGet")) {
            while (allInterleavings.hasNext()) {
                FieldRepositoryImpl fieldRepositoryImpl = new FieldRepositoryImpl();
                int id = fieldRepositoryImpl.asInt(FIELD_OWNER_AND_NAME);
                Thread first = new Thread(() ->{
                    int secondId = fieldRepositoryImpl
                            .getIdAndSetFieldIsNormal(FIELD_OWNER_AND_NAME);
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



}
