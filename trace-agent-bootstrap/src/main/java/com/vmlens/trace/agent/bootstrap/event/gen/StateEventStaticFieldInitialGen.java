package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class StateEventStaticFieldInitialGen  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     long     threadIdAtEvent;
      public final     int     fieldId;
      public final     int     methodId;
      public final     int     methodCounter;
      public final     int     operation;
      public final     int     slidingWindowIdAtEvent;
      public  StateEventStaticFieldInitialGen(
int slidingWindowId 
,   long     threadIdAtEvent
,   int     fieldId
,   int     methodId
,   int     methodCounter
,   int     operation
,   int     slidingWindowIdAtEvent
)
 {

   this.slidingWindowId = slidingWindowId;

   this.threadIdAtEvent   =  threadIdAtEvent;
   this.fieldId   =  fieldId;
   this.methodId   =  methodId;
   this.methodCounter   =  methodCounter;
   this.operation   =  operation;
   this.slidingWindowIdAtEvent   =  slidingWindowIdAtEvent;
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.stateInitial.getByteBuffer(slidingWindowId, 29, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  48 );
   
  buffer.putLong( threadIdAtEvent );  ;
 buffer.putInt( fieldId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
 buffer.putInt( slidingWindowIdAtEvent ); ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 29, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  48 );
   
  buffer.putLong( threadIdAtEvent );  ;
 buffer.putInt( fieldId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
 buffer.putInt( slidingWindowIdAtEvent ); ;
}












}