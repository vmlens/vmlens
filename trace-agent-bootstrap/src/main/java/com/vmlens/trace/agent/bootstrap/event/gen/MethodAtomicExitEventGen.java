package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class MethodAtomicExitEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     long     threadId;
   public final     int     methodId;
   public final     int     methodCounter;
   public final     byte     hasCallback;
   public final     int     loopId;
   public final     int     runId;
   public final     int     runPosition;



public  MethodAtomicExitEventGen(
int slidingWindowId 
  ,   long     threadId
  ,   int     methodId
  ,   int     methodCounter
  ,   byte     hasCallback
  ,   int     loopId
  ,   int     runId
  ,   int     runPosition
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.threadId   =  threadId;
      this.methodId   =  methodId;
      this.methodCounter   =  methodCounter;
      this.hasCallback   =  hasCallback;
      this.loopId   =  loopId;
      this.runId   =  runId;
      this.runPosition   =  runPosition;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.scheduler.getByteBuffer(slidingWindowId, 30, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  37 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( methodId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.put( hasCallback ); ;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 30, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  37 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( methodId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.put( hasCallback ); ;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}












}