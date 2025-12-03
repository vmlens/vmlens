package com.vmlens.trace.agent.bootstrap.fieldrepository;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.Runner;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;
import org.junit.jupiter.api.Test;

import static com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl.NO_OP_FIELD_STRATEGY;
import static com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl.VOLATILE_FIELD_STRATEGY;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldUpdaterRepositoryTest {

    private static final FieldOwnerAndName fieldOwnerAndName  =
            new FieldOwnerAndName("com.vmlens.test.Test",  "volatileField");
    private final Object updater = new Object();

    /**
     * FieldUpdaterRepository.get is non-commutative to
     * FieldUpdaterRepository.set
     * and
     * FieldRepositoryImpl.getIdAndSetFieldIsVolatile
     */
    @Test
    public void testSetGetAndFieldIsVolatile() {
        try(AllInterleavings allInterleavings =
                    new AllInterleavings("testSetGetAndFieldIsVolatile")) {
            while (allInterleavings.hasNext()) {
                FieldRepositoryImpl fieldRepositoryImpl = new FieldRepositoryImpl();
                FieldUpdaterRepository fieldUpdaterRepository = new FieldUpdaterRepository(fieldRepositoryImpl);
                Runner.runParallel(
                        () -> fieldRepositoryImpl.getIdAndSetFieldIsVolatile(fieldOwnerAndName),
                        () -> fieldUpdaterRepository.set(updater,fieldOwnerAndName),
                        () -> { FieldStrategy strategy = fieldUpdaterRepository.get(updater);
                                assertThat( strategy ,
                                        either( is(NO_OP_FIELD_STRATEGY)).or(
                                                is(VOLATILE_FIELD_STRATEGY)));
                        }
                );
            }
        }
    }


}
