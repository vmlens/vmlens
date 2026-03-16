package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.DoNotTraceType;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionDoNotTrace;

public class FactoryCollectionDoNotTraceFactory  implements FactoryCollectionFactory  {

    private final DoNotTraceType doNotTraceType;

    public FactoryCollectionDoNotTraceFactory(DoNotTraceType doNotTraceType) {
        this.doNotTraceType = doNotTraceType;
    }

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionDoNotTrace(doNotTraceType);
    }
}
