package com.vmlens.trace.agent.bootstrap.callback.gen;

import com.vmlens.trace.agent.bootstrap.callback.*;
import java.lang.invoke.VarHandle;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;


public class VarHandleCallbackGen extends VarHandleCallback
{

public static byte getVolatile3 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
byte  result = (byte) 
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static double getVolatile8 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
double  result = (double) 
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static float getVolatile6 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
float  result = (float) 
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static int getVolatile5 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
int  result = (int) 
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static long getVolatile7 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
long  result = (long) 
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static char getVolatile2 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
char  result = (char) 
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static short getVolatile4 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
short  result = (short) 
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static boolean getVolatile1 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
boolean  result = (boolean) 
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static java.lang.Object getVolatile10 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
java.lang.Object  result = (java.lang.Object) 
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static void getVolatile0 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
varHandle.getVolatile  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
}


public static void setVolatile3 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile8 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile6 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile5 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile7 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile2 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile4 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile1 ( VarHandle varHandle ,  Object obj, boolean value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile10 ( VarHandle varHandle ,  Object obj, java.lang.Object value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static byte getAcquire3 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
byte  result = (byte) 
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static double getAcquire8 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
double  result = (double) 
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static float getAcquire6 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
float  result = (float) 
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static int getAcquire5 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
int  result = (int) 
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static long getAcquire7 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
long  result = (long) 
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static char getAcquire2 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
char  result = (char) 
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static short getAcquire4 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
short  result = (short) 
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static boolean getAcquire1 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
boolean  result = (boolean) 
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static java.lang.Object getAcquire10 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
java.lang.Object  result = (java.lang.Object) 
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static void getAcquire0 ( VarHandle varHandle ,  Object obj, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
varHandle.getAcquire  ( obj );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_READ );
}


public static void setRelease3 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease8 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease6 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease5 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease7 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease2 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease4 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease1 ( VarHandle varHandle ,  Object obj, boolean value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease10 ( VarHandle varHandle ,  Object obj, java.lang.Object value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static byte getAndAdd33 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndAdd88 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndAdd66 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndAdd55 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndAdd77 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndAdd22 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndAdd44 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndAdd30 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd80 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd60 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd50 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd70 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd20 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd40 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static byte getAndBitwiseXor33 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndBitwiseXor88 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndBitwiseXor66 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndBitwiseXor55 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndBitwiseXor77 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndBitwiseXor22 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndBitwiseXor44 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndBitwiseXor30 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor80 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor60 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor50 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor70 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor20 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor40 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static byte getAndBitwiseOr33 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndBitwiseOr88 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndBitwiseOr66 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndBitwiseOr55 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndBitwiseOr77 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndBitwiseOr22 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndBitwiseOr44 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndBitwiseOr30 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr80 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr60 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr50 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr70 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr20 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr40 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static byte getAndBitwiseAnd33 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndBitwiseAnd88 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndBitwiseAnd66 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndBitwiseAnd55 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndBitwiseAnd77 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndBitwiseAnd22 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndBitwiseAnd44 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndBitwiseAnd30 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd80 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd60 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd50 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd70 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd20 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd40 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static byte getAndSet33 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndSet88 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndSet66 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndSet55 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndSet77 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndSet22 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndSet44 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean getAndSet11 ( VarHandle varHandle ,  Object obj, boolean value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static java.lang.Object getAndSet1010 ( VarHandle varHandle ,  Object obj, java.lang.Object value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
java.lang.Object  result = (java.lang.Object) 
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndSet30 ( VarHandle varHandle ,  Object obj, byte value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet80 ( VarHandle varHandle ,  Object obj, double value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet60 ( VarHandle varHandle ,  Object obj, float value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet50 ( VarHandle varHandle ,  Object obj, int value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet70 ( VarHandle varHandle ,  Object obj, long value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet20 ( VarHandle varHandle ,  Object obj, char value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet40 ( VarHandle varHandle ,  Object obj, short value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet10 ( VarHandle varHandle ,  Object obj, boolean value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet100 ( VarHandle varHandle ,  Object obj, java.lang.Object value, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,value );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static boolean compareAndSet3 ( VarHandle varHandle ,  Object obj, byte valueOne , byte valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet8 ( VarHandle varHandle ,  Object obj, double valueOne , double valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet6 ( VarHandle varHandle ,  Object obj, float valueOne , float valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet5 ( VarHandle varHandle ,  Object obj, int valueOne , int valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet7 ( VarHandle varHandle ,  Object obj, long valueOne , long valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet2 ( VarHandle varHandle ,  Object obj, char valueOne , char valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet4 ( VarHandle varHandle ,  Object obj, short valueOne , short valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet1 ( VarHandle varHandle ,  Object obj, boolean valueOne , boolean valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet10 ( VarHandle varHandle ,  Object obj, java.lang.Object valueOne , java.lang.Object valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet3 ( VarHandle varHandle ,  Object obj, byte valueOne , byte valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet8 ( VarHandle varHandle ,  Object obj, double valueOne , double valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet6 ( VarHandle varHandle ,  Object obj, float valueOne , float valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet5 ( VarHandle varHandle ,  Object obj, int valueOne , int valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet7 ( VarHandle varHandle ,  Object obj, long valueOne , long valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet2 ( VarHandle varHandle ,  Object obj, char valueOne , char valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet4 ( VarHandle varHandle ,  Object obj, short valueOne , short valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet1 ( VarHandle varHandle ,  Object obj, boolean valueOne , boolean valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet10 ( VarHandle varHandle ,  Object obj, java.lang.Object valueOne , java.lang.Object valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static byte compareAndExchange33 ( VarHandle varHandle ,  Object obj, byte valueOne , byte valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double compareAndExchange88 ( VarHandle varHandle ,  Object obj, double valueOne , double valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float compareAndExchange66 ( VarHandle varHandle ,  Object obj, float valueOne , float valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int compareAndExchange55 ( VarHandle varHandle ,  Object obj, int valueOne , int valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long compareAndExchange77 ( VarHandle varHandle ,  Object obj, long valueOne , long valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char compareAndExchange22 ( VarHandle varHandle ,  Object obj, char valueOne , char valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short compareAndExchange44 ( VarHandle varHandle ,  Object obj, short valueOne , short valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndExchange11 ( VarHandle varHandle ,  Object obj, boolean valueOne , boolean valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static java.lang.Object compareAndExchange1010 ( VarHandle varHandle ,  Object obj, java.lang.Object valueOne , java.lang.Object valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
java.lang.Object  result = (java.lang.Object) 
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void compareAndExchange30 ( VarHandle varHandle ,  Object obj, byte valueOne , byte valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange80 ( VarHandle varHandle ,  Object obj, double valueOne , double valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange60 ( VarHandle varHandle ,  Object obj, float valueOne , float valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange50 ( VarHandle varHandle ,  Object obj, int valueOne , int valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange70 ( VarHandle varHandle ,  Object obj, long valueOne , long valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange20 ( VarHandle varHandle ,  Object obj, char valueOne , char valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange40 ( VarHandle varHandle ,  Object obj, short valueOne , short valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange10 ( VarHandle varHandle ,  Object obj, boolean valueOne , boolean valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange100 ( VarHandle varHandle ,  Object obj, java.lang.Object valueOne , java.lang.Object valueTwo, int methodId  )
{

beforeVolatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,valueOne,valueTwo );


volatileAccess( varHandle , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static byte getVolatile3 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
byte  result = (byte) 
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static double getVolatile8 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
double  result = (double) 
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static float getVolatile6 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
float  result = (float) 
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static int getVolatile5 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
int  result = (int) 
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static long getVolatile7 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
long  result = (long) 
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static char getVolatile2 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
char  result = (char) 
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static short getVolatile4 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
short  result = (short) 
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static boolean getVolatile1 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
boolean  result = (boolean) 
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static java.lang.Object getVolatile10 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
java.lang.Object  result = (java.lang.Object) 
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static void getVolatile0 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
varHandle.getVolatile  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
}


public static void setVolatile3 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile8 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile6 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile5 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile7 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile2 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile4 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile1 ( VarHandle varHandle ,  Object obj, int index , boolean value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setVolatile10 ( VarHandle varHandle ,  Object obj, int index , java.lang.Object value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setVolatile  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static byte getAcquire3 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
byte  result = (byte) 
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static double getAcquire8 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
double  result = (double) 
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static float getAcquire6 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
float  result = (float) 
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static int getAcquire5 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
int  result = (int) 
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static long getAcquire7 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
long  result = (long) 
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static char getAcquire2 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
char  result = (char) 
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static short getAcquire4 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
short  result = (short) 
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static boolean getAcquire1 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
boolean  result = (boolean) 
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static java.lang.Object getAcquire10 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
java.lang.Object  result = (java.lang.Object) 
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
return  result; 
}


public static void getAcquire0 ( VarHandle varHandle ,  Object obj, int index , int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
varHandle.getAcquire  ( obj,index );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_READ );
}


public static void setRelease3 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease8 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease6 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease5 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease7 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease2 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease4 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease1 ( VarHandle varHandle ,  Object obj, int index , boolean value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static void setRelease10 ( VarHandle varHandle ,  Object obj, int index , java.lang.Object value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
varHandle.setRelease  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_WRITE );
}


public static byte getAndAdd33 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndAdd88 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndAdd66 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndAdd55 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndAdd77 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndAdd22 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndAdd44 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndAdd30 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd80 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd60 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd50 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd70 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd20 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndAdd40 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndAdd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static byte getAndBitwiseXor33 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndBitwiseXor88 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndBitwiseXor66 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndBitwiseXor55 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndBitwiseXor77 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndBitwiseXor22 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndBitwiseXor44 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndBitwiseXor30 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor80 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor60 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor50 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor70 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor20 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseXor40 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseXor  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static byte getAndBitwiseOr33 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndBitwiseOr88 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndBitwiseOr66 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndBitwiseOr55 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndBitwiseOr77 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndBitwiseOr22 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndBitwiseOr44 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndBitwiseOr30 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr80 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr60 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr50 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr70 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr20 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseOr40 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseOr  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static byte getAndBitwiseAnd33 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndBitwiseAnd88 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndBitwiseAnd66 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndBitwiseAnd55 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndBitwiseAnd77 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndBitwiseAnd22 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndBitwiseAnd44 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndBitwiseAnd30 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd80 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd60 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd50 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd70 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd20 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndBitwiseAnd40 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndBitwiseAnd  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static byte getAndSet33 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double getAndSet88 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float getAndSet66 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int getAndSet55 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long getAndSet77 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char getAndSet22 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short getAndSet44 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean getAndSet11 ( VarHandle varHandle ,  Object obj, int index , boolean value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static java.lang.Object getAndSet1010 ( VarHandle varHandle ,  Object obj, int index , java.lang.Object value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
java.lang.Object  result = (java.lang.Object) 
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void getAndSet30 ( VarHandle varHandle ,  Object obj, int index , byte value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet80 ( VarHandle varHandle ,  Object obj, int index , double value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet60 ( VarHandle varHandle ,  Object obj, int index , float value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet50 ( VarHandle varHandle ,  Object obj, int index , int value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet70 ( VarHandle varHandle ,  Object obj, int index , long value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet20 ( VarHandle varHandle ,  Object obj, int index , char value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet40 ( VarHandle varHandle ,  Object obj, int index , short value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet10 ( VarHandle varHandle ,  Object obj, int index , boolean value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void getAndSet100 ( VarHandle varHandle ,  Object obj, int index , java.lang.Object value, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.getAndSet  ( obj,index,value );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static boolean compareAndSet3 ( VarHandle varHandle ,  Object obj, int index , byte valueOne , byte valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet8 ( VarHandle varHandle ,  Object obj, int index , double valueOne , double valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet6 ( VarHandle varHandle ,  Object obj, int index , float valueOne , float valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet5 ( VarHandle varHandle ,  Object obj, int index , int valueOne , int valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet7 ( VarHandle varHandle ,  Object obj, int index , long valueOne , long valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet2 ( VarHandle varHandle ,  Object obj, int index , char valueOne , char valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet4 ( VarHandle varHandle ,  Object obj, int index , short valueOne , short valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet1 ( VarHandle varHandle ,  Object obj, int index , boolean valueOne , boolean valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndSet10 ( VarHandle varHandle ,  Object obj, int index , java.lang.Object valueOne , java.lang.Object valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet3 ( VarHandle varHandle ,  Object obj, int index , byte valueOne , byte valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet8 ( VarHandle varHandle ,  Object obj, int index , double valueOne , double valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet6 ( VarHandle varHandle ,  Object obj, int index , float valueOne , float valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet5 ( VarHandle varHandle ,  Object obj, int index , int valueOne , int valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet7 ( VarHandle varHandle ,  Object obj, int index , long valueOne , long valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet2 ( VarHandle varHandle ,  Object obj, int index , char valueOne , char valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet4 ( VarHandle varHandle ,  Object obj, int index , short valueOne , short valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet1 ( VarHandle varHandle ,  Object obj, int index , boolean valueOne , boolean valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean weakCompareAndSet10 ( VarHandle varHandle ,  Object obj, int index , java.lang.Object valueOne , java.lang.Object valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.weakCompareAndSet  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static byte compareAndExchange33 ( VarHandle varHandle ,  Object obj, int index , byte valueOne , byte valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
byte  result = (byte) 
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static double compareAndExchange88 ( VarHandle varHandle ,  Object obj, int index , double valueOne , double valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
double  result = (double) 
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static float compareAndExchange66 ( VarHandle varHandle ,  Object obj, int index , float valueOne , float valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
float  result = (float) 
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static int compareAndExchange55 ( VarHandle varHandle ,  Object obj, int index , int valueOne , int valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
int  result = (int) 
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static long compareAndExchange77 ( VarHandle varHandle ,  Object obj, int index , long valueOne , long valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
long  result = (long) 
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static char compareAndExchange22 ( VarHandle varHandle ,  Object obj, int index , char valueOne , char valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
char  result = (char) 
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static short compareAndExchange44 ( VarHandle varHandle ,  Object obj, int index , short valueOne , short valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
short  result = (short) 
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static boolean compareAndExchange11 ( VarHandle varHandle ,  Object obj, int index , boolean valueOne , boolean valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
boolean  result = (boolean) 
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static java.lang.Object compareAndExchange1010 ( VarHandle varHandle ,  Object obj, int index , java.lang.Object valueOne , java.lang.Object valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
java.lang.Object  result = (java.lang.Object) 
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
return  result; 
}


public static void compareAndExchange30 ( VarHandle varHandle ,  Object obj, int index , byte valueOne , byte valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange80 ( VarHandle varHandle ,  Object obj, int index , double valueOne , double valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange60 ( VarHandle varHandle ,  Object obj, int index , float valueOne , float valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange50 ( VarHandle varHandle ,  Object obj, int index , int valueOne , int valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange70 ( VarHandle varHandle ,  Object obj, int index , long valueOne , long valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange20 ( VarHandle varHandle ,  Object obj, int index , char valueOne , char valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange40 ( VarHandle varHandle ,  Object obj, int index , short valueOne , short valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange10 ( VarHandle varHandle ,  Object obj, int index , boolean valueOne , boolean valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


public static void compareAndExchange100 ( VarHandle varHandle ,  Object obj, int index , java.lang.Object valueOne , java.lang.Object valueTwo, int methodId  )
{

beforeVolatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
varHandle.compareAndExchange  ( obj,index,valueOne,valueTwo );


volatileAccessArray( index , obj   , methodId , MemoryAccessType.IS_ATOMIC );
}


}