package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class FieldAccessEventGenInterleave  implements RuntimeEvent 
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
   public final     long     objectHashCode;
   public final     boolean     showSharedMemory;
   public final     int     loopId;
   public final     int     runId;
   public final     int     runPosition;



public  FieldAccessEventGenInterleave(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     fieldId
  ,   int     methodCounter
  ,   int     operation
  ,   int     methodId
  ,   boolean     stackTraceIncomplete
  ,   long     objectHashCode
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
      this.objectHashCode   =  objectHashCode;
      this.showSharedMemory   =  showSharedMemory;
      this.loopId   =  loopId;
      this.runId   =  runId;
      this.runPosition   =  runPosition;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.field.getByteBuffer(slidingWindowId, 51, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  2 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( operation ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;
      buffer.putLong( objectHashCode );  ;
     buffer.put( (byte) ( showSharedMemory ? 1 : 0 ) );;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 51, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  2 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( operation ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;
      buffer.putLong( objectHashCode );  ;
     buffer.put( (byte) ( showSharedMemory ? 1 : 0 ) );;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}












}