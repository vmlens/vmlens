package com.vmlens.report.assertion;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.description.ThreadOrLoopDescription;

public class OnDescriptionAndLeftBeforeRightNoOp implements OnDescriptionAndLeftBeforeRight {

    @Override
    public void startRun(int loopId, int runId) {

    }

    @Override
    public void onLeftBeforeRight(LeftBeforeRight leftBeforeRight) {
    }

    @Override
    public void onClassDescription(ClassDescription classDescription) {

    }

    @Override
    public void onThreadOrLoopDescription(ThreadOrLoopDescription threadOrLoopDescription) {

    }
}
