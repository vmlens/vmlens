package com.anarsoft.race.detection.process.workflow


import com.anarsoft.race.detection.process._;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashMap;

class Loop[C]( val pipeline : ProcessPipeline[C],val contextClass : Class[C],val  maxSlidingWindowId: Int,val mainPipeline : ProcessPipeline[_]) extends Step[C] {
  
  
  
  
  
  
  
  def execute(  in : C ,progressMonitor : ProgressMonitor)
  {
    
    val updateDoneEvery = (maxSlidingWindowId/90).asInstanceOf[Int]
    
    var updateCount = 0;
    
    
    for( i <- 0 to maxSlidingWindowId)
    {
      pipeline.executeWithoutDelete(in,mainPipeline);
      
      if(updateCount >  updateDoneEvery)
      {
        updateCount = 0;
        progressMonitor.done(1);
      }
      
      
      updateCount = updateCount  + 1;
      
      
      
      
    }
    
    
  
   
    
  }
  
  def desc = "";
  
  
  
  override  def getStepName() = getClass().getCanonicalName()+ "@" + contextClass.getCanonicalName();
  
  
  override  def getMethodSet(firstTime : Boolean) =
  {
   
    val methodSet = new HashSet[String]();
   
   
            
          for( cm <- contextClass.getMethods() )
          {
             
            methodSet.add(cm.getName() );
          }
          
          

    
    methodSet;
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}