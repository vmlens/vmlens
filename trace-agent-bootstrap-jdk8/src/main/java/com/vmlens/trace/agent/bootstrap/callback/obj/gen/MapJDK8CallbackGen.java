package com.vmlens.trace.agent.bootstrap.callback.obj.gen;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.obj.MapCallback;

import java.util.Map;

/* templates/MapTemplateCallback.mustache */

public class MapJDK8CallbackGen extends MapCallback
{



public static java.lang.Object putIfAbsent (  Map obj   ,java.lang.Object a0,java.lang.Object a1  , int methodId  )
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


public static boolean remove (  Map obj   ,java.lang.Object a0,java.lang.Object a1  , int methodId  )
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


public static boolean replace (  Map obj   ,java.lang.Object a0,java.lang.Object a1,java.lang.Object a2  , int methodId  )
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


public static java.lang.Object replace (  Map obj   ,java.lang.Object a0,java.lang.Object a1  , int methodId  )
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


public static java.lang.Object computeIfAbsent (  Map obj   ,java.lang.Object a0,java.util.function.Function a1  , int methodId  )
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


public static java.lang.Object computeIfPresent (  Map obj   ,java.lang.Object a0,java.util.function.BiFunction a1  , int methodId  )
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


public static java.lang.Object compute (  Map obj   ,java.lang.Object a0,java.util.function.BiFunction a1  , int methodId  )
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


public static java.lang.Object merge (  Map obj   ,java.lang.Object a0,java.lang.Object a1,java.util.function.BiFunction a2  , int methodId  )
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


public static void replaceAll (  Map obj   ,java.util.function.BiFunction a0  , int methodId  )
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


public static java.lang.Object getOrDefault (  Map obj   ,java.lang.Object a0,java.lang.Object a1  , int methodId  )
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


public static void forEach (  Map obj   ,java.util.function.BiConsumer a0  , int methodId  )
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
 