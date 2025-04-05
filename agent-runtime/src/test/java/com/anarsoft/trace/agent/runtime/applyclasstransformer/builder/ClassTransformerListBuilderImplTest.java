package com.anarsoft.trace.agent.runtime.applyclasstransformer.builder;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.ClassFilterAndTransformerStrategyCollection;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClassTransformerListBuilderImplTest {

    @Test
    public void createFilter() {
        // Given
        TransformerStrategyFactory transformerStrategyFactoryMock = mock(TransformerStrategyFactory.class);
        ClassTransformerListBuilderImpl classBuilderImpl = new ClassTransformerListBuilderImpl(transformerStrategyFactoryMock);

        TransformerStrategy transformerStrategyMock = mock(TransformerStrategy.class);
        when(transformerStrategyFactoryMock.createNoOp()).thenReturn(transformerStrategyMock);

        // When
        classBuilderImpl.addFilterStartsWith("java/lang");
        ClassFilterAndTransformerStrategyCollection collection = classBuilderImpl.build();

        // Then
        assertThat(collection.get("java"), is(nullValue()));
        assertThat(collection.get("java/lang/test"), is(transformerStrategyMock));

    }


}
