package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class MethodAtomicEnterEventGen  implements RuntimeEvent 
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



public  MethodAtomicEnterEventGen(
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

 buffer.put( (byte)  36 );
   
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

 buffer.put( (byte)  36 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( methodId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.put( hasCallback ); ;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}












}