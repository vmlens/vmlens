package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeFilter;

public class ClassTypeCollectionBuilder {

    public TypeCollection<ClassType> build() {
        TypeCollection<ClassType> result = new TypeCollection<>();
        result.add(ClassTypeFilter.SINGLETON);
        return result;
    }

}
