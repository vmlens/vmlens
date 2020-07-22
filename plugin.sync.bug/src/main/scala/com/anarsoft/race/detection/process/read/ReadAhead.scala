package com.anarsoft.race.detection.process.read

import java.io._
import scala.collection.mutable._
import java.util.concurrent._


class ReadAhead(val executorService : ExecutorService) {
  
  var nextData : Option[Future[ReadAheadResult]] = None;
  
  
  
  def read(stream :  RandomAccessFile,  currentfilePositions : ArrayBuffer[FilePosition], currentSlidingWingowId : Int,
       nextTask : Option[ReadTask] ) =
  {
    
    val result = 
    nextData match
    {
      case None =>
        {
          ReadArray.read(stream, currentfilePositions)
        }
      
      case Some(data) =>
        {
          data.get.data;
        }
      
    }
    
    
    nextTask match
    {
      
      case None =>
        {
          nextData = None;
        }
      
      case Some(task) =>
        {
           nextData = Some(executorService.submit(task));
        }
      
    }
    
    
   
   
    
    
    result;
    
  }
  
  
  
}