package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class ThreadBeginEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     long     threadId;
   public final     long     startedThreadId;
   public final     int     programCounter;
   public final     int     methodCounter;



public  ThreadBeginEventGen(
int slidingWindowId 
  ,   long     threadId
  ,   long     startedThreadId
  ,   int     programCounter
  ,   int     methodCounter
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.threadId   =  threadId;
      this.startedThreadId   =  startedThreadId;
      this.programCounter   =  programCounter;
      this.methodCounter   =  methodCounter;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.syncActions.getByteBuffer(slidingWindowId, 25, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  34 );
   
      buffer.putLong( threadId );  ;
      buffer.putLong( startedThreadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( methodCounter ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 25, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  34 );
   
      buffer.putLong( threadId );  ;
      buffer.putLong( startedThreadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( methodCounter ); ;





}












}