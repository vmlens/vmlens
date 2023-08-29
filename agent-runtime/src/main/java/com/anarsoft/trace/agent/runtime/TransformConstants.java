package com.anarsoft.trace.agent.runtime;

public class TransformConstants {

    public static final String CALLBACK_CLASS_FIELD_ACCESS = "com/vmlens/trace/agent/bootstrap/callback/FieldAccessCallback";
    public static final String CALLBACK_CLASS_SYNCHRONIZED_STATEMENT = "com/vmlens/trace/agent/bootstrap/callback/SynchronizedStatementCallback";
    public static final String CALLBACK_CLASS_LOCK_STATEMENT = "com/vmlens/trace/agent/bootstrap/callback/LockStatementCallback";
    public static final String INTERFACE_NAME = "com/vmlens/trace/agent/bootstrap/ThreadIdForField";
    public static final String CALLBACK_CLASS_METHOD = "com/vmlens/trace/agent/bootstrap/callback/MethodCallback";
    public static final String CALLBACK_CLASS_STRACKTRACE_BASED_FILTER = "com/vmlens/trace/agent/bootstrap/callback/StackTraceBasedFilterCallback";

    public static final String FIELD_NAME = "_pAnarsoft_";
    public static final String SET_METHOD = "_pAnarsoft_set_";
    public static final String GET_METHOD = "_pAnarsoft_get_";

    private TransformConstants() {
    }


}
