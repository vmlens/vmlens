package com.vmlens.trace.agent.bootstrap.callback;

import java.lang.invoke.MethodHandles.Lookup;

import com.vmlens.trace.agent.bootstrap.FieldIdAndTyp;
import com.vmlens.trace.agent.bootstrap.FieldIdRepository;

import java.lang.invoke.VarHandle;

import gnu.trove.map.hash.TObjectIntHashMap;

public class MethodHandlesLookupCallback {
	
	
	private static final TObjectIntHashMap<VarHandle> varHandle2FieldId = new TObjectIntHashMap<VarHandle>();
	private static final Object LOCK = new Object();
	

	private static void add2Map(VarHandle varHandle  ,  Class recv , String name )
	{
		String className = recv.getName().replace('.', '/');
		
		FieldIdAndTyp fieldIdAndTyp = FieldIdRepository.getForUnsafe( className ,  name );
		synchronized(LOCK) {
				varHandle2FieldId.put( varHandle ,   fieldIdAndTyp.id );
			}
	
	}
	
	static int getFieldId2VarHandle(VarHandle varHandle)
	{
		synchronized(LOCK) {
		if(varHandle2FieldId.contains(varHandle))
		{
			return varHandle2FieldId.get(varHandle);
		}
		else
		{
			return -1;
		}
		}
		
	}
	
	
	
	
	public static  VarHandle findVarHandle( Lookup lookup ,  Class recv , String name , Class type, int methodId) throws NoSuchFieldException, IllegalAccessException
	{
		VarHandle varHandle = lookup.findVarHandle(recv, name, type);
		
		add2Map( varHandle , recv , name );
		
		return varHandle;
	}
	
}
