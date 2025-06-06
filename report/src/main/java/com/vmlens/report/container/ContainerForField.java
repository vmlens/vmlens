package com.vmlens.report.container;

import com.vmlens.trace.agent.bootstrap.description.ClassDescription;
import com.vmlens.trace.agent.bootstrap.description.FieldInClassDescription;

public class ContainerForField implements Container {

    // all fields can be null
    private ClassDescription classDescription;
    private FieldInClassDescription fieldInClassDescription;

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
        if (classDescription == null || fieldInClassDescription == null) {
            return null;
        }

        return classDescription.name() + "." + fieldInClassDescription.name();
    }
}
