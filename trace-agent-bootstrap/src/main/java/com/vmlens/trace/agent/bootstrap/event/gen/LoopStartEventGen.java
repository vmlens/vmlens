package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class LoopStartEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     int     loopId;



public  LoopStartEventGen(
int slidingWindowId 
  ,   int     loopId
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.loopId   =  loopId;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.scheduler.getByteBuffer(slidingWindowId, 5, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  40 );
   
     buffer.putInt( loopId ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 5, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  40 );
   
     buffer.putInt( loopId ); ;





}












}