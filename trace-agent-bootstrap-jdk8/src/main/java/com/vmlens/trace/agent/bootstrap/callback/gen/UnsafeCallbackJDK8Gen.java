package com.vmlens.trace.agent.bootstrap.callback.gen;

import com.vmlens.trace.agent.bootstrap.callback.field.CallbackObject;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

public  class UnsafeCallbackJDK8Gen  {
	




public static  int getAndAddInt(sun.misc.Unsafe unsafe, Object in , long offset , int a, int methodId)
	{

		 int result = unsafe.getAndAddInt(in, offset, a);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);
		
		return result;
	}

public static  long getAndAddLong(sun.misc.Unsafe unsafe, Object in , long offset , long a, int methodId)
	{

		 long result = unsafe.getAndAddLong(in, offset, a);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);
		
		return result;
	}

public static  int getAndSetInt(sun.misc.Unsafe unsafe, Object in , long offset , int a, int methodId)
	{

		 int result = unsafe.getAndSetInt(in, offset, a);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);
		
		return result;
	}

public static  long getAndSetLong(sun.misc.Unsafe unsafe, Object in , long offset , long a, int methodId)
	{

		 long result = unsafe.getAndSetLong(in, offset, a);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);
		
		return result;
	}

public static  java.lang.Object getAndSetObject(sun.misc.Unsafe unsafe, Object in , long offset , java.lang.Object a, int methodId)
	{

		 java.lang.Object result = unsafe.getAndSetObject(in, offset, a);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);
		
		return result;
	}

}

