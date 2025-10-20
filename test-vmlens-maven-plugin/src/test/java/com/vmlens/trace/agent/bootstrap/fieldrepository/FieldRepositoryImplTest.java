package com.vmlens.trace.agent.bootstrap.fieldrepository;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import static com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl.NORMAL_FIELD_STRATEGY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldRepositoryImplTest {

    private static final FieldOwnerAndName fieldOwnerAndName  = new FieldOwnerAndName("com.vmlens.test.Test",  "benignDataRace");

    @Test
    public void testNormalField() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("fieldRepositoryImplTest.testNoOpAndNormalField")) {
            while (allInterleavings.hasNext()) {
                FieldRepositoryImpl fieldRepositoryImpl = new FieldRepositoryImpl();
                Thread first = new Thread(() ->{
                    fieldRepositoryImpl.getIdAndSetFieldIsNormal(fieldOwnerAndName);
                });
                first.start();
                int id = fieldRepositoryImpl.asInt(fieldOwnerAndName);
                first.join();
                assertThat(fieldRepositoryImpl.get(id),is(NORMAL_FIELD_STRATEGY));
            }
        }
    }

}
