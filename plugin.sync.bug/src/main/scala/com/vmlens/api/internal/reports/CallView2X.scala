package com.vmlens.api.internal.reports

import com.anarsoft.race.detection.model.result._;
import java.io._;
import com.vmlens.api.internal.reports.element._;
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

abstract class CallView2X[RESULT] {
  
  def call(viewResult : ViewResult[ReportElement], reportDir : File, elementTemplate : String, elementDetailTemplate : String , elementTitle : String, noElementsTemplate : String ) : RESULT;
  
  
  def templateMonitor(modelFacade : ModelFacadeMonitor, reportDir : File) =
  {
         val viewData =  TransformModel2View.transformMonitor(modelFacade);      
           call(viewData , reportDir , "templates/htmlSharedMonitor.mustache" , "templates/htmlSharedMonitorDetail.mustache" , "Accessed Monitors" ,   "templates/htmlNoAccessedMonitors.mustache" );
  }
  
  
  
  def templateState(modelFacade : ModelFacadeState, reportDir : File) =
  {
     val viewData =  TransformModel2View.transformState(modelFacade);      
      call(viewData , reportDir ,  "templates/htmlSharedState.mustache" , "templates/htmlSharedStateDetail.mustache" , "Shared State" , "templates/htmlNoSharedState.mustache");
  }
  
  
  def templateAll(modelFacade : ModelFacadeAll, reportDir : File) =
  {
     val viewData =  TransformModel2View.transformAll(modelFacade);    
     call(viewData , reportDir ,  "templates/htmlParallizedGroups.mustache"    ,  "templates/htmlLastRun.mustache" , "Interleave" , "templates/htmlNothingInterleaved.mustache"   );
  }
  
  
  
}