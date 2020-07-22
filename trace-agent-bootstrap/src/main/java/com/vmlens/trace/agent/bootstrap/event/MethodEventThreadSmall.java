package com.vmlens.trace.agent.bootstrap.event;

import java.nio.ByteBuffer;

public class MethodEventThreadSmall implements RuntimeEvent {

	  private final byte    type;
      private final byte    threadId;
	  private final int     slidingWindowId;
      private final int     methodId;
	
      
      @Override
	public int getSlidingWindowId() {
		return slidingWindowId;
	}



	/**
       *  type     1
       *  threadId 1
       *  methodId 4
       */
      
      public static final int blockSize =  6 * 10000;
      
      public MethodEventThreadSmall(byte type, byte threadId, int slidingWindowId, int methodId) {
		super();
		this.type = type;
		this.threadId = threadId;
		this.slidingWindowId = slidingWindowId;
		this.methodId = methodId;
	}
      
      
      
      public void serialize(StreamRepository streamRepository) throws Exception
      {
    	  ByteBuffer buffer =       streamRepository.method.getByteBuffer( slidingWindowId , 21 ,  blockSize );

          buffer.put( type );
          buffer.put( threadId );
          buffer.putInt( methodId );
   
      }



	@Override
	public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception {
		  ByteBuffer buffer =       streamWrapper.getByteBuffer( slidingWindowId , 21 ,  blockSize );

          buffer.put( type );
          buffer.put( threadId );
          buffer.putInt( methodId );
		
	}
	
      
      

    
	
	
}
