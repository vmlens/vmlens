package com.vmlens.trace.agent.bootstrap.callback.obj.gen;

import com.vmlens.trace.agent.bootstrap.callback.obj.*;
import java.util.*;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

/* templates/MapTemplateCallback.mustache */

public class HashMapJDK8CallbackGen extends HashMapCallback
{

public static java.lang.Object putIfAbsent (  HashMap obj   ,java.lang.Object a0,java.lang.Object a1  , int methodId  )
{


java.lang.Object result = 
obj.putIfAbsent  (  a0, a1 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static boolean remove (  HashMap obj   ,java.lang.Object a0,java.lang.Object a1  , int methodId  )
{


boolean result = 
obj.remove  (  a0, a1 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static boolean replace (  HashMap obj   ,java.lang.Object a0,java.lang.Object a1,java.lang.Object a2  , int methodId  )
{


boolean result = 
obj.replace  (  a0, a1, a2 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static java.lang.Object replace (  HashMap obj   ,java.lang.Object a0,java.lang.Object a1  , int methodId  )
{


java.lang.Object result = 
obj.replace  (  a0, a1 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static java.lang.Object computeIfAbsent (  HashMap obj   ,java.lang.Object a0,java.util.function.Function a1  , int methodId  )
{


java.lang.Object result = 
obj.computeIfAbsent  (  a0, a1 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static java.lang.Object computeIfPresent (  HashMap obj   ,java.lang.Object a0,java.util.function.BiFunction a1  , int methodId  )
{


java.lang.Object result = 
obj.computeIfPresent  (  a0, a1 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static java.lang.Object compute (  HashMap obj   ,java.lang.Object a0,java.util.function.BiFunction a1  , int methodId  )
{


java.lang.Object result = 
obj.compute  (  a0, a1 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static java.lang.Object merge (  HashMap obj   ,java.lang.Object a0,java.lang.Object a1,java.util.function.BiFunction a2  , int methodId  )
{


java.lang.Object result = 
obj.merge  (  a0, a1, a2 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
return result;
}
public static void replaceAll (  HashMap obj   ,java.util.function.BiFunction a0  , int methodId  )
{


obj.replaceAll  (  a0 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
}
}
public static java.lang.Object getOrDefault (  HashMap obj   ,java.lang.Object a0,java.lang.Object a1  , int methodId  )
{


java.lang.Object result = 
obj.getOrDefault  (  a0, a1 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
}
return result;
}
public static void forEach (  HashMap obj   ,java.util.function.BiConsumer a0  , int methodId  )
{


obj.forEach  (  a0 );

if( obj instanceof  java.util.HashMap )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
}
}
}
 