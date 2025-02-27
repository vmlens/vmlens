package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FieldCallbackImplTest {

    @Test
    public void fieldRead() {
        // Given
        int GIVEN_FIELD_ID = 434;
        Object GIVEN_OBJECT = new Object();
        int GIVEN_POSITION = 1;
        int GIVEN_IN_METHOD_ID = 675;

        FieldRepositoryForCallback fieldIdToStrategy = mock(FieldRepositoryForCallback.class);
        FieldStrategy fieldStrategy = mock(FieldStrategy.class);
        when(fieldIdToStrategy.get(GIVEN_FIELD_ID)).thenReturn(fieldStrategy);

        FieldCallbackImpl fieldCallbackImpl = new FieldCallbackImpl(fieldIdToStrategy, null);

        // When
        fieldCallbackImpl.beforeFieldRead(GIVEN_OBJECT, GIVEN_FIELD_ID, GIVEN_POSITION, GIVEN_IN_METHOD_ID);

        // Then
        verify(fieldStrategy).onAccess(GIVEN_OBJECT, GIVEN_FIELD_ID, GIVEN_POSITION, GIVEN_IN_METHOD_ID,
                MemoryAccessType.IS_READ, null);
    }

}
