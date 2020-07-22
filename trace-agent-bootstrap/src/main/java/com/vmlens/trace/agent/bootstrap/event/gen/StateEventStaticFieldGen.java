package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class StateEventStaticFieldGen  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     long     threadId;
      public final     int     fieldId;
      public final     int     methodId;
      public final     int     methodCounter;
      public final     int     operation;
      public  StateEventStaticFieldGen(
int slidingWindowId 
,   long     threadId
,   int     fieldId
,   int     methodId
,   int     methodCounter
,   int     operation
)
 {

   this.slidingWindowId = slidingWindowId;

   this.threadId   =  threadId;
   this.fieldId   =  fieldId;
   this.methodId   =  methodId;
   this.methodCounter   =  methodCounter;
   this.operation   =  operation;
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.state.getByteBuffer(slidingWindowId, 25, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  47 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( fieldId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 25, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  47 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( fieldId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
}












}