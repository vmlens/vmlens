package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.process.mode.state.CreateFieldGroups
import com.anarsoft.race.detection.process.sharedState._
import com.vmlens.api.internal.reports.ContextReport
import com.vmlens.api.internal.reports.ReportElement
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.result._

import  com.anarsoft.race.detection.model.description.ArrayModel
import com.vmlens.api.internal.IconRepository
import  com.anarsoft.race.detection.process.result.CreateThreadGroups

class ReportParentChildMonitor(val parent : ReportElementList , val child : ReportElementList, val threadList : ReportElementList) extends ReportElement {
  
    def name() = "ReportParentChildMonitor";
  
  
  def initialize( contextReport : ContextReport) 
       {
    
    if(contextReport.showChildLinks   )
    {
             parent.initialize(contextReport);
             child.initialize(contextReport);
    }
           

       }
  
}

object ReportParentChildMonitor
{
  
   
   def create4Parent( reportParentMethod : ReportParentMethodWithMonitor  , maxMethods : Int ) =
  {
  
     val tuple = CreateMonitorGroupListFacade.create4Parent(reportParentMethod.parentMethod.stackTraceOrdinalSet.map(  x =>x.ordinal ), reportParentMethod.modelFacade.stackTraceGraph, 
         reportParentMethod.modelFacade.stackTraceGraphMonitorAnnotation);
     
      val parentList =  new CreateParents4Monitor().create(reportParentMethod.parentMethod.stackTraceOrdinalSet, reportParentMethod.modelFacade.stackTraceGraph);
    
      val parent =  
    new ReportElementList("Called By:" ,  parentList.map( x =>   new ReportParentMethodWithMonitor(   x ,   reportParentMethod.modelFacade  )   ) ) ;
     
      
         val child = 
    new ReportElementList("Calling Method:" , tuple._2.map( x =>  new ReportMethodWithMonitor( x,  reportParentMethod.modelFacade , true)   ).sorted.take(maxMethods)  );
     
              
          val threadOrdinalSet = tuple._1.threadOrdinalSet( reportParentMethod.modelFacade.stackTraceGraph);
    val threadNames =  new HashSet[ThreadModel]
      
    for( t <-   threadOrdinalSet )
    {
      threadNames.add(     reportParentMethod.modelFacade.threadNames.getThreadModelForOrdinal( t ));
    }
      
  
    
    
    val threadGroups =  CreateThreadGroups.create(threadNames.toSet);
    
    
    
       val threadList = 
    new ReportElementList("Threads:" ,   threadGroups.toSeq.map(  x =>  
      {
         val text =
           if( x.count == 1)
               {
             x.name
               }
           else
           {
             x.name + " ("  + x.count + " Threads)"
           }
        
          new  ReportTextAndImage(text , IconRepository.imagePath(IconRepository.THREAD ) )
        
      }
      ).sortBy( x =>  x.name   ) );
    
   List(  new  ReportParentChildMonitor(parent, child  , threadList)   )
        
        
        
        
        
         
  }  
  
  
  def apply(  element : ReportMethodWithMonitor , maxMethods : Int ) =
  {
   
    
    
    val parentList =  new CreateParents4Monitor().create(element.modelElement.stackTraceOrdinalSet, element.modelFacade.stackTraceGraph);
    
    
    
    
    // .sortBy(  x => x.toReportMethodWithSharedState()  )  
    
    
    val parent =  
    new ReportElementList("Called By:" ,  parentList.map( x =>   new ReportParentMethodWithMonitor(   x ,   element.modelFacade  )   ) ) ;
    
    
    val child = 
    new ReportElementList("Calling Method:" ,  CreateMonitorGroupListFacade.create4Child( element.modelElement.stackTraceOrdinalSet.map(  x => x.ordinal ) ,  element.modelFacade.stackTraceGraph 
        , element.modelFacade.stackTraceGraphMonitorAnnotation)
        .map( x =>  new ReportMethodWithMonitor( x,  element.modelFacade , true)   ).sorted.take(maxMethods)  );
    
    
  
    val threadOrdinalSet = element.modelElement.threadOrdinalSet( element.modelFacade.stackTraceGraph);
    val threadNames =  new HashSet[ThreadModel]
      
    for( t <-   threadOrdinalSet )
    {
      threadNames.add(     element.modelFacade.threadNames.getThreadModelForOrdinal( t ));
    }
      
  
    
    
    val threadGroups =  CreateThreadGroups.create(threadNames.toSet);
    
    
    
       val threadList = 
    new ReportElementList("Threads:" ,   threadGroups.toSeq.map(  x =>  
      {
         val text =
           if( x.count == 1)
               {
             x.name
               }
           else
           {
             x.name + " ("  + x.count + " Threads)"
           }
        
          new  ReportTextAndImage(text , IconRepository.imagePath(IconRepository.THREAD ) )
        
      }
      ).sortBy( x =>  x.name   ) );
    
   List(  new  ReportParentChildMonitor(parent, child  , threadList)   )
    

  
  }
  
  
 
}