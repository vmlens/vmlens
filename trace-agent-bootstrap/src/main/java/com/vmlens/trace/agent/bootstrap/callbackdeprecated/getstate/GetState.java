package com.vmlens.trace.agent.bootstrap.callbackdeprecated.getstate;


import com.vmlens.trace.agent.bootstrap.callbackdeprecated.state.StateAccess;

public interface GetState {

    StateAccess getState(Object in);

    void resetState(Object in);


}
