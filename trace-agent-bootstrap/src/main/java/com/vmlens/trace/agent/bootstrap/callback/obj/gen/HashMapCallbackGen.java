package com.vmlens.trace.agent.bootstrap.callback.obj.gen;

import com.vmlens.trace.agent.bootstrap.callback.obj.*;
import java.util.*;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

/* templates/MapTemplateCallback.mustache */

public class HashMapCallbackGen extends HashMapCallback
{

public static java.lang.Object put (  HashMap obj   ,java.lang.Object a0,java.lang.Object a1  , int methodId  )
{


java.lang.Object result = 
obj.put  (  a0, a1 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static void putAll (  HashMap obj   ,java.util.Map a0  , int methodId  )
{


obj.putAll  (  a0 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
}
public static java.lang.Object remove (  HashMap obj   ,java.lang.Object a0  , int methodId  )
{


java.lang.Object result = 
obj.remove  (  a0 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static void clear (  HashMap obj     , int methodId  )
{


obj.clear  (  );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
}
public static int size (  HashMap obj     , int methodId  )
{


int result = 
obj.size  (  );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
}
return result;
}
public static boolean isEmpty (  HashMap obj     , int methodId  )
{


boolean result = 
obj.isEmpty  (  );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
}
return result;
}
public static java.lang.Object get (  HashMap obj   ,java.lang.Object a0  , int methodId  )
{


java.lang.Object result = 
obj.get  (  a0 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
}
return result;
}
public static boolean containsKey (  HashMap obj   ,java.lang.Object a0  , int methodId  )
{


boolean result = 
obj.containsKey  (  a0 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
}
return result;
}
public static boolean containsValue (  HashMap obj   ,java.lang.Object a0  , int methodId  )
{


boolean result = 
obj.containsValue  (  a0 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
}
return result;
}
public static java.util.Set entrySet (  HashMap obj     , int methodId  )
{


java.util.Set result = 
obj.entrySet  (  );

createDelegate( result , obj  );


return result;
}
public static java.util.Set keySet (  HashMap obj     , int methodId  )
{


java.util.Set result = 
obj.keySet  (  );

createDelegate( result , obj  );


return result;
}
}
 