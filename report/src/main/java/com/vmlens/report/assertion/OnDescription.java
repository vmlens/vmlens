package com.vmlens.report.assertion;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.ThreadOrLoopDescription;

public interface OnDescription {
    
    void onClassDescription(ClassDescription classDescription);
    void onThreadOrLoopDescription(ThreadOrLoopDescription threadOrLoopDescription);
    
}
