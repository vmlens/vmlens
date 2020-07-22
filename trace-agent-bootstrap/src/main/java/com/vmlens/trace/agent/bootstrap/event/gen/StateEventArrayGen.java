package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class StateEventArrayGen  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     long     threadId;
      public final     int     methodId;
      public final     int     position;
      public final     int     methodCounter;
      public final     int     operation;
      public final     long     objectHashCode;
      public final     int     classId;
      public  StateEventArrayGen(
int slidingWindowId 
,   long     threadId
,   int     methodId
,   int     position
,   int     methodCounter
,   int     operation
,   long     objectHashCode
,   int     classId
)
 {

   this.slidingWindowId = slidingWindowId;

   this.threadId   =  threadId;
   this.methodId   =  methodId;
   this.position   =  position;
   this.methodCounter   =  methodCounter;
   this.operation   =  operation;
   this.objectHashCode   =  objectHashCode;
   this.classId   =  classId;
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.state.getByteBuffer(slidingWindowId, 37, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  49 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( methodId ); ;
 buffer.putInt( position ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
  buffer.putLong( objectHashCode );  ;
 buffer.putInt( classId ); ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 37, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  49 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( methodId ); ;
 buffer.putInt( position ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
  buffer.putLong( objectHashCode );  ;
 buffer.putInt( classId ); ;
}












}