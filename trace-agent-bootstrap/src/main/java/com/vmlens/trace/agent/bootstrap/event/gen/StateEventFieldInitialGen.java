package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class StateEventFieldInitialGen  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     long     threadIdAtEvent;
      public final     int     classId;
      public final     int     methodId;
      public final     int     methodCounter;
      public final     int     operation;
      public final     long     objectHashCode;
      public final     int     slidingWindowIdAtEvent;
      public  StateEventFieldInitialGen(
int slidingWindowId 
,   long     threadIdAtEvent
,   int     classId
,   int     methodId
,   int     methodCounter
,   int     operation
,   long     objectHashCode
,   int     slidingWindowIdAtEvent
)
 {

   this.slidingWindowId = slidingWindowId;

   this.threadIdAtEvent   =  threadIdAtEvent;
   this.classId   =  classId;
   this.methodId   =  methodId;
   this.methodCounter   =  methodCounter;
   this.operation   =  operation;
   this.objectHashCode   =  objectHashCode;
   this.slidingWindowIdAtEvent   =  slidingWindowIdAtEvent;
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.stateInitial.getByteBuffer(slidingWindowId, 37, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  46 );
   
  buffer.putLong( threadIdAtEvent );  ;
 buffer.putInt( classId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
  buffer.putLong( objectHashCode );  ;
 buffer.putInt( slidingWindowIdAtEvent ); ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 37, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  46 );
   
  buffer.putLong( threadIdAtEvent );  ;
 buffer.putInt( classId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
  buffer.putLong( objectHashCode );  ;
 buffer.putInt( slidingWindowIdAtEvent ); ;
}












}