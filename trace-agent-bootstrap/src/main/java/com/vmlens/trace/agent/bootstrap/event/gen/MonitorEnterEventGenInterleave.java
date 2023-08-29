package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class MonitorEnterEventGenInterleave  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     long     threadId;
   public final     int     programCounter;
   public final     int     order;
   public final     int     monitorId;
   public final     int     methodCounter;
   public final     int     methodId;
   public final     int     position;
   public final     int     loopId;
   public final     int     runId;
   public final     int     runPosition;



public  MonitorEnterEventGenInterleave(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     order
  ,   int     monitorId
  ,   int     methodCounter
  ,   int     methodId
  ,   int     position
  ,   int     loopId
  ,   int     runId
  ,   int     runPosition
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.threadId   =  threadId;
      this.programCounter   =  programCounter;
      this.order   =  order;
      this.monitorId   =  monitorId;
      this.methodCounter   =  methodCounter;
      this.methodId   =  methodId;
      this.position   =  position;
      this.loopId   =  loopId;
      this.runId   =  runId;
      this.runPosition   =  runPosition;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.monitor.getByteBuffer(slidingWindowId, 45, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  24 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
     buffer.putInt( monitorId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( position ); ;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 45, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  24 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
     buffer.putInt( monitorId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( position ); ;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}












}