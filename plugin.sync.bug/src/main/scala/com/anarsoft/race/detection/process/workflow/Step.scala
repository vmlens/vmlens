package com.anarsoft.race.detection.process.workflow

import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashMap;


trait Step[-IN] {
  
  
  def execute( in : IN,progressMonitor : ProgressMonitor);
  
  
  
  def getStepName() = getClass().getCanonicalName();
  
  
  
   def getMethodSet(firstTime : Boolean) =
  {
   
    val methodSet = new HashSet[String]();
   
   
   
   
    
    for( m <- getClass().getDeclaredMethods() )
    {
      if( m.getName().equals("execute") )
      {
        val param = m.getParameterTypes();
        if( ! param(0).getCanonicalName.equals("java.lang.Object" ) )
        {
          val contextClass = param(0);
           
          for( cm <- contextClass.getMethods() )
          {
            
            methodSet.add(cm.getName() );
          }
          
          
          
        }
        
        
      }
    }
    
    methodSet;
    
  }
  
  
  
  
  
  
  
  
  
}