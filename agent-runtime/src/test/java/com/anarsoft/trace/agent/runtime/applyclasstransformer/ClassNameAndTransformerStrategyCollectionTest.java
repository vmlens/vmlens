package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.transformerstrategyimpl.TransformerStrategyFilter;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepository;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ClassNameAndTransformerStrategyCollectionTest {

    @Test
    public void order() {
        // Given
        WriteClassDescriptionAndWarning writeClassDescription = mock(WriteClassDescriptionAndWarning.class);
        ClassNameAndTransformerStrategyCollectionFactory factory = new ClassNameAndTransformerStrategyCollectionFactory(new MethodRepository(),
                new FieldRepository(), writeClassDescription);

        // When
        factory.add("java/lang/util/HashMap", new TransformerStrategyGuineaPig());
        factory.add("java/lang/util", new TransformerStrategyFilter());


        ClassNameAndTransformerStrategyCollection collection = factory.createInternal();

        ClassNameAndTransformerStrategy transformer = collection.get("java/lang/util/HashMap");

        // Then
        assertThat(transformer.transformStrategy(), instanceOf(TransformerStrategyGuineaPig.class));

        // When
        transformer = collection.get("java/lang/util/HashSet");

        // Then
        assertThat(transformer.transformStrategy(), instanceOf(TransformerStrategyFilter.class));
    }


}
