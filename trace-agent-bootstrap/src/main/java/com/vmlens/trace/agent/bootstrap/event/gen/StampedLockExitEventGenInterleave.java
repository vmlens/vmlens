package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;

public class StampedLockExitEventGenInterleave  implements RuntimeEvent 
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
   public final     boolean     isShared;
   public final     int     lockTyp;
   public final     int     stampedLockMethodId;
   public final     int     loopId;
   public final     int     runId;
   public final     int     runPosition;



public  StampedLockExitEventGenInterleave(
int slidingWindowId 
  ,   long     threadId
  ,   int     programCounter
  ,   int     order
  ,   int     monitorId
  ,   int     methodCounter
  ,   boolean     isShared
  ,   int     lockTyp
  ,   int     stampedLockMethodId
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
      this.isShared   =  isShared;
      this.lockTyp   =  lockTyp;
      this.stampedLockMethodId   =  stampedLockMethodId;
      this.loopId   =  loopId;
      this.runId   =  runId;
      this.runPosition   =  runPosition;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.syncActions.getByteBuffer(slidingWindowId, 46, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  21 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
     buffer.putInt( monitorId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.put( (byte) ( isShared ? 1 : 0 ) );;
     buffer.putInt( lockTyp ); ;
     buffer.putInt( stampedLockMethodId ); ;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 46, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  21 );
   
      buffer.putLong( threadId );  ;
     buffer.putInt( programCounter ); ;
     buffer.putInt( order ); ;
     buffer.putInt( monitorId ); ;
     buffer.putInt( methodCounter ); ;
     buffer.put( (byte) ( isShared ? 1 : 0 ) );;
     buffer.putInt( lockTyp ); ;
     buffer.putInt( stampedLockMethodId ); ;
     buffer.putInt( loopId ); ;
     buffer.putInt( runId ); ;
     buffer.putInt( runPosition ); ;





}












}