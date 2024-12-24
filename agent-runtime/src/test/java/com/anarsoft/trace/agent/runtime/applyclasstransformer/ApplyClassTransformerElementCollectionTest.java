package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.transformerstrategyimpl.TransformerStrategyFilter;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepository;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ApplyClassTransformerElementCollectionTest {

    @Test
    public void order() {
        // Given
        WriteClassDescription writeClassDescription = mock(WriteClassDescription.class);
        ApplyClassTransformerCollectionFactory factory = new ApplyClassTransformerCollectionFactory(new MethodRepository(),
                new FieldRepository(), writeClassDescription);

        // When
        factory.add("java/lang/util/HashMap", new TransformerStrategyGuineaPig());
        factory.add("java/lang/util", new TransformerStrategyFilter());


        ApplyClassTransformerCollection collection = factory.createInternal();

        ApplyClassTransformerElement transformer = collection.get("java/lang/util/HashMap");

        // Then
        assertThat(transformer.transformStrategy(), instanceOf(TransformerStrategyGuineaPig.class));

        // When
        transformer = collection.get("java/lang/util/HashSet");

        // Then
        assertThat(transformer.transformStrategy(), instanceOf(TransformerStrategyFilter.class));
    }


}
