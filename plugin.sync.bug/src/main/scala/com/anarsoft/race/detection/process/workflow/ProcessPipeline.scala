package com.anarsoft.race.detection.process.workflow


import scala.collection.mutable.ArrayBuffer
import collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import java.lang.management.ManagementFactory;
import collection.JavaConverters._
import java.lang.management.MemoryType
import com.typesafe.scalalogging.Logger
import org.apache.commons.lang3.time.DurationFormatUtils

class ProcessPipeline[B] {
  
    val logger = Logger("com.vmlens.Performance")
   val stepList = new ArrayBuffer[Step[B]]();
   
   
   val stepName2CompleteDuration = new HashMap[String,Long]
   val className2Count =  new HashMap[String,Long]
  
  
   def step[A >: B]( step :  Step[A] ) 
  {
     stepList.append(  step );
  }
  
  
    /**
   * rückwärts durchlaufen
   * x = length -1
   * 
   * delete after 
   * 
   * 
   */
  
  
  def createDeleteMap() =
  {
    var currentStep = stepList.length - 1;
    
    val valueNeededTill = new HashMap[String,Int]();
    
    for( s <- stepList.reverse )
    {
      
      for( m <-   s.getMethodSet(true) )
      {
        valueNeededTill.get(m) match
        {
          
          case None =>
            {
              valueNeededTill.put(m, currentStep);
            }
          
          case Some(x) =>
            {
              
            }
          
        }
        
      }
      
      
      currentStep = currentStep - 1;
    }
       
    
    valueNeededTill;
    
  }
  
  
  
  def executeWithoutDelete(context : B,mainPipeline : ProcessPipeline[_])
  {
    val progressMonitor = new NoOpProgressMonitor();
   
    
    for( step <- stepList )
    {
       val startTime = System.currentTimeMillis();
      
       step.execute( context , progressMonitor  );
       
       mainPipeline.traceStatistic(step,startTime); 
       
    }
  }
  
  
  
  
  def execute(context : B,progressMonitor : ProgressMonitor)
  {
    
     
    
     val  valueNeededTill = createDeleteMap();
     val currentlyActiveMethods = new HashSet[String]();
     var currentStep = 0;  
    
    
  //  val alreadyCalled = new HashSet[String]();
    
    
    for( step <- stepList )
    {
      /*
       * wir löschen vor dem ausführen, damit verbleiben die werte
       * für den letzten step
       */
      
      
      for( m <- currentlyActiveMethods )
      {
        
        valueNeededTill.get(m) match
        {
          
          case None =>
            {
              println("not needed ? " + m);
            }
          case Some(x) =>
            {
              
              if( x < currentStep )
              {
                valueNeededTill.remove(m);
                currentlyActiveMethods.remove(m);
                
                var deletedSuccesful = false;
                
                
                 for( c <- context.getClass().getMethods )
                {
                  if( c.getName == m  )
                  {
                     if( classOf[ToBeClosed].isAssignableFrom(    c.getReturnType ) )
                    {
                       val result =  c.invoke(  context );
                       
                       result.asInstanceOf[ToBeClosed].close();
                     
                    
                    }
                    
                  }
                }
                
                
                
                
                
                
                
                for( c <- context.getClass().getMethods )
                {
                  if( c.getName == m + "_$eq" )
                  {
                   
                   
                    if( ! c.getParameterTypes()(0).isPrimitive())
                    {
                       c.invoke(  context ,  null);
                    }
                    
                   
                    
                    deletedSuccesful = true;
                    
                  }
                  
                  
                }
                
         
              }
              
              
            }
          
          
        }
        
        
      }
      
      
      
      val currentMethods = step.getMethodSet(false);
      
      for( m <- currentMethods )
      {
        if( m.endsWith("_$eq") )
        {
          val name = m.substring(0 , m.length() - "_$eq".length() );
           
          currentlyActiveMethods.add(name)
  
        }
        
        
      }
      
      val startTime = System.currentTimeMillis();
      
       if(progressMonitor.isCanceled())
      {
        throw new TaskCanceledException();
      } 
      
      
     step.execute(context, progressMonitor);
      
      
      
      traceStatistic(step,startTime);
      
      currentStep = currentStep + 1;
    }
    
    
     logger.whenDebugEnabled
      {
       
        val list = new ArrayBuffer[Tuple2[String,Long]]
        
        for( elem <- stepName2CompleteDuration )
        {
          list.append( elem )
        }
        
        val sorted = list.sortBy(  x => -1* x._2 )
        var count = 0; 
       
        for( elem <- sorted )
        {
          if( count < 16 )
          {
            logger.debug( elem._1 + " " + DurationFormatUtils.formatDurationHMS(  elem._2)   )
          }
          
          count = count + 1;
        }
        
       
        val listCount = new ArrayBuffer[Tuple2[String,Long]]
          
        for( elem <- className2Count )
        {
          listCount.append( elem )
        }
        
          val sorted4Count = listCount.sortBy(  x => -1* x._2 )
         count = 0; 
       
        for( elem <- sorted4Count )
        {
          if( count < 8 )
          {
            logger.debug( elem._1 + " "  + elem._2  )
          }
          
          count = count + 1;
        }
        
        
        
        
        
        
        
        
        
      }
    
    
  }
   
    
  def traceEventCount( cl : Class[_], count : Int )
  {
      logger.whenDebugEnabled
      {
             
              val current = className2Count.getOrElseUpdate(cl.getName, 0L);
              className2Count.put( cl.getName , count + current );
      }
    
    
  }
  
  

     def traceStatistic(step : Step[_],startTime : Long)
  {
       
    logger.whenDebugEnabled
      {
              val duration =    System.currentTimeMillis() - startTime;
              val current = stepName2CompleteDuration.getOrElseUpdate(step.getStepName(), 0L);
              stepName2CompleteDuration.put(  step.getStepName() , duration + current );
      }
        

  } 
    

     
    
     
}
