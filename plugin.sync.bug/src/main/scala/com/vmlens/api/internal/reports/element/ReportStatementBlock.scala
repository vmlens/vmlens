package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._
import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
/*
 * 
 * val fieldAccess : Option[ReportStatementFieldAccess], val monitorOrLockAccess : Option[ReportStatementMonitorOrLockAccess] ,
val atomicMethod : Option[ReportAtomicMethod] , val callbackMethod : Option[ReportCallbackMethod]
 * 
 */


class ReportStatementBlock(val element : InterleaveEventStatement, val modelFacade : ModelFacadeAll ,val context : ContextLastRun) extends ReportElement[ContextReport] {
  
  
   def objectId  = element.objectId
  
   def operation   =element.operationText(modelFacade) 
   def method      =   element.methodModel(modelFacade).getFullNameWithBoldName() 

   def trStyle() = context.getTrStyle(element.threadId);
  
   
   def threadId = element.threadId
   
   def imagePath=
   {
   
     val icon = element.icon();
    
  
    if( icon == null )
    {
      null;
    }
    else
    {
      "img/" + icon.getName() + ".png" 
    }
  
   }
   
   
   
  
  
   def hasLink() = ! link.isEmpty
  
  
      def name() = "ReportParallizedStatementBlock";
  
       var link : Option[String] = None;
      
  
   def initialize( contextReport : ContextReport) 
       {
       link = contextReport.elementDetailLink.createLink4Template(  
           
         new ViewData(  children().map(  x =>  new ReportElementIssuePart(x,modelFacade) )  , Nil ,   "Stack Trace" , None,   "../" , contextReport  ) , Some( "../" ) , "templates/htmlIssue.mustache"
       
       );
       }
  
   
   def children( )       =
  {
         val list = new ArrayBuffer[IssuePartElement]();
     
    
     modelFacade.stackTraceGraph.formHereToRoot(new StackTraceOrdinal(element.stackTraceOrdinal) ,
          
       s =>
         {
          
              list.append(  new StackTraceModel(s) );
           
           
       
         }
       
      
      
      
      )
       list;
  }
   
  
}