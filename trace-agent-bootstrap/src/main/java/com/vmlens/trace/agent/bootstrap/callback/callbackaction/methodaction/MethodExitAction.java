package com.vmlens.trace.agent.bootstrap.callback.callbackaction.methodaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.specialevents.LastActionInThreadRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldUpdaterRepository;
import com.vmlens.trace.agent.bootstrap.strategy.MethodContext;
import com.vmlens.trace.agent.bootstrap.strategy.MethodStrategyAdapter;

import static com.vmlens.trace.agent.bootstrap.strategy.MethodContext.methodExitContext;

public class MethodExitAction implements CallbackAction  {

    private final Object object;
    private final int methodId;
    private final Object returnValue;
    private final Object objectParam;
    private final String stringParam;
    private final MethodStrategyAdapter methodStrategyAdapter;
    private final FieldUpdaterRepository fieldUpdaterRepository;


    private MethodExitAction(Object object,
                             int methodId,
                             Object returnValue,
                             Object objectParam,
                             String stringParam,
                             MethodStrategyAdapter methodStrategyAdapter,
                             FieldUpdaterRepository fieldUpdaterRepository) {
        this.object = object;
        this.methodId = methodId;
        this.returnValue = returnValue;
        this.objectParam = objectParam;
        this.stringParam = stringParam;
        this.methodStrategyAdapter = methodStrategyAdapter;
        this.fieldUpdaterRepository = fieldUpdaterRepository;
    }

    public static MethodExitAction methodExitAction(Object object,
                                                    int methodId,
                                                    MethodStrategyAdapter methodStrategyAdapter) {
        return new MethodExitAction(object,methodId,null,null,null,methodStrategyAdapter,null);
    }

    public static MethodExitAction methodExitActionWithReturnValue(Object object,
                                                                   int methodId,
                                                                   Object returnValue,
                                                                   MethodStrategyAdapter methodStrategyAdapter,
                                                                   FieldUpdaterRepository fieldUpdaterRepository) {
        return new MethodExitAction(object,methodId,returnValue,null,null,methodStrategyAdapter,fieldUpdaterRepository);
    }

    public static MethodExitAction methodExitActionWithObjectStringParamObjectReturn(
                                                                   int methodId,
                                                                   Object returnValue,
                                                                   Object objectParam,
                                                                   String stringParam,
                                                                   MethodStrategyAdapter methodStrategyAdapter,
                                                                   FieldUpdaterRepository fieldUpdaterRepository) {
        return new MethodExitAction(null,methodId,returnValue,objectParam,stringParam,methodStrategyAdapter,fieldUpdaterRepository);
    }


    public static MethodExitAction methodExitActionWithObjectParam(Object object,
                                                                   int methodId,
                                                                   Object objectParam,
                                                                   MethodStrategyAdapter methodStrategyAdapter,
                                                                   FieldUpdaterRepository fieldUpdaterRepository) {
        return new MethodExitAction(object,methodId,null,objectParam,null,methodStrategyAdapter,fieldUpdaterRepository);
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        MethodContext context = methodExitContext(object,methodId,returnValue,objectParam,stringParam,inTestActionProcessor,fieldUpdaterRepository);
        methodStrategyAdapter.methodExit(context);
    }

    @Override
    public Integer isFirstMethodInThread(CheckIsThreadRun checkIsThreadRun) {
        return null;
    }

    @Override
    public boolean couldBeLastMethodInThread(ThreadLocalWhenInTest dataWhenInTest) {
        // firstMethodInThread is null for main thread (thread index == 0)
        if(dataWhenInTest.firstMethodInThread() == null) {
            return false;
        }
        return dataWhenInTest.firstMethodInThread().methodId() == methodId;
    }

    @Override
    public RuntimeEvent isLastMethodInThread(ThreadLocalWhenInTest dataWhenInTest, int stackTraceDepth) {
        if(dataWhenInTest.firstMethodInThread().stacktraceDepth() ==
                    stackTraceDepth) {
                return new LastActionInThreadRuntimeEvent(methodId,dataWhenInTest.threadIndex());
        }
        return null;
    }

}
