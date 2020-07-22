package com.vmlens.trace.agent.bootstrap;



import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;

import gnu.trove.map.hash.TObjectIntHashMap;
import gnu.trove.set.hash.THashSet;



public class UnknownIdArray {

	
	private final Object WRITE_LOCK = new Object();
	private TObjectIntHashMap<FieldName> fieldId2Index = new TObjectIntHashMap<FieldName>();
	private THashSet<FieldName> alreadyLogged = new THashSet<FieldName>();
	private final Object ALREADY_LOGGED_LOCK =  new Object();
	
	private int maxIndex = 0;
	
	private final int RESIZE_SIZE;
	public volatile UnknownIdElement[] array;
	
	public UnknownIdArray(int resize_size) {
		super();
	    RESIZE_SIZE = resize_size;
	    
	    
	    UnknownIdElement[] temp = new UnknownIdElement[RESIZE_SIZE];
	    
	    for(int i = 0 ; i < RESIZE_SIZE ; i++)
	    {
	    	temp[i] = new UnknownIdElement();
	    }
	    
	    array = temp;
	      
	}
	
	
	
	public int getOrCreateId(FieldName fieldId)
	{
		synchronized(WRITE_LOCK)
		{
			if( fieldId2Index.contains( fieldId ) )
			{
				return fieldId2Index.get(fieldId);
			}
			
		    int newIndex = maxIndex;
		    maxIndex++;
			
			
		    if( newIndex >= array.length )
		    {
		    	// resize
		    	int newArrayLength = array.length + RESIZE_SIZE;
		    	UnknownIdElement[] temp = new UnknownIdElement[ newArrayLength];
		  	    System.arraycopy(array, 0, temp, 0, array.length);
		  	    
		  	    for(int i = array.length ; i < newArrayLength ; i++)
		  	    {
		  	    	temp[i] = new UnknownIdElement();
		  	    }
		  	    
		    	array = temp;
		    }
		    
		  
		    
		    array[newIndex].fieldName = fieldId;
		    fieldId2Index.put(fieldId,newIndex);
		    
		    return newIndex;
		    
			
		}
	}
	
	
	private void logStillUndefined(FieldName fieldName)
	{
//		synchronized(ALREADY_LOGGED_LOCK)
//		{
//			if( ! alreadyLogged.contains(fieldName) )
//			{
//				 AgentLogCallback.log(  "still undefined " + fieldName.getOwner() + " " + fieldName.getName() );
//				 alreadyLogged.add(fieldName);
//			}
//			
//			
//		}
	}
	
	
	
	
	public FieldIdAndTyp getOrCreateElement(int index)
	{

		{
		UnknownIdElement temp =  array[index];
		
		
		if( temp.fieldIdAndTyp == null)
		{
			
			FieldIdAndTyp fieldIdAndTyp = FieldIdRepository.get(  temp.fieldName );
			
			String currentClass =  temp.fieldName.getOwner();
			
			while(fieldIdAndTyp == null)
			{
				 currentClass = ClassInheritanceRepository.getParent4Child(currentClass);
				
				 if(currentClass == null)
				 {
					 logStillUndefined(  temp.fieldName );
					 fieldIdAndTyp =  new FieldIdAndTyp( 1 , FieldTyp.FINAL );  //FieldIdRepository.create(temp.fieldName.getOwner(), temp.fieldName.getName(), FieldTyp.NON_VOLATILE);
				 }
				 else
				 {
					 fieldIdAndTyp = FieldIdRepository.get(  currentClass , temp.fieldName.getName() );
				 }
				
				
			}
			
			
			
			temp.fieldIdAndTyp  =  fieldIdAndTyp;
		}
		
		return temp.fieldIdAndTyp;
		}
	}
	
	
	
}
