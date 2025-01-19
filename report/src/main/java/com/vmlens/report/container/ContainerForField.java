package com.vmlens.report.container;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.FieldInClassDescription;

public class ContainerForField implements Container {

    // all fields can be null
    private ClassDescription classDescription;
    private FieldInClassDescription fieldInClassDescription;

    public ClassDescription classDescription() {
        return classDescription;
    }


    public void setClassDescription(ClassDescription classDescription) {
        this.classDescription = classDescription;
    }

    public FieldInClassDescription fieldInClassDescription() {
        return fieldInClassDescription;
    }

    public void setFieldInClassDescription(FieldInClassDescription fieldInClassDescription) {
        this.fieldInClassDescription = fieldInClassDescription;
    }

    @Override
    public String getName() {
        return null;
    }
}
