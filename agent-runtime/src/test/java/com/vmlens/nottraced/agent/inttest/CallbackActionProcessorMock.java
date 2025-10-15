package com.vmlens.nottraced.agent.inttest;

import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;

import java.util.HashMap;
import java.util.Map;

public class CallbackActionProcessorMock implements CallbackActionProcessor {

    private final Map<Class,Integer> callbackActionToCount = new HashMap<>();

    @Override
    public void vmlensApiClose(Object o) {

    }

    @Override
    public boolean vmlensApiHasNext(Object o) {
        return false;
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
