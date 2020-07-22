package com.anarsoft.race.detection.process.workflow


import scala.collection.mutable.HashMap;

trait SingleStep[-IN] extends Step[IN]{
  
  
  def execute(  in : IN );
 
  
   def execute( context : IN,progressMonitor : ProgressMonitor)
   {
      execute( context );
      
  
   }
  
  
}