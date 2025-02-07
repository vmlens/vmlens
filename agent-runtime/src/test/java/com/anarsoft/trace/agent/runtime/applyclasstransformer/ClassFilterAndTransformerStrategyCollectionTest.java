package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepository;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodRepository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ClassFilterAndTransformerStrategyCollectionTest {

    @Test
    public void order() {
        // Given
        WriteClassDescriptionAndWarning writeClassDescription = mock(WriteClassDescriptionAndWarning.class);
        ClassFilterAndTransformerStrategyCollectionFactory factory = new ClassFilterAndTransformerStrategyCollectionFactory(new MethodRepository(),
                new FieldRepository(), writeClassDescription);

        // When
        factory.add("java/lang/util/HashMap", new TransformerStrategyGuineaPig());
        factory.add("java/lang/util", new TransformerStrategyNoOp());


        ClassFilterAndTransformerStrategyCollection collection = factory.createInternal();


        TransformerStrategy transformer = collection.get("java/lang/util/HashMap");

        // Then
        assertThat(transformer, instanceOf(TransformerStrategyGuineaPig.class));

        // When
        transformer = collection.get("java/lang/util/HashSet");

        // Then
        assertThat(transformer, instanceOf(TransformerStrategyNoOp.class));
    }


}
