package com.vmlens.trace.agent.bootstrap.callback.obj.gen;

import com.vmlens.trace.agent.bootstrap.callback.obj.*;
import java.util.*;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

/* templates/MapTemplateCallback.mustache */

public class SetCallbackGen extends SetCallback
{



public static boolean add (  Set obj   ,java.lang.Object a0  , int methodId  )
{


	boolean result = 
 obj.add  (  a0 );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ_WRITE  , methodId);
}


	return result;

}


public static boolean remove (  Set obj   ,java.lang.Object a0  , int methodId  )
{


	boolean result = 
 obj.remove  (  a0 );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ_WRITE  , methodId);
}


	return result;

}


public static boolean retainAll (  Set obj   ,java.util.Collection a0  , int methodId  )
{


	boolean result = 
 obj.retainAll  (  a0 );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ_WRITE  , methodId);
}


	return result;

}


public static boolean removeAll (  Set obj   ,java.util.Collection a0  , int methodId  )
{


	boolean result = 
 obj.removeAll  (  a0 );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ_WRITE  , methodId);
}


	return result;

}


public static void clear (  Set obj     , int methodId  )
{


 obj.clear  (  );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ_WRITE  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ_WRITE  , methodId);
}



}


public static int size (  Set obj     , int methodId  )
{


	int result = 
 obj.size  (  );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ  , methodId);
}


	return result;

}


public static boolean isEmpty (  Set obj     , int methodId  )
{


	boolean result = 
 obj.isEmpty  (  );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ  , methodId);
}


	return result;

}


public static boolean contains (  Set obj   ,java.lang.Object a0  , int methodId  )
{


	boolean result = 
 obj.contains  (  a0 );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ  , methodId);
}


	return result;

}


public static java.lang.Object[] toArray (  Set obj     , int methodId  )
{


	java.lang.Object[] result = 
 obj.toArray  (  );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ  , methodId);
}


	return result;

}


public static java.lang.Object[] toArray (  Set obj   ,java.lang.Object[] a0  , int methodId  )
{


	java.lang.Object[] result = 
 obj.toArray  (  a0 );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ  , methodId);
}


	return result;

}


public static boolean containsAll (  Set obj   ,java.util.Collection a0  , int methodId  )
{


	boolean result = 
 obj.containsAll  (  a0 );

if( obj instanceof  java.util.HashSet )
{
  access( obj, MemoryAccessType.IS_READ  , methodId);
}
else
{
	DelegateRepository.access(obj ,  MemoryAccessType.IS_READ  , methodId);
}


	return result;

}


public static java.util.Iterator iterator (  Set obj     , int methodId  )
{


	java.util.Iterator result = 
 obj.iterator  (  );



   createDelegate( result , obj  );




	return result;

}

}
 