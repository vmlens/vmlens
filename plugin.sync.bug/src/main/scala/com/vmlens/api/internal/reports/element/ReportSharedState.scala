package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports._
import com.anarsoft.race.detection.process.sharedState.CreateAccess4State
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.process.mode.state.CreateFieldGroups

class ReportSharedState(val model : SharedState,val  modelFacade : ModelFacadeState) extends ReportElement {
  
    var link : Option[String] = None;
  
  
  def initialize( contextReport : ContextReport) 
       {
             if(contextReport.showChildLinks   )  {
     
               val stackTraceOrdinalSet = new HashSet[StackTraceOrdinal]
               
               for( s <- model.memoryAggregateSet )
               {
                 
                 
                 modelFacade.stackTraceGraph.foreachStacktraceOrdinalForMemoryAggregate( s ,  x =>   { 
                   
                   
                   stackTraceOrdinalSet.add(x)  })
               }
               
               
               
               
                val parentList =  new CreateAccess4State().create(stackTraceOrdinalSet.toSet, modelFacade.stackTraceGraph); 
              
                
              val parent =  
    new ReportElementList("Accessed By:" ,  parentList.map( x =>   new ReportParentMethod(   x ,   modelFacade  )   ) ) ;     
                
                   val sharedState =   CreateFieldGroups.createForMemoryAggregateSet( model.memoryAggregateSet, modelFacade , ReportParentChild.MAX_SHARED_STATE_ELEMENTS ) 
    
    
        
       val sharedStateList = 
    new ReportElementList("State Detail:" ,   sharedState.toSeq.map(  x =>   new  ReportSharedState(x , modelFacade )  ).sortBy( x =>  x.name   ) );
                
                
                    val linkPrefix=   Some (  "../" )
     
      
        val  sharedStateListOption =
        if(sharedStateList.issues.length > 1)
        {
          Some(sharedStateList);
        }
        else
        {
          None
        }
                    
                    
          link = contextReport.elementDetailLink.createLink4Template(  
        new ViewData(  List( new ReportSharedStateDetail( parent , sharedStateListOption) ) , Nil ,  model.name  , None ,   "../" , contextReport  ) , linkPrefix  , "templates/htmlSharedStateAccess.mustache" )
                   
                   
               /*
                *   link = contextReport.issueDetailLinks.createLink(  
           
         new ViewData(  element.children(modelFacade).map(  x =>  new ReportElementIssuePart(x,modelFacade)   )  , Nil , name ,   "../" , contextReport  ) , None
       
       );
                */
               
               
            }
       }
  
  
  
  
  
  
  def name() = model.name;
  
  
}