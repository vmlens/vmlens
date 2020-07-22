package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.process.mode.state.CreateFieldGroups
import com.anarsoft.race.detection.process.sharedState.CreateParents4State
import com.vmlens.api.internal.reports.ContextReport
import com.vmlens.api.internal.reports.ReportElement
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.result._
import com.anarsoft.race.detection.process.sharedState.CreateSharedStateGroupListFacade
import  com.anarsoft.race.detection.model.description.ArrayModel
import com.vmlens.api.internal.IconRepository
import  com.anarsoft.race.detection.process.result.CreateThreadGroups


class ReportParentChild(val parent : ReportElementList , val child : ReportElementList, val sharedState :  ReportElementList, val threadList : ReportElementList) extends ReportElement {
  
  
    def name() = "ReportParentChild";
  
  
  def initialize( contextReport : ContextReport) 
       {
    
    if(contextReport.showChildLinks   )
    {
             parent.initialize(contextReport);
             child.initialize(contextReport);
             sharedState.initialize(contextReport);
    }
           

       }
  
  
}

object ReportParentChild
{
  
   val MAX_SHARED_STATE_ELEMENTS = 10;
  
   
   def create4Parent( reportParentMethod : ReportParentMethod  , maxMethods : Int ) =
  {
  
     val tuple = CreateSharedStateGroupListFacade.create4Parent(reportParentMethod.parentMethod.stackTraceOrdinalSet.map(  x =>x.ordinal ),
         reportParentMethod.modelFacade.stackTraceGraph , reportParentMethod.modelFacade.fieldAndArrayPerStackTraceFacade , reportParentMethod.modelFacade.stackTraceGraphStateAnnotation );
     
      val parentList =  new CreateParents4State().create(reportParentMethod.parentMethod.stackTraceOrdinalSet, reportParentMethod.modelFacade.stackTraceGraph);
    
      val parent =  
    new ReportElementList("Called By:" ,  parentList.map( x =>   new ReportParentMethod(   x ,   reportParentMethod.modelFacade  )   ) ) ;
     
      
         val child = 
    new ReportElementList("Calling Method:" , tuple._2.map( x =>  new ReportMethodWithSharedState( x,  reportParentMethod.modelFacade , true)   ).sorted.take(maxMethods)  );
     
        val sharedState =   CreateFieldGroups.createForMemoryAggregateSet( tuple._1.memoryAggregateSet(reportParentMethod.modelFacade).toSet, reportParentMethod.modelFacade , MAX_SHARED_STATE_ELEMENTS )   
           val sharedStateList = 
    new ReportElementList("Shared State:" ,   sharedState.toSeq.map(  x =>   new  ReportSharedState(x, reportParentMethod.modelFacade )  ).sortBy( x =>  x.name   ) );
         
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
    
   List(  new  ReportParentChild(parent, child , sharedStateList , threadList)   )
        
        
        
        
        
         
  }  
  
  
  def apply(  element : ReportMethodWithSharedState , maxMethods : Int ) =
  {
   
    
    
    val parentList =  new CreateParents4State().create(element.sharedState.stackTraceOrdinalSet, element.modelFacade.stackTraceGraph);
    
    
    
    
    // .sortBy(  x => x.toReportMethodWithSharedState()  )  
    
    
    val parent =  
    new ReportElementList("Called By:" ,  parentList.map( x =>   new ReportParentMethod(   x ,   element.modelFacade  )   ) ) ;
    
    
    val child = 
    new ReportElementList("Calling Method:" ,  CreateSharedStateGroupListFacade.create4Child( element.sharedState.stackTraceOrdinalSet.map(  x => x.ordinal ) ,  
        element.modelFacade.stackTraceGraph,element.modelFacade.fieldAndArrayPerStackTraceFacade , element.modelFacade.stackTraceGraphStateAnnotation )
        .map( x =>  new ReportMethodWithSharedState( x,  element.modelFacade , true)   ).sorted.take(maxMethods)  );
    
    
    val sharedState =   CreateFieldGroups.createForMemoryAggregateSet( element.sharedState.memoryAggregateSet(element.modelFacade).toSet, element.modelFacade , MAX_SHARED_STATE_ELEMENTS ) 
    
    
        
       val sharedStateList = 
    new ReportElementList("Shared State:" ,   sharedState.toSeq.map(  x =>   new  ReportSharedState(x , element.modelFacade )  ).sortBy( x =>  x.name   ) );
    
   
    
    
    
    
    
    val threadOrdinalSet = element.sharedState.threadOrdinalSet( element.modelFacade.stackTraceGraph);
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
    
   List(  new  ReportParentChild(parent, child , sharedStateList , threadList)   )
    

  
  }
  
  
 
}