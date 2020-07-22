package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class StateEventFieldGen  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     long     threadId;
      public final     int     classId;
      public final     int     methodId;
      public final     int     methodCounter;
      public final     int     operation;
      public final     long     objectHashCode;
      public  StateEventFieldGen(
int slidingWindowId 
,   long     threadId
,   int     classId
,   int     methodId
,   int     methodCounter
,   int     operation
,   long     objectHashCode
)
 {

   this.slidingWindowId = slidingWindowId;

   this.threadId   =  threadId;
   this.classId   =  classId;
   this.methodId   =  methodId;
   this.methodCounter   =  methodCounter;
   this.operation   =  operation;
   this.objectHashCode   =  objectHashCode;
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.state.getByteBuffer(slidingWindowId, 33, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  45 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( classId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
  buffer.putLong( objectHashCode );  ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 33, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  45 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( classId ); ;
 buffer.putInt( methodId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
  buffer.putLong( objectHashCode );  ;
}












}