package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;

public class VolatileAccessEventStaticGenInterleave  implements RuntimeEvent 
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
   public final     boolean     isWrite;
   public final     int     loopId;
   public final     int     runId;
   public final     int     runPosition;



public  VolatileAccessEventStaticGenInterleave(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     order
  ,   int     fieldId
  ,   int     methodCounter
  ,   int     methodId
  ,   boolean     isWrite
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
      this.isWrite   =  isWrite;
      this.loopId   =  loopId;
      this.runId   =  runId;
      this.runPosition   =  runPosition;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.syncActions.getByteBuffer(slidingWindowId, 42, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  8 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( isWrite ? 1 : 0 ) );;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 42, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  8 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( isWrite ? 1 : 0 ) );;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}












}