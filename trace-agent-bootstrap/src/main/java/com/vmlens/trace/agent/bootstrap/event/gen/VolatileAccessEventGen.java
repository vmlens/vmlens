package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class VolatileAccessEventGen  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     long     threadId;
      public final     int     programCounter;
      public final     int     order;
      public final     int     fieldId;
      public final     int     methodCounter;
      public final     int     methodId;
      public final     int     operation;
      public final     long     objectHashCode;
      public  VolatileAccessEventGen(
int slidingWindowId 
,   long     threadId
,   int     programCounter
,   int     order
,   int     fieldId
,   int     methodCounter
,   int     methodId
,   int     operation
,   long     objectHashCode
)
 {

   this.slidingWindowId = slidingWindowId;

   this.threadId   =  threadId;
   this.programCounter   =  programCounter;
   this.order   =  order;
   this.fieldId   =  fieldId;
   this.methodCounter   =  methodCounter;
   this.methodId   =  methodId;
   this.operation   =  operation;
   this.objectHashCode   =  objectHashCode;
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.syncActions.getByteBuffer(slidingWindowId, 41, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  9 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( programCounter ); ;
 buffer.putInt( order ); ;
 buffer.putInt( fieldId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( operation ); ;
  buffer.putLong( objectHashCode );  ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 41, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  9 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( programCounter ); ;
 buffer.putInt( order ); ;
 buffer.putInt( fieldId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( operation ); ;
  buffer.putLong( objectHashCode );  ;
}












}