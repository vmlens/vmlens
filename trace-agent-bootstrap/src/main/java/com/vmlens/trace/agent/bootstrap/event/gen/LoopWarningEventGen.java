package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class LoopWarningEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     int     loopId;
   public final     int     runId;



public  LoopWarningEventGen(
int slidingWindowId 
  ,   int     loopId
  ,   int     runId
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.loopId   =  loopId;
      this.runId   =  runId;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.scheduler.getByteBuffer(slidingWindowId, 9, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  44 );
   
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 9, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  44 );
   
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;





}












}