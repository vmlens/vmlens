package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class VolatileArrayAccessEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     long     threadId;
   public final     int     programCounter;
   public final     int     order;
   public final     long     index;
   public final     int     methodCounter;
   public final     int     methodId;
   public final     int     operation;
   public final     long     objectHashCode;



public  VolatileArrayAccessEventGen(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     order
  ,   long     index
  ,   int     methodCounter
  ,   int     methodId
  ,   int     operation
  ,   long     objectHashCode
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.threadId   =  threadId;
      this.programCounter   =  programCounter;
      this.order   =  order;
      this.index   =  index;
      this.methodCounter   =  methodCounter;
      this.methodId   =  methodId;
      this.operation   =  operation;
      this.objectHashCode   =  objectHashCode;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.syncActions.getByteBuffer(slidingWindowId, 45, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  11 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
      buffer.putLong( index );  ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( operation ); ;
      buffer.putLong( objectHashCode );  ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 45, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  11 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
      buffer.putLong( index );  ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( operation ); ;
      buffer.putLong( objectHashCode );  ;





}












}