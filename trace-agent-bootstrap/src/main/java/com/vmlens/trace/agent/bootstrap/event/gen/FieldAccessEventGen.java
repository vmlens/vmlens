package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class FieldAccessEventGen  implements RuntimeEvent 
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



public  FieldAccessEventGen(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     fieldId
  ,   int     methodCounter
  ,   int     operation
  ,   int     methodId
  ,   boolean     stackTraceIncomplete
  ,   long     objectHashCode
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
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.field.getByteBuffer(slidingWindowId, 38, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  1 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( operation ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;
      buffer.putLong( objectHashCode );  ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 38, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  1 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( operation ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;
      buffer.putLong( objectHashCode );  ;





}












}