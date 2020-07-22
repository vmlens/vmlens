package com.anarsoft.trace.agent.runtime.waitPoints;


import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import java.io.*;

import com.vmlens.trace.agent.bootstrap.typeDesc.*;

public class LoadAtomicClassesFromClasspath implements SerializableDataVisitor {

	
	private final THashMap<String,AtomicMethodWithCallback[]> atomicClasses2AtomicMethodWithCallbackArray  =  new THashMap<String,AtomicMethodWithCallback[]>();

	public static void  load()
	{
		
		LoadAtomicClassesFromClasspath parallizeMapBuilder = new LoadAtomicClassesFromClasspath();
		
		
		try{
			DataInputStream stream = new DataInputStream( parallizeMapBuilder.getClass().getClassLoader().getResourceAsStream("atomicClasses.vmlens"));
			try{
				while( true )
				{
					SerializableData parallizeMetaData = ParallizeDeSerializer.deSerialize(stream);
					parallizeMetaData.accept(parallizeMapBuilder);
				}
			}
			catch (EOFException e) {
			
			}
			}
			catch(IOException e)
			{
				e.printStackTrace();

			}
		FilterList.atomicClasses2AtomicMethodWithCallbackArray = parallizeMapBuilder.atomicClasses2AtomicMethodWithCallbackArray;
		
		
		
		//return parallizeMapBuilder.atomicClasses2AtomicMethodWithCallbackArray;
	}

	@Override
	public void visit(AtomicClass atomicClass) {
		atomicClasses2AtomicMethodWithCallbackArray.put(  atomicClass.getName().replace('.', '/')  , atomicClass.getAtomicMethodWithCallbackArray() );
		
	}

	
	
}
