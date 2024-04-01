package com.vmlens.trace.agent.bootstrap;

import gnu.trove.map.hash.TObjectIntHashMap;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 
 * zur verwendung von Unsafe calls
 * 
 * @author Thomas
 *
 */


public class Offset2FieldId {

	
	private static TObjectIntHashMap<OffsetAndClassName>
	offsetAndClassName2FieldId = new  TObjectIntHashMap<OffsetAndClassName>();
	
	
	public static synchronized void addOffset(OffsetAndClassName offset , String fieldName)
	{
		int id  = FieldIdRepository.getForUnsafe(offset.getClassName(), fieldName).id;
		
		offsetAndClassName2FieldId.put(offset, id);
	
	
	}
	
	
	public static void initialize()
	{
		 Unsafe unsafe = Unsafe.getUnsafe();
		 addAllFieldsForobject(Class.class ,  unsafe);
	}
	
	
	private static void addAllFieldsForobject(Object obj, sun.misc.Unsafe unsafe)
	{
		for( Field field :  obj.getClass().getDeclaredFields() )
		{
			 if (! java.lang.reflect.Modifier.isStatic(field.getModifiers()))
			 {
				 long newOffset = unsafe.objectFieldOffset(field);
					OffsetAndClassName offsetAndClassName = new OffsetAndClassName(newOffset , field.getDeclaringClass().getName());
					Offset2FieldId.addOffset(offsetAndClassName, field.getName());
			 }
			
			
		}
	}
	
	
	public static synchronized int getFieldId(Object obj, sun.misc.Unsafe unsafe , OffsetAndClassName offset)
	{
		
		if(!  offsetAndClassName2FieldId.contains(offset) )
		{
			String currentClassName = ClassInheritanceRepository.getParent4Child(offset.getClassName());
			
			while(  currentClassName != null )
			{
				
				OffsetAndClassName newOffset = new OffsetAndClassName(offset.getOffset() , currentClassName);
				
				if( offsetAndClassName2FieldId.contains(newOffset) )
				{
					int result = offsetAndClassName2FieldId.get(newOffset);			
					return result;
				}
				
				
				
				 currentClassName = ClassInheritanceRepository.getParent4Child(currentClassName);
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		if(!  offsetAndClassName2FieldId.contains(offset) )
		{


            addAllFieldsForobject( obj,  unsafe);


        }
		
		
		int result = offsetAndClassName2FieldId.get(offset);
		
		
		return result;
	}
	
	
	
}
