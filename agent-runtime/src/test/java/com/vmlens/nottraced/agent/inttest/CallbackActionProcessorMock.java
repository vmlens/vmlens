package com.vmlens.nottraced.agent.inttest;

import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.InitializationAction;

import java.util.HashMap;
import java.util.Map;

public class CallbackActionProcessorMock implements CallbackActionProcessor {

    private final Map<Class,Integer> callbackActionToCount = new HashMap<>();

    @Override
    public void vmlensApiClose(Object o) {

    }

    @Override
    public void initialize(InitializationAction initializationAction) {

    }

    @Override
    public boolean vmlensApiHasNext(Object o) {
        return false;
    }

    @Override
    public void automaticTestSuccess(int i, String s) {

    }

    @Override
    public void automaticTestMethod(int i, int i1, int i2) {

    }

    @Override
    public boolean process(CallbackAction callbackAction) {
        add(callbackAction.getClass());
        return true;
    }


    @Override
    public void startDoNotTrace() {
    }

    @Override
    public void endDoNotTrace() {
    }

    public void add(Class cl) {
        callbackActionToCount.compute( cl, ( k ,v ) -> {
            if(v == null) {
                return 1;
            }
            return v + 1;
        } );
    }

    public int getCount(Class cl) {
        Integer count = callbackActionToCount.get(cl);
        if(count ==  null) {
            return 0;
        }
        return count;
    }

}
