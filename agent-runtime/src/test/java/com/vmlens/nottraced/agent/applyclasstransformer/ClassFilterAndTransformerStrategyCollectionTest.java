package com.vmlens.nottraced.agent.applyclasstransformer;

import com.vmlens.nottraced.agent.classtransformer.RunTestClassTransformer;
import com.vmlens.nottraced.agent.classtransformer.TransformerStrategyForClassTransformer;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.factory.FactoryCollectionDoNotTraceFactory;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassFilterAndTransformerStrategyCollectionTest {

    @Test
    public void mockitoMock() throws IOException {
        TransformerStrategy strategy =  RunTestClassTransformer
                .createFromLoaded()
                .getStrategy("org/mockito/internal/creation/bytebuddy/codegen/List$MockitoMock$Ma5iei0b");

        TransformerStrategyForClassTransformer transformerStrategyForClassTransformer =
                 (TransformerStrategyForClassTransformer) strategy;


        assertThat(transformerStrategyForClassTransformer.factoryCollectionFactory(),is(instanceOf(FactoryCollectionDoNotTraceFactory.class)));
    }

}
