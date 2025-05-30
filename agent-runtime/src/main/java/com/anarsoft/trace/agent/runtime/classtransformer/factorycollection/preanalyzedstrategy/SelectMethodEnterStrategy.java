package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.preanalyzedstrategy;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;


public class SelectMethodEnterStrategy {

    private final THashSet<NameAndDescriptor> useWithInParam = new THashSet<>();

    public void addToUseWithInParam(NameAndDescriptor nameAndDescriptor) {
        useWithInParam.add(nameAndDescriptor);
    }

    public boolean useWithInParam(NameAndDescriptor nameAndDescriptor) {
        return useWithInParam.contains(nameAndDescriptor);
    }



}
