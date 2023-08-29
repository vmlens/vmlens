package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class MethodEnterShortThreadIdEventGen  implements RuntimeEvent 
{


    public int getSlidingWindowId()
    {
      return slidingWindowId;
     } 
  

   private final int slidingWindowId;
  

   public final     short     shortThreadId;
   public final     int     methodId;
   public final     int     methodCounter;



public  MethodEnterShortThreadIdEventGen(
int slidingWindowId 
  ,   short     shortThreadId
  ,   int     methodId
  ,   int     methodCounter
 )
 {

   this.slidingWindowId = slidingWindowId;

      this.shortThreadId   =  shortThreadId;
      this.methodId   =  methodId;
      this.methodCounter   =  methodCounter;
     
 
  
  
 }
 

 

public void serialize(StreamRepository streamRepository) throws Exception
{
     ByteBuffer buffer =  streamRepository.method.getByteBuffer(slidingWindowId, 11, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  32 );
   
     buffer.putShort( shortThreadId ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( methodCounter ); ;





}



public void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception
{
     ByteBuffer buffer =  streamWrapper.getByteBuffer(slidingWindowId, 11, EventFactory.MAX_ARRAY_SIZE * 1000);

 buffer.put( (byte)  32 );
   
     buffer.putShort( shortThreadId ); ;
     buffer.putInt( methodId ); ;
     buffer.putInt( methodCounter ); ;





}












}