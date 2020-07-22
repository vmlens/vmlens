package com.vmlens.trace.agent.bootstrap.typeDesc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class AtomicClass implements SerializableData {

	
	private final String name;
	private final AtomicMethodWithCallback[] atomicMethodWithCallbackArray;
	

	public AtomicClass(String name, AtomicMethodWithCallback[] atomicMethodWithCallbackArray) {
		super();
		this.name = name;
		this.atomicMethodWithCallbackArray = atomicMethodWithCallbackArray;
	}

	public String getName() {
		return name;
	}
	
	
	public AtomicClass(DataInputStream stream) throws IOException {
		this.name = stream.readUTF();
		
		int size = stream.readInt();
		
		atomicMethodWithCallbackArray = new AtomicMethodWithCallback[size];
		
		for( int i = 0 ; i < size ; i++)
		{
			atomicMethodWithCallbackArray[i] = new AtomicMethodWithCallback(stream);
 		}
		
		
	
	}
	
	@Override
	public void serialize(DataOutputStream stream) throws IOException {
		stream.writeInt( SerializationIds.ATOMIC_CLASS );
		
		stream.writeUTF(name);
		
		
		stream.writeInt( atomicMethodWithCallbackArray.length  );
		
		for( int i = 0 ; i < atomicMethodWithCallbackArray.length  ; i++)
		{
			atomicMethodWithCallbackArray[i].serialize(stream);
		}
		
		
		
		
		
	}
	
	@Override
	public void accept(SerializableDataVisitor parallizeMetaDataVisitor) {
		parallizeMetaDataVisitor.visit(this);
		
	}

	
	
	
	
	
	@Override
	public String toString() {
		return "AtomicClass [name=" + name + ", atomicMethodWithCallbackArray="
				+ Arrays.toString(atomicMethodWithCallbackArray) + "]";
	}

	public AtomicMethodWithCallback[] getAtomicMethodWithCallbackArray() {
		return atomicMethodWithCallbackArray;
	}
	
	
	
	
	
}
