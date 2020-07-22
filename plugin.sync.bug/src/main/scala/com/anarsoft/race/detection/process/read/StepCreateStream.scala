package com.anarsoft.race.detection.process.read



import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.process.workflow.SingleStep
import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.process.workflow.ToBeClosed
import java.io._;
import java.util.concurrent.ExecutorService

class StepCreateStream[EVENT,C](  val set : (  StreamAndStreamStatistic[EVENT]  ,   C   ) => Unit  ,  
    val readStrategy :  ReadStrategy[EVENT]  , val fileNamePrefix : String,val  statisticsPrefix : String  , val eventDir : String , 
    val executorService : ExecutorService , 
    val listForFinally :  HashMap[Object,ToBeClosed]  ,stopAtSlidingWindowId : Int , goBackWard :Int ) extends SingleStep[C] {
  
   def execute(  in : C )
   {
   /*
    * ( statisticFile: File,   dataFile : File ,eventCount : Int ,readStrategy :  ReadStrategy[EVENT]  ,  listForFinally : HashMap[Object,ToBeClosed])
    */
     
     
     val dir = new File(eventDir);
     
     var dataFile : Option[File] = None;
     var statisticFile : Option[File] = None;
     
     for( f <- dir.listFiles() )
     {
       
     
           if(  f.getName.startsWith(fileNamePrefix) )
       {
         dataFile = Some(f);
       }
       
       
        
       if(  f.getName.startsWith(statisticsPrefix) )
       {
         statisticFile = Some(f);
       }
       
     }
     
     
     if(! statisticFile.isEmpty)
     {
        dataFile match
    {
      
      case Some(x) =>
        {
          set (StreamAndStreamStatisticFull[EVENT]  (     dataFile.get ,  statisticFile.get ,       readStrategy    , executorService , listForFinally , stopAtSlidingWindowId , goBackWard) , in); 
        }
      
      case None =>
        {
            set ( new StreamAndStreamStatisticEmpty[EVENT] , in  );  
        }
      
      
    }
     }
        else
        {
            set ( new StreamAndStreamStatisticEmpty[EVENT] , in  );
        }
        
        
        
        
   
     
     
  
     
     
   }
   
   
   
  def desc = "";
  
  
}