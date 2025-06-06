package com.vmlens.report.assertion;

import com.vmlens.trace.agent.bootstrap.description.ClassDescription;
import com.vmlens.trace.agent.bootstrap.description.ThreadOrLoopDescription;

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
