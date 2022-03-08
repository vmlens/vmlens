package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class ArrayAccessEventGen  implements RuntimeEvent 
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
   public final     int     position;
   public final     int     classId;



public  ArrayAccessEventGen(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     fieldId
  ,   int     methodCounter
  ,   int     operation
  ,   int     methodId
  ,   boolean     stackTraceIncomplete
  ,   long     objectHashCode
  ,   int     position
  ,   int     classId
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
      this.position   =  position;
      this.classId   =  classId;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.field.getByteBuffer(slidingWindowId, 46, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  5 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( operation ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;
      buffer.putLong( objectHashCode );  ;
     buffer.putInt( position ); ;
     buffer.putInt( classId ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 46, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  5 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( operation ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;
      buffer.putLong( objectHashCode );  ;
     buffer.putInt( position ); ;
     buffer.putInt( classId ); ;





}












}