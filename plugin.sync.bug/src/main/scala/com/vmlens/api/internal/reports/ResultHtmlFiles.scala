package com.vmlens.api.internal.reports

import scala.collection.mutable.Stack
import java.io._;
import scala.collection.mutable.HashSet
import com.anarsoft.integration.CopyFiles

class ResultHtmlFiles(val reportDir : File ) extends ReportFactory  {
  
  val viewStack = new Stack[ViewProvider];
  val alreadyCreatedPrefix = new HashSet[String] 
  
  
  
  
  
  
  
  def addView(link: String,viewProvider : ViewProvider)
  {
    viewStack.push(viewProvider)
    
    
  }
  
  
  
  def create()
  {
    
      val copyFiles = new CopyFiles();
     copyFiles.copy("mvnHtmlReport", reportDir);
    
    
    
    while( ! viewStack.isEmpty  )
    {
      
      val op =  viewStack.pop();
      
      val current  = op.view();
      
      current.initialize();
      current.createHtmlReport(reportDir);
    }
    
    
  }
  
  
  def addPrefix(prefix : String)
  {
      if( ! alreadyCreatedPrefix.contains(prefix) )
     {
         if( ! new File( reportDir + "/" +  prefix ).exists() )
     {
         new File( reportDir + "/" +  prefix ).mkdir() ;
         
          alreadyCreatedPrefix.add(prefix)
     }
     }
  }
  
  
   
  
  
}