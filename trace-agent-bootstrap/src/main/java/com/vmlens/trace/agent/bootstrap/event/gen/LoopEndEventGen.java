package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class LoopEndEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     int     loopId;
   public final     int     status;



public  LoopEndEventGen(
int slidingWindowId 
  ,   int     loopId
  ,   int     status
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.loopId   =  loopId;
      this.status   =  status;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.scheduler.getByteBuffer(slidingWindowId, 9, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  41 );
   
     buffer.putInt( loopId ); ;
     buffer.putInt( status ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 9, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  41 );
   
     buffer.putInt( loopId ); ;
     buffer.putInt( status ); ;





}












}