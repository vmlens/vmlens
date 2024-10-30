package com.anarsoft.trace.agent.runtime.repositorydeprecated;


import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.typeDesc.*;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

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
	}

	@Override
	public void visit(AtomicClass atomicClass) {
		atomicClasses2AtomicMethodWithCallbackArray.put(  atomicClass.getName().replace('.', '/')  , atomicClass.getAtomicMethodWithCallbackArray() );
		
	}

	
	
}
