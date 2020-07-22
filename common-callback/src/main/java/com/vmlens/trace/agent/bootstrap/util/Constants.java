package com.vmlens.trace.agent.bootstrap.util;

public class Constants {

	public static final int INTERNAL_QUEUE_LENGTH = 1000;
	
	public static final int WRITE_WRITE_COUNT_EVERY_X = 1000;
	
	public static final int NEW_SLIDING_WINDOW_ID_EVERY_N_THREAD = 200;
	
	
	
	// jedes WRITE_WRITE_COUNT_EVERY_X * NEW_SLIDING_WINDOW_EVERY_N_EVENTS event, 10000
	public static final int NEW_SLIDING_WINDOW_EVERY_N_EVENTS  = 100;
	
	
	public static final int STATIC_MAXIMUM_STACK_TRACE_DEPTH = 15;
	
	
	public static final int FIELD_ID_JAVA_UTIL_HASH_MAP = 1;
	public static final int FIELD_ID_JAVA_UTIL_IDENTITY_HASH_MAP = 2;
	public static final int FIELD_ID_JAVA_UTIL_LINKED_HASH_MAP = 3;
	public static final int FIELD_ID_JAVA_UTIL_HASH_SET = 4;

	public static final int MAX_PRE_DEFINED_FIELD_ID = FIELD_ID_JAVA_UTIL_HASH_SET;
	
	
	public static final int TYPE_THREAD_NAME_EVENT     = 0; 
	public static final int TYPE_WHILE_LOOP_NAME_EVENT = 1; 
	
	//public static final int OVERLOAD_THROW_AWAY_FIELDS_EVENT_QUEUE_SIZE = 20 * INTERNAL_QUEUE_LENGTH;
	
	public static final byte TRUE = 1;
	public static final byte FALSE = 0;
	
	
	
	public static final int STAMPED_READ_LOCK = 0;
	public static final int STAMPED_WRITE_LOCK = 1;
	
	public static final int STAMPED_READ_UNLOCK = 2;
	public static final int STAMPED_WRITE_UNLOCK = 3;
	
	public static final int STAMPED_UNLOCK = 4;
	
	
	public static final int STAMPED_PRIVATE = 5;
	
}
