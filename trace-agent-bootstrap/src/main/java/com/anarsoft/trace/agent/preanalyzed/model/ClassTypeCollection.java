package com.anarsoft.trace.agent.preanalyzed.model;


import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.*;

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
        throw new RuntimeException("unknown " + id);
    }
}
