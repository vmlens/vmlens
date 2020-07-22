package com.vmlens.api.internal.reports.element

import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports._
import scala.collection.mutable.ArrayBuffer
import com.vmlens.api.internal.reports.Model2View
import com.anarsoft.race.detection.process.mode.state.CreateFieldGroups

class ReportMethodWithMonitor(val modelElement: MethodWithMonitor, val modelFacade: ModelFacadeMonitor,
  val isInChildView: Boolean) extends ReportElement with Ordered[ReportMethodWithMonitor] {

  def textInsteadState = 
    {
       if( modelElement.isThreadSafe )
       {
          Some("Thread Safe")
       }
       else
       {
         None;
       }
      
      
    }
  
  
  
     def map4Test()  = new TestMethodWithMonitor(  name() ,group() , None ,   methodContainsMonitor ,  calledMethodContainsMonitor );
  
  
  def methodContainsMonitor = modelElement.methodContainsMonitor;

  def calledMethodContainsMonitor = modelElement.calledMethodContainsMonitor(modelFacade);

  def name() = Model2View.makeBreakable(modelElement.methodOrdinal.fullName(modelFacade));

  def group() = modelElement.group;

  def color =
    {
      if (group() % 2 == 1) {
        // Some("Wheat")
        Some("#d8dbdd")

      } else {
        None;
      }

    }

  def compare(that: ReportMethodWithMonitor): Int =
    {
      if (group() != that.group()) {
        java.lang.Integer.compare(group(), that.group())
      } else {
        name().compareTo(that.name());
      }

    }

  def threadCount() = modelElement.threadCount(modelFacade.stackTraceGraph);

  var parentChildLink: Option[String] = None;

  def initialize(contextReport: ContextReport) {

    if (contextReport.showChildLinks) {
      val linkPrefix = if (isInChildView) {
        Some( "../" )
      } else {
         None;
      }
      
        parentChildLink = contextReport.elementDetailLink.createLink(  
        new ViewData( ReportParentChildMonitor(this, 20) , Nil ,  modelElement.methodOrdinal.fullName( modelFacade )  , None ,   "../" , contextReport  ) , linkPrefix )
  
      
      
//
//      parentChildLink = contextReport.parentChildLink.createLinkFromChild(
//        root,
//        (templateName, link) =>
//          {
//            new ReportView(ReportParentChildMonitor(this, 20), Nil, modelElement.methodOrdinal.fullName(modelFacade),
//              link, templateName, "../", contextReport)
//
//          })

    }

  }

}