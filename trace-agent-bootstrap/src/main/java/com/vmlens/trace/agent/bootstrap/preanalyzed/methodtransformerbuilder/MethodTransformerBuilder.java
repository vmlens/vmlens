package com.vmlens.trace.agent.bootstrap.preanalyzed.methodtransformerbuilder;

/**
 * note that only one call is allowed, e.g. mixture is currently not supported
 */

public interface MethodTransformerBuilder {

    void setWithoutParam();
    void setWithIntParam();
    void setWithoutParamAndWithObjectReturn();
    void setWithObjectParamAtReturn();
    void setWithObjectStringParamAtReturn();
    void setWithObjectPlaceHolderStringParamAtReturn();

}
