package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeAllStartWith;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeFilter;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeVmlensApi;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.PreAnalyzedEqualNoOp;

public class ClassTypeCollectionBuilder {

    public TypeCollection<ClassType> build() {
        TypeCollection<ClassType> result = new TypeCollection<>();
        result.add(ClassTypeFilter.SINGLETON);
        result.add(PreAnalyzedEqualNoOp.SINGLETON);
        result.add(ClassTypeAllStartWith.SINGLETON);
        result.add(ClassTypeVmlensApi.SINGLETON);
        return result;
    }

}
