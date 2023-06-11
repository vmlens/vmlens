package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class ThreadStoppedEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     long     threadId;
   public final     long     stoppedThreadId;
   public final     int     programCounter;
   public final     int     methodCounter;



public  ThreadStoppedEventGen(
int slidingWindowId 
  ,   long     threadId
  ,   long     stoppedThreadId
  ,   int     programCounter
  ,   int     methodCounter
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.threadId   =  threadId;
      this.stoppedThreadId   =  stoppedThreadId;
      this.programCounter   =  programCounter;
      this.methodCounter   =  methodCounter;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.syncActions.getByteBuffer(slidingWindowId, 25, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  35 );
   
      buffer.putLong( threadId );  ;
      buffer.putLong( stoppedThreadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( methodCounter ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 25, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  35 );
   
      buffer.putLong( threadId );  ;
      buffer.putLong( stoppedThreadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( methodCounter ); ;





}












}