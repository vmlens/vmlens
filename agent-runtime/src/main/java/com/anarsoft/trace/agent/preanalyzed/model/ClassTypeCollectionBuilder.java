package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeAnonymousClass;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeFilter;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeThread;

public class ClassTypeCollectionBuilder {

    public TypeCollection<ClassType> build() {
        TypeCollection<ClassType> result = new TypeCollection<>();
        result.add(ClassTypeFilter.SINGLETON);
        result.add(ClassTypeThread.SINGLETON);
        result.add(ClassTypeAnonymousClass.SINGLETON);
        return result;
    }

}
