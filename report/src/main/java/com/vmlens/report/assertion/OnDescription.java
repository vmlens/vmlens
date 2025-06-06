package com.vmlens.report.assertion;

import com.vmlens.trace.agent.bootstrap.description.ClassDescription;
import com.vmlens.trace.agent.bootstrap.description.ThreadOrLoopDescription;

public interface OnDescription {
    
    void onClassDescription(ClassDescription classDescription);
    void onThreadOrLoopDescription(ThreadOrLoopDescription threadOrLoopDescription);
    
}
