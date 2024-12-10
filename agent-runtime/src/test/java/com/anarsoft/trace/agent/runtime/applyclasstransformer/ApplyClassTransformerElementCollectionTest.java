package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndNameToIntMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApplyClassTransformerElementCollectionTest {

    @Test
    public void order() {
        // Given
        ApplyClassTransformerCollectionFactory factory = new ApplyClassTransformerCollectionFactory(new MethodCallIdMap(),
                new FieldOwnerAndNameToIntMap());

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
