package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class MethodExitEventGen  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     long     threadId;
      public final     int     methodId;
      public final     int     methodCounter;
      public  MethodExitEventGen(
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

 buffer.put( (byte)  27 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 17, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  27 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
}












}