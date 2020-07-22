package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class MethodEnterSmallThreadIdEventGen  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     byte     smallThreadId;
      public final     int     methodId;
      public final     int     methodCounter;
      public  MethodEnterSmallThreadIdEventGen(
int slidingWindowId 
,   byte     smallThreadId
,   int     methodId
,   int     methodCounter
)
 {

   this.slidingWindowId = slidingWindowId;

   this.smallThreadId   =  smallThreadId;
   this.methodId   =  methodId;
   this.methodCounter   =  methodCounter;
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.method.getByteBuffer(slidingWindowId, 10, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  30 );
   
 buffer.put( smallThreadId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 10, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  30 );
   
 buffer.put( smallThreadId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
}












}