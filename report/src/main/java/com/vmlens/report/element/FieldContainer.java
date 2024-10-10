package com.vmlens.report.element;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.FieldAccessDescription;
import com.anarsoft.trace.agent.description.FieldInClassDescription;

public class FieldContainer implements Container {

    // all fields can be null
    private ClassDescription classDescription;
    private FieldInClassDescription fieldInClassDescription;
    private FieldAccessDescription FieldAccessDescription;

    public ClassDescription classDescription() {
        return classDescription;
    }

    public void setFieldAccessDescription(com.anarsoft.trace.agent.description.FieldAccessDescription fieldAccessDescription) {
        FieldAccessDescription = fieldAccessDescription;
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
