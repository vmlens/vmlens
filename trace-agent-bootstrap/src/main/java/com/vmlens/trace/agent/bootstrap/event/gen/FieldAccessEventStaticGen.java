package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class FieldAccessEventStaticGen  implements RuntimeEvent 
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



public  FieldAccessEventStaticGen(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     fieldId
  ,   int     methodCounter
  ,   int     operation
  ,   int     methodId
  ,   boolean     stackTraceIncomplete
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
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.field.getByteBuffer(slidingWindowId, 30, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  3 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( operation ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 30, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  3 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( fieldId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( operation ); ;
     buffer.putInt( methodId ); ;
     buffer.put( (byte) ( stackTraceIncomplete ? 1 : 0 ) );;





}












}