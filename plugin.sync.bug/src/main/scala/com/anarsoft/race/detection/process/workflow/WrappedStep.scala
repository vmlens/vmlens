package com.anarsoft.race.detection.process.workflow

import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashMap;

class WrappedStep[C](val wrappedStep : Step[C], val contextClass : Class[C]) extends  Step[C] {
  
  
  def execute( in : C,progressMonitor : ProgressMonitor) =wrappedStep.execute(in, progressMonitor) 
  
  override  def getStepName() = wrappedStep.getClass().getCanonicalName()+ "@" + contextClass.getCanonicalName();
  
  
  override  def getMethodSet(firstTime : Boolean) =
  {
   
    val methodSet = new HashSet[String]();
   
 
       
   
         
         
            
          for( cm <- contextClass.getMethods() )
          {
            
            methodSet.add(cm.getName() );
          }
          
          

    
    methodSet;
    
  }
  
  
  
  //def desc = wrappedStep.desc;
  
  
}