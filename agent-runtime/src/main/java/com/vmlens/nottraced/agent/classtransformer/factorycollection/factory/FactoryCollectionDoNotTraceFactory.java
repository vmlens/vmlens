package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionDoNotTrace;

public class FactoryCollectionDoNotTraceFactory  implements FactoryCollectionFactory  {

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionDoNotTrace();
    }
}
