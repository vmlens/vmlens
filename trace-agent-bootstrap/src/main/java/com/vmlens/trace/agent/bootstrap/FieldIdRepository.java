package com.vmlens.trace.agent.bootstrap;

import com.vmlens.trace.agent.bootstrap.util.Constants;

import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;


public class FieldIdRepository {
	
	private static THashMap<FieldName,FieldIdAndTyp> fieldName2Id = new THashMap<FieldName,FieldIdAndTyp>();
	private static int maxId = Constants.MAX_PRE_DEFINED_FIELD_ID;
	
	private static TIntObjectHashMap<FieldTyp> fieldId2Typ = new TIntObjectHashMap<FieldTyp> ();
	
	
//	static {
//		
//		
//		fieldId2Typ.put(   Constants.FIELD_ID_JAVA_UTIL_HASH_MAP ,  FieldTyp.NON_VOLATILE );
//		fieldId2Typ.put(   Constants.FIELD_ID_JAVA_UTIL_IDENTITY_HASH_MAP ,  FieldTyp.NON_VOLATILE );
//		fieldId2Typ.put(   Constants.FIELD_ID_JAVA_UTIL_LINKED_HASH_MAP ,  FieldTyp.NON_VOLATILE );
//		fieldId2Typ.put(   Constants.FIELD_ID_JAVA_UTIL_HASH_SET ,  FieldTyp.NON_VOLATILE );
//	}
//	
	
	
	public static synchronized FieldTyp getFieldTyp(int fieldId)
	{
		return fieldId2Typ.get(fieldId);
	}
	
	
	
	
	public static synchronized FieldIdAndTyp create(String owner, String name,FieldTyp fieldTyp)
	{
		FieldName fieldName = new FieldName(owner,name);
		
		if( fieldName2Id.contains( fieldName ) )
		{
			FieldIdAndTyp fieldIdAndTyp =  fieldName2Id.get(fieldName);
			
			FieldIdAndTyp corrected = new FieldIdAndTyp(fieldIdAndTyp.id ,  fieldTyp );
			
			fieldId2Typ.put( corrected.id ,  fieldTyp);
			
			fieldName2Id.put(fieldName , corrected);
			
			return corrected;
		}
		else
		{
			maxId = maxId + 1;
			FieldIdAndTyp temp = new  FieldIdAndTyp(maxId,fieldTyp);
		
			fieldName2Id.put(fieldName,temp );
			
			fieldId2Typ.put( temp.id ,  fieldTyp);
			
			return temp;
		}
	}
	
	public static synchronized FieldIdAndTyp get(FieldName fieldName)
	{
			return fieldName2Id.get(fieldName);

	}
	
	
	public static synchronized FieldIdAndTyp get(String owner, String name)
	{
		FieldName fieldName = new FieldName(owner,name);
		

			return fieldName2Id.get(fieldName);

	}

	
	public static synchronized FieldIdAndTyp getForUnsafe(String owner, String name)
	{
		FieldName fieldName = new FieldName(owner,name);
		

	     FieldIdAndTyp result = fieldName2Id.get(fieldName);
	     
	     if( result == null )
	     {
	    	 maxId = maxId + 1;
	    	 result = new  FieldIdAndTyp(maxId,FieldTyp.VOLATILE);
			
	    	 fieldId2Typ.put( result.id ,  FieldTyp.VOLATILE);
	    	 
				fieldName2Id.put(fieldName,result );
	     }
	     
        return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
