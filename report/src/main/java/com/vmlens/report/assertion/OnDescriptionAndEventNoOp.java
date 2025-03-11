package com.vmlens.report.assertion;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.ThreadOrLoopDescription;

public class OnDescriptionAndEventNoOp implements OnDescriptionAndEvent {
    
    @Override
    public void onFieldDescription(int loopId, int fieldId, String name) {

    }

    @Override
    public void onVolatileAccessEvent(int loopId, int fieldId, int threadIndex, int operation) {

    }

    @Override
    public void onClassDescription(ClassDescription classDescription) {

    }

    @Override
    public void onThreadOrLoopDescription(ThreadOrLoopDescription threadOrLoopDescription) {

    }
}
