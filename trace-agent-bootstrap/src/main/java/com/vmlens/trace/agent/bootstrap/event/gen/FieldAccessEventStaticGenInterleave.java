package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class FieldAccessEventStaticGenInterleave  implements RuntimeEvent 
{


  
    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

     public final     long     threadId;
      public final     int     programCounter;
      public final     int     fieldId;
      public final     int     methodCounter;
      public final     int     operation;
      public final     int     methodId;
      public final     boolean     stackTraceIncomplete;
      public final     boolean     showSharedMemory;
      public final     int     loopId;
      public final     int     runId;
      public final     int     runPosition;
      public  FieldAccessEventStaticGenInterleave(
int slidingWindowId 
,   long     threadId
,   int     programCounter
,   int     fieldId
,   int     methodCounter
,   int     operation
,   int     methodId
,   boolean     stackTraceIncomplete
,   boolean     showSharedMemory
,   int     loopId
,   int     runId
,   int     runPosition
)
 {

   this.slidingWindowId = slidingWindowId;

   this.threadId   =  threadId;
   this.programCounter   =  programCounter;
   this.fieldId   =  fieldId;
   this.methodCounter   =  methodCounter;
   this.operation   =  operation;
   this.methodId   =  methodId;
   this.stackTraceIncomplete   =  stackTraceIncomplete;
   this.showSharedMemory   =  showSharedMemory;
   this.loopId   =  loopId;
   this.runId   =  runId;
   this.runPosition   =  runPosition;
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.field.getByteBuffer(slidingWindowId, 43, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  4 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( programCounter ); ;
 buffer.putInt( fieldId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
 buffer.putInt( methodId ); ;
 buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;
 buffer.put( (byte) ( showSharedMemory ? 1 : 0 ) );;
 buffer.putInt( loopId ); ;
 buffer.putInt( runId ); ;
 buffer.putInt( runPosition ); ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 43, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  4 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( programCounter ); ;
 buffer.putInt( fieldId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.putInt( operation ); ;
 buffer.putInt( methodId ); ;
 buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;
 buffer.put( (byte) ( showSharedMemory ? 1 : 0 ) );;
 buffer.putInt( loopId ); ;
 buffer.putInt( runId ); ;
 buffer.putInt( runPosition ); ;
}












}