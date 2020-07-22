package com.vmlens.trace.agent.bootstrap.util;



public class CopyOnResizeArray<T> {

private volatile CopyOnResizeArrayElement<T>[] array;
	
	private final int RESIZE_SIZE;
	private final Object RESIZE_LOCK = new Object();
	
	public CopyOnResizeArray(int resize_size) {
		super();
		
		RESIZE_SIZE = resize_size;
		CopyOnResizeArrayElement[] temp = new CopyOnResizeArrayElement[RESIZE_SIZE];
		
		for( int i = 0 ; i < temp.length ; i++)
		{
			temp[i] = new CopyOnResizeArrayElement<T>();
		}
		
		
		array = temp;
		
	} 
	
	
	public void set(int index , T in  )
	{
		
		 if( array.length <= index )
		 {
			 resize(index);
		 }
	
		 
		 CopyOnResizeArrayElement<T>[] temp = array;
		 
		 temp[index].item = in;
		 
		 
		 
		 
	}
	
	
	public T get(int index)
	{
		return array[index].item;
	}
	
	
	
	private void resize(int index)
	{
		synchronized(RESIZE_LOCK)
		{
			
			 if( array.length > index )
			 {
				 return;
			 }
		
			
			
		int newArrayLength = Math.max(index + 1 , array.length + RESIZE_SIZE ) ;
		CopyOnResizeArrayElement[] temp = new CopyOnResizeArrayElement[ newArrayLength];
  	    System.arraycopy(array, 0, temp, 0, array.length);
  	    
  	    for(int i = array.length ; i < newArrayLength ; i++)
  	    {
  	    	temp[i] = new CopyOnResizeArrayElement();
  	    }
  	    
    	array = temp;
		}
	}
	
	
	public void foreach(Consumer<T> consumer )
	{
		CopyOnResizeArrayElement<T>[] temp = array;
		
		for( int i = 0 ; i < temp.length ; i++ )
		{
			consumer.accept(  temp[i].item );
		}
		
		
	}
	
	
	
	
}
