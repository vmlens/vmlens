package com.vmlens.nottraced.agent.applyclasstransformer;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransformerStrategyFilterInnerClassesTest {

    @Test
    public void innerClass() {
        // Given
        TransformerContext transformerContext = new TransformerContext(null, "java/lang/Thread$Inner");

        // When
        byte[] array = createTransformerStrategy().transform(transformerContext);

        // Then
        assertThat(array, is(nullValue()));
    }

    @Test
    public void anonymousClass() {
        // Given
        TransformerContext transformerContext = new TransformerContext(null,"java/lang/Thread$12");

        // When
        byte[] array = createTransformerStrategy().transform(transformerContext);

        // Then
        assertThat(array,is(notNullValue()));
    }

    private TransformerStrategy createTransformerStrategy() {
        TransformerStrategy strategy = mock(TransformerStrategy.class);
        when(strategy.transform(any())).thenReturn(new byte[0]);
        return  new TransformerStrategyFilterInnerClasses( "java/lang/Thread$",
                strategy);
    }

}
