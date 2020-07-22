package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class ParallizedMethodEnterEventGen  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     long     threadId;
      public final     int     methodId;
      public final     int     methodCounter;
      public final     int     parallizeId;
      public  ParallizedMethodEnterEventGen(
int slidingWindowId 
,   long     threadId
,   int     methodId
,   int     methodCounter
,   int     parallizeId
)
 {

   this.slidingWindowId = slidingWindowId;

   this.threadId   =  threadId;
   this.methodId   =  methodId;
   this.methodCounter   =  methodCounter;
   this.parallizeId   =  parallizeId;
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.method.getByteBuffer(slidingWindowId, 21, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  28 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( parallizeId ); ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 21, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  28 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( parallizeId ); ;
}












}