package com.vmlens.trace.agent.bootstrap.callbackdeprecated.gen;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.CallbackObject;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.MemoryAccessType;

public  class UnsafeCallbackGen  {
	


public static void putByteVolatile(sun.misc.Unsafe unsafe, Object in , long offset , byte a, int methodId)
	{

		 	unsafe.putByteVolatile(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putDoubleVolatile(sun.misc.Unsafe unsafe, Object in , long offset , double a, int methodId)
	{

		 	unsafe.putDoubleVolatile(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putFloatVolatile(sun.misc.Unsafe unsafe, Object in , long offset , float a, int methodId)
	{

		 	unsafe.putFloatVolatile(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putIntVolatile(sun.misc.Unsafe unsafe, Object in , long offset , int a, int methodId)
	{

		 	unsafe.putIntVolatile(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putLongVolatile(sun.misc.Unsafe unsafe, Object in , long offset , long a, int methodId)
	{

		 	unsafe.putLongVolatile(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putCharVolatile(sun.misc.Unsafe unsafe, Object in , long offset , char a, int methodId)
	{

		 	unsafe.putCharVolatile(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putShortVolatile(sun.misc.Unsafe unsafe, Object in , long offset , short a, int methodId)
	{

		 	unsafe.putShortVolatile(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putBooleanVolatile(sun.misc.Unsafe unsafe, Object in , long offset , boolean a, int methodId)
	{

		 	unsafe.putBooleanVolatile(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putObjectVolatile(sun.misc.Unsafe unsafe, Object in , long offset , java.lang.Object a, int methodId)
	{

		 	unsafe.putObjectVolatile(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}










public static void putOrderedInt(sun.misc.Unsafe unsafe, Object in , long offset , int a, int methodId)
	{

		 	unsafe.putOrderedInt(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putOrderedLong(sun.misc.Unsafe unsafe, Object in , long offset , long a, int methodId)
	{

		 	unsafe.putOrderedLong(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putOrderedObject(sun.misc.Unsafe unsafe, Object in , long offset , java.lang.Object a, int methodId)
	{

		 	unsafe.putOrderedObject(in, offset, a);
		 
			CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}




public static boolean compareAndSwapInt(sun.misc.Unsafe unsafe, Object in , long offset , int a, int b, int methodId)
	{
	
	
	CallbackObject.beforeVolatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);

		 	boolean result = unsafe.compareAndSwapInt(in, offset, a, b);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);
		
		return result;
	}


public static boolean compareAndSwapLong(sun.misc.Unsafe unsafe, Object in , long offset , long a, long b, int methodId)
	{
	
	
	CallbackObject.beforeVolatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);

		 	boolean result = unsafe.compareAndSwapLong(in, offset, a, b);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);
		
		return result;
	}


public static boolean compareAndSwapObject(sun.misc.Unsafe unsafe, Object in , long offset , java.lang.Object a, java.lang.Object b, int methodId)
	{
	
	
	CallbackObject.beforeVolatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);

		 	boolean result = unsafe.compareAndSwapObject(in, offset, a, b);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId , MemoryAccessType.IS_ATOMIC);
		
		return result;
	}




	public static byte getByteVolatile(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		byte result =  unsafe.getByteVolatile (in, offset);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static double getDoubleVolatile(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		double result =  unsafe.getDoubleVolatile (in, offset);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static float getFloatVolatile(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		float result =  unsafe.getFloatVolatile (in, offset);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static int getIntVolatile(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		int result =  unsafe.getIntVolatile (in, offset);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static long getLongVolatile(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		long result =  unsafe.getLongVolatile (in, offset);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static char getCharVolatile(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		char result =  unsafe.getCharVolatile (in, offset);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static short getShortVolatile(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		short result =  unsafe.getShortVolatile (in, offset);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static boolean getBooleanVolatile(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		boolean result =  unsafe.getBooleanVolatile (in, offset);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static java.lang.Object getObjectVolatile(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		java.lang.Object result =  unsafe.getObjectVolatile (in, offset);
		
		CallbackObject.volatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}





	public static byte getByte(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		byte result =  unsafe.getByte (in, offset);
		
		CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static double getDouble(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		double result =  unsafe.getDouble (in, offset);
		
		CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static float getFloat(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		float result =  unsafe.getFloat (in, offset);
		
		CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static int getInt(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		int result =  unsafe.getInt (in, offset);
		
		CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static long getLong(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		long result =  unsafe.getLong (in, offset);
		
		CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static char getChar(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		char result =  unsafe.getChar (in, offset);
		
		CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static short getShort(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		short result =  unsafe.getShort (in, offset);
		
		CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static boolean getBoolean(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		boolean result =  unsafe.getBoolean (in, offset);
		
		CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}


	public static java.lang.Object getObject(sun.misc.Unsafe unsafe, Object in , long offset, int methodId )
	{

		java.lang.Object result =  unsafe.getObject (in, offset);
		
		CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,   MemoryAccessType.IS_READ);

		return result;
	}

		
		

public static void putByte(sun.misc.Unsafe unsafe, Object in , long offset , byte a, int methodId)
	{

		 	unsafe.putByte(in, offset, a);
		 
			CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putDouble(sun.misc.Unsafe unsafe, Object in , long offset , double a, int methodId)
	{

		 	unsafe.putDouble(in, offset, a);
		 
			CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putFloat(sun.misc.Unsafe unsafe, Object in , long offset , float a, int methodId)
	{

		 	unsafe.putFloat(in, offset, a);
		 
			CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putInt(sun.misc.Unsafe unsafe, Object in , long offset , int a, int methodId)
	{

		 	unsafe.putInt(in, offset, a);
		 
			CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putLong(sun.misc.Unsafe unsafe, Object in , long offset , long a, int methodId)
	{

		 	unsafe.putLong(in, offset, a);
		 
			CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putChar(sun.misc.Unsafe unsafe, Object in , long offset , char a, int methodId)
	{

		 	unsafe.putChar(in, offset, a);
		 
			CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putShort(sun.misc.Unsafe unsafe, Object in , long offset , short a, int methodId)
	{

		 	unsafe.putShort(in, offset, a);
		 
			CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putBoolean(sun.misc.Unsafe unsafe, Object in , long offset , boolean a, int methodId)
	{

		 	unsafe.putBoolean(in, offset, a);
		 
			CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}


public static void putObject(sun.misc.Unsafe unsafe, Object in , long offset , java.lang.Object a, int methodId)
	{

		 	unsafe.putObject(in, offset, a);
		 
			CallbackObject.nonVolatileAccessWithOffset(in, unsafe , offset , methodId ,  MemoryAccessType.IS_WRITE);
	}

		
		
		
		
		
		
		
		
 
	
}

