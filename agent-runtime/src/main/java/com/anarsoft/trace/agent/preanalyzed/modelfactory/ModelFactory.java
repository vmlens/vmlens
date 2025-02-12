package com.anarsoft.trace.agent.preanalyzed.modelfactory;


import com.anarsoft.trace.agent.preanalyzed.model.PackageOrClass;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeAllStartWith;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.PreAnalyzedEqualNoOp;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodTypeThreadStart;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class ModelFactory {

    public TLinkedList<TLinkableWrapper<PackageOrClass>> create() {
        TLinkedList<TLinkableWrapper<PackageOrClass>> result = new TLinkedList<>();


        PreAnalyzedMethod[] methods = new PreAnalyzedMethod[1];
        methods[0] = new PreAnalyzedMethod("start", "()V", MethodTypeThreadStart.SINGLETON);
        result.add(wrap(new PackageOrClass("java/lang/Thread", PreAnalyzedEqualNoOp.SINGLETON, methods)));


        result.add(wrap(new PackageOrClass("com/vmlens", ClassTypeAllStartWith.SINGLETON, new PreAnalyzedMethod[0])));


        return result;
    }
}
