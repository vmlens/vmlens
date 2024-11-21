package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import gnu.trove.map.hash.TObjectIntHashMap;

public class Class2Id {

	private int maxId = 1;
	TObjectIntHashMap<Class> class2Id = new TObjectIntHashMap<Class>();
	private Object LOCK = new Object();
	
	
	public int getId(Class cl)
	{
		synchronized(LOCK)
		{
			int current = class2Id.get(cl);

			if (current != 0) {
				return current;
			}


			current = maxId;

            ThreadLocalForParallelize callbackStatePerThread = ThreadLocalWhenInTestAdapterImpl.callbackStatePerThread.get();


			// Fixme
			//callbackStatePerThread.offer(new ClassNameEvent(cl.getName(), current));


			maxId++;
			class2Id.put(cl, current);

			return current;
		}
	}
	
	public int getArrayClassId(Class cl)
	{
		synchronized(LOCK)
		{
			int current = class2Id.get(cl);
			
			if( current != 0 )
			{
				return current;
			}


			current = maxId;

            ThreadLocalForParallelize callbackStatePerThread = ThreadLocalWhenInTestAdapterImpl.callbackStatePerThread.get();


			String name = createName(cl);
			// Fixme
			//callbackStatePerThread.offer(new ClassNameEvent(name, current));


			maxId++;

			class2Id.put(cl, current);

			return current;


		}
	}
	
	
	
	
	public static String createName(Class cl)
	{
		return createNamePart( cl.getComponentType() , "[]" );
	}

	
	private static String createNamePart(Class cl, String prefix)
	{
		if( cl.isArray() )
		{
			return createNamePart( cl.getComponentType() , prefix + "[]" );
		}
		
		return cl.getName() + prefix;
	}
	
	
	
	
	
}
