package com.vmlens.trace.agent.bootstrap.typeDesc;

import java.io.DataInputStream;
import java.io.IOException;

public class ParallizeDeSerializer {

	
	public static SerializableData deSerialize(DataInputStream stream) throws IOException
	{
		int id = stream.readInt();
		
		if( id == SerializationIds.ATOMIC_CLASS )
		{
			return new AtomicClass(stream);
		}
	
        else
		{
			throw new RuntimeException( "unknown id " +  id);
		}
	}
	
	
	
	
	
	
	
}
