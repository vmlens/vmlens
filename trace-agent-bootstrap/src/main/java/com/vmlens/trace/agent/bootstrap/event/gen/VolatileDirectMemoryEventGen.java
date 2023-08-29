package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class VolatileDirectMemoryEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     long     threadId;
   public final     int     programCounter;
   public final     int     methodCounter;
   public final     long     objectHashCode;
   public final     int     operation;
   public final     int     order;



public  VolatileDirectMemoryEventGen(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     methodCounter
  ,   long     objectHashCode
  ,   int     operation
  ,   int     order
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.threadId   =  threadId;
      this.programCounter   =  programCounter;
      this.methodCounter   =  methodCounter;
      this.objectHashCode   =  objectHashCode;
      this.operation   =  operation;
      this.order   =  order;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.directMemory.getByteBuffer(slidingWindowId, 33, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  13 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( methodCounter ); ;
      buffer.putLong( objectHashCode );  ;
     buffer.putInt( operation ); ;
     buffer.putInt( order ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 33, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  13 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( methodCounter ); ;
      buffer.putLong( objectHashCode );  ;
     buffer.putInt( operation ); ;
     buffer.putInt( order ); ;





}












}