package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;

public class VolatileAccessEventGenInterleave  implements RuntimeEvent 
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
   public final     int     loopId;
   public final     int     runId;
   public final     int     runPosition;



public  VolatileAccessEventGenInterleave(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     order
  ,   int     fieldId
  ,   int     methodCounter
  ,   int     methodId
  ,   int     operation
  ,   long     objectHashCode
  ,   int     loopId
  ,   int     runId
  ,   int     runPosition
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
      this.loopId   =  loopId;
      this.runId   =  runId;
      this.runPosition   =  runPosition;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.syncActions.getByteBuffer(slidingWindowId, 53, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  10 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( operation ); ;
      buffer.putLong( objectHashCode );  ;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 53, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  10 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( operation ); ;
      buffer.putLong( objectHashCode );  ;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}












}