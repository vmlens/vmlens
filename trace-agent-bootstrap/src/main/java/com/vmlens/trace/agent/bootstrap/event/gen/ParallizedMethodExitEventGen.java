package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class ParallizedMethodExitEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     long     threadId;
   public final     int     methodId;
   public final     int     methodCounter;



public  ParallizedMethodExitEventGen(
int slidingWindowId 
  ,   long     threadId
  ,   int     methodId
  ,   int     methodCounter
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.threadId   =  threadId;
      this.methodId   =  methodId;
      this.methodCounter   =  methodCounter;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.method.getByteBuffer(slidingWindowId, 17, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  29 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( methodId ); ;
     buffer.putInt( methodCounter ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 17, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  29 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( methodId ); ;
     buffer.putInt( methodCounter ); ;





}












}