package com.anarsoft.race.detection.process.workflow

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap;


class CompositeStep[B](val name : String, val processPipeline :ProcessPipeline[_]) extends Step[B]{
  
  
  val stepList = new ArrayBuffer[Step[B]]();
  
  
   def step[A >: B]( step :  Step[A] ) 
  {
     stepList.append(  step );
  }
  
   
   override  def getStepName() = name; 
   
  
  def execute(context : B,progressMonitor : ProgressMonitor)
  {
    
     
    
    for( step <- stepList )
    {
      val startTime = System.currentTimeMillis();
      
      step.execute(context, progressMonitor)
      
      processPipeline.traceStatistic(step, startTime);
    
    }
    
    
  }
  

}