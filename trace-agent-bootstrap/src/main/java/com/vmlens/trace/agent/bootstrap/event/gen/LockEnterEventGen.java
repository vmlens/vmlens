package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class LockEnterEventGen  implements RuntimeEvent 
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
      public  LockEnterEventGen(
int slidingWindowId 
,   long     threadId
,   int     programCounter
,   int     order
,   int     monitorId
,   int     methodCounter
,   boolean     isShared
,   int     lockTyp
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
   }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.syncActions.getByteBuffer(slidingWindowId, 30, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  14 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( programCounter ); ;
 buffer.putInt( order ); ;
 buffer.putInt( monitorId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.put( (byte) ( isShared ? 1 : 0 ) );;
 buffer.putInt( lockTyp ); ;
}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 30, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  14 );
   
  buffer.putLong( threadId );  ;
 buffer.putInt( programCounter ); ;
 buffer.putInt( order ); ;
 buffer.putInt( monitorId ); ;
 buffer.putInt( methodCounter ); ;
 buffer.put( (byte) ( isShared ? 1 : 0 ) );;
 buffer.putInt( lockTyp ); ;
}












}