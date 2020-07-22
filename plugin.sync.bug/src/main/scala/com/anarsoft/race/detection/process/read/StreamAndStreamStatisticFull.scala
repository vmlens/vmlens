package com.anarsoft.race.detection.process.read

import java.io._
import scala.collection.mutable._
import java.nio._
import java.nio.channels.FileChannel.MapMode;
import com.anarsoft.race.detection.process.workflow._;
import java.util.concurrent._


class StreamAndStreamStatisticFull[EVENT](val stream: RandomAccessFile,
    val slidingWindowId2PositionList: HashMap[Int, ArrayBuffer[FilePosition]], 
    val maxSlidingWindow: Int, 
    val readStrategy :  ReadStrategy[EVENT] ,
    val readAhead : ReadAhead ,  
    listForFinally : HashMap[Object,ToBeClosed])  extends    ToBeClosed(listForFinally)   with StreamAndStreamStatistic[EVENT]
{
  
  
 
   

    var currentSlidingWindowId = 1; 
    
    
    
     def closeInternal()
  {
    stream.close();
  }
    
    
    
     
   def execute( readCallback : ReadCallback[EVENT] )
   {
     
       readCallback.readSlidingWindowId( currentSlidingWindowId  );
     
     read(currentSlidingWindowId,array=>{
       
       
//           val arraySize = readStrategy.eventArraySize();
//           var position = 0;
           
             val byteBuffer =  ByteBuffer.wrap(array);
           
          // try
             {
           while(  byteBuffer.position() < array.length   )
           {
             
            
             
             
              val event = readStrategy.deSerializeJavaEvent(byteBuffer);
              
              
          //    println(event);
              
              readCallback.onEvent(event);
              
           //   position = position + arraySize;
           }
           }
//           catch
//           {
//             case throwable =>
//               {
//                 println(byteBuffer.position() );
//                     println(array.length );
//                 
//                 throwable.printStackTrace();
//               }
//           }
       
       
     });
     
     
     currentSlidingWindowId = currentSlidingWindowId + 1;
   }
    
    
    
  def read( slidingWindowId : Int ,  f :   Array[Byte] => Unit )
    {
    
    
   
    
      slidingWindowId2PositionList.get(slidingWindowId) match {

        case None =>
          {
      
          }

        case Some(list) =>
          {

               val nextToRead = getNextToRead( slidingWindowId );
               val result = readAhead.read(stream, list, slidingWindowId, nextToRead);

               for( array <- result )
               {
                 f(array);
               }
               
          }

      }

     

    }
  
  
  def getNextToRead( slidingWindowId : Int) =
  {
    var nextId = slidingWindowId + 1;
    var result : Option[ReadTask] = None;
    
    
    while(  nextId <= maxSlidingWindow && result.isEmpty)
    {
       slidingWindowId2PositionList.get(nextId) match {

        case None =>
          {
      
          }

        case Some(list) =>
          {

              result = Some( new ReadTask(stream ,nextId , list) );

          }
       }
      
      
      nextId = nextId + 1;
    }
    
    
    result;
    
    
  }
  
  
  
  
  
  
  def stillDataAvailable( slidingWindowId : Int) = slidingWindowId < maxSlidingWindow;
  

}

object StreamAndStreamStatisticFull {

   
  
  def apply[EVENT](streamFile: File, statisticFile: File,  readStrategy :  ReadStrategy[EVENT]  , executorService : ExecutorService , listForFinally : HashMap[Object,ToBeClosed],
      stopAtSlidingWindowId : Int , goBackWard : Int) =
    {
      val slidingWindowId2PositionList = new HashMap[Int, ArrayBuffer[FilePosition]]();
      var maxSlidingWindow = 0;
      var prevoiusEnd  = 0L;

      //  var prevoiusPosition : Option[FilePosition] = None; 

      val stream = new DataInputStream(new BufferedInputStream(new FileInputStream(statisticFile)));
      try {

        while (true) {
          val slidingWindow = stream.readInt();
          
           val position = stream.readLong();

          val current = new FilePosition(prevoiusEnd , position);
          
          
          slidingWindowId2PositionList.getOrElseUpdate(slidingWindow , new ArrayBuffer[FilePosition]).append(current);
          
          
          
          
          
          
          maxSlidingWindow = Math.max(maxSlidingWindow, slidingWindow);
          prevoiusEnd = position;

        }

      } catch {
        case e: java.io.EOFException =>
          {

          }
      }
      finally
      {
        stream.close();
      }

      val file = new RandomAccessFile(streamFile , "r");

       // goBackWard
    
              maxSlidingWindow = Math.min(maxSlidingWindow, stopAtSlidingWindowId);
          
        
        
      
      
      

      new StreamAndStreamStatisticFull(
        file, slidingWindowId2PositionList, maxSlidingWindow, readStrategy , new ReadAhead(executorService) ,  listForFinally );

    }
}
