package com.vmlens.trace.agent.bootstrap.preanalyzed.model;


import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.*;

public class ClassTypeCollection {

    public ClassTypeCollection() {
    }

    public int id(ClassType classType) {
        if(classType instanceof ClassTypeFilter) {
            return 0;
        }
        if(classType instanceof PreAnalyzedSpecificMethods) {
            return 1;
        }
        if(classType instanceof ClassTypeAllStartWith) {
            return 2;
        }
        if(classType instanceof ClassTypeVmlensApi) {
            return 3;
        }
        if(classType instanceof PreAnalyzedAllMethods) {
            return 4;
        }
        if(classType instanceof ClassNotYetImplemented) {
            return 5;
        }
        if(classType instanceof DoNotTraceInClass) {
            return 6;
        }
        if(classType instanceof ClassTypeThreadPool) {
            return 7;
        }
        if(classType instanceof ClassTypeFilterInnerIncludeAnonymous) {
            return 8;
        }
        if(classType instanceof DoNotTraceInTestContainsClassName) {
            return 9;
        }
        if(classType instanceof DoNotTraceInTestStartsWithClassName) {
            return 10;
        }
        throw new RuntimeException("unknown " + classType.getClass());
    }

    public ClassType type(int id) {
        if(id == 0) {
            return ClassTypeFilter.SINGLETON;
        }
        if(id == 1) {
            return PreAnalyzedSpecificMethods.SINGLETON;
        }
        if(id == 2) {
            return ClassTypeAllStartWith.SINGLETON;
        }
        if(id == 3) {
            return ClassTypeVmlensApi.SINGLETON;
        }
        if(id == 4) {
            return PreAnalyzedAllMethods.SINGLETON;
        }
        if(id == 5) {
            return ClassNotYetImplemented.SINGLETON;
        }
        if(id == 6) {
            return DoNotTraceInClass.SINGLETON;
        }
        if(id == 7) {
            return ClassTypeThreadPool.SINGLETON;
        }
        if(id == 8) {
            return ClassTypeFilterInnerIncludeAnonymous.SINGLETON;
        }
        if(id == 9) {
            return DoNotTraceInTestContainsClassName.SINGLETON;
        }
        if(id == 10) {
            return DoNotTraceInTestStartsWithClassName.SINGLETON;
        }
        throw new RuntimeException("unknown " + id);
    }
}
