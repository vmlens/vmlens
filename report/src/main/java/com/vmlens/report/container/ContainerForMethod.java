package com.vmlens.report.container;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.MethodDescription;

public class ContainerForMethod implements Container {

    // all fields can be null
    private ClassDescription classDescription;
    private MethodDescription methodDescription;

    public void setClassDescription(ClassDescription classDescription) {
        this.classDescription = classDescription;
    }

    public void setMethodDescription(MethodDescription methodDescription) {
        this.methodDescription = methodDescription;
    }


    @Override
    public String getName() {
        if (classDescription == null || methodDescription == null) {
            return null;
        }

        return classDescription.name() + "." + methodDescription.name();
    }
}
