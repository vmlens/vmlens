package com.vmlens.trace.agent.bootstrap.util;

public class Constants {

	public static final int FIELD_ID_JAVA_UTIL_HASH_MAP = 1;
	public static final int FIELD_ID_JAVA_UTIL_HASH_SET = 4;

	public static final int MAX_PRE_DEFINED_FIELD_ID = FIELD_ID_JAVA_UTIL_HASH_SET;
	
	
	public static final int TYPE_THREAD_NAME_EVENT     = 0; 
	public static final int TYPE_WHILE_LOOP_NAME_EVENT = 1;

    public static final int STAMPED_READ_LOCK = 0;
	public static final int STAMPED_WRITE_LOCK = 1;

    public static final int STAMPED_READ_UNLOCK = 2;
	public static final int STAMPED_WRITE_UNLOCK = 3;
	
	public static final int STAMPED_UNLOCK = 4;
	public static final int STAMPED_PRIVATE = 5;
	
}
