package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class MethodExitShortThreadIdEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     short     shortThreadId;
   public final     int     methodId;
   public final     int     methodCounter;



public  MethodExitShortThreadIdEventGen(
int slidingWindowId 
  ,   short     shortThreadId
  ,   int     methodId
  ,   int     methodCounter
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.shortThreadId   =  shortThreadId;
      this.methodId   =  methodId;
      this.methodCounter   =  methodCounter;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.method.getByteBuffer(slidingWindowId, 11, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  33 );
   
     buffer.putShort( shortThreadId ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( methodCounter ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 11, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  33 );
   
     buffer.putShort( shortThreadId ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( methodCounter ); ;





}












}