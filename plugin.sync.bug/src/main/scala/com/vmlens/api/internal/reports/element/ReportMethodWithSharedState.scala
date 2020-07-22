package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.model.result.MethodWithSharedState
import com.anarsoft.race.detection.model.result.ModelFacadeState
import com.vmlens.api.internal.reports._
import scala.collection.mutable.ArrayBuffer
import com.vmlens.api.internal.reports.Model2View
import com.anarsoft.race.detection.process.mode.state.CreateFieldGroups
import com.vmlens.api._
import com.vmlens.api.internal._


class ReportMethodWithSharedState(val sharedState: MethodWithSharedState, val modelFacade: ModelFacadeState, val isInChildView: Boolean) extends ReportElement with Ordered[ReportMethodWithSharedState] {

    def textInsteadState = 
    {
       if( sharedState.isStateless )
       {
         
         if(sharedState.stateNotFiltered)
         {
           Some("Error: Not Stateless");
         }
         else
         {
            Some("Stateless");
         }
         
         
        
       }
       else if( sharedState.isThreadSafe )
       {
          Some("Thread Safe")
       }
       else
       {
         None;
       }
      
      
    }
    
    
    
    def imagePath=
   {
      
      
       if( sharedState.isStateless && sharedState.stateNotFiltered)
       {
         val icon =   IconRepository.getImageForField(   new  MemoryAccessType(MemoryAccessType.IS_READ_WRITE) , false , false , true  )
         
         val oneUp = if(isInChildView)
         {
           "../"
         }
         else
         {
           ""
         }
         
         
         Some( oneUp + "img/" + icon.getName() + ".png" )
       }
       else
       {
         None;
       }
  
  
   }
    
  
  
  def name() = Model2View.makeBreakable(sharedState.methodOrdinal.fullName(modelFacade));

  def group() = sharedState.group;

  def color =
    {
      if (group() % 2 == 1) {
        // Some("Wheat")
        // Some("#d8dbdd")

        Some("#e6e6e6")

      } else {
        None;
      }

      //  Some("#c2c6cb")

      // None;
    }

  def threadCount() = sharedState.threadCount(modelFacade.stackTraceGraph);

  def hasState = !sharedState.memoryAggregateSet(modelFacade).isEmpty

  def state =
    {
      if (sharedState.memoryAggregateSet(modelFacade).isEmpty) {
        Nil
      } else {
        CreateFieldGroups.createForMemoryAggregateSet(sharedState.memoryAggregateSet(modelFacade).toSet, modelFacade, 3).map(x => new ReportText(Model2View.makeBreakable(x.name)));
      }

    }

  def compare(that: ReportMethodWithSharedState): Int =
    {
      if (group() != that.group()) {
        java.lang.Integer.compare(group(), that.group())
      } else {
        name().compareTo(that.name());
      }

    }

  var parentChildLink: Option[String] = None;

  def initialize(contextReport: ContextReport) {

    if (contextReport.showChildLinks) {
      val linkPrefix = if (isInChildView) {
         Some (  "../" )
      } else {
         None;
      }
      
          parentChildLink = contextReport.elementDetailLink.createLink(  
        new ViewData( ReportParentChild(this, 20) , Nil ,  sharedState.methodOrdinal.fullName( modelFacade )  ,   None ,  "../" , contextReport  ) , linkPrefix )

//      parentChildLink = contextReport.parentChildLink.createLinkFromChild(
//        root,
//        (templateName, link) =>
//          {
//            new ReportView(ReportParentChild(this, 20), Nil, sharedState.methodOrdinal.fullName(modelFacade),
//              link, templateName, "../", contextReport)
//
//          })
    }

  }

}