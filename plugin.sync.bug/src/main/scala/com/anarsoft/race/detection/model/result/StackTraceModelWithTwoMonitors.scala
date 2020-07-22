package com.anarsoft.race.detection.model.result

import com.vmlens.api.internal._
import com.vmlens.api._;
import org.apache.commons.lang.StringEscapeUtils


class StackTraceModelWithTwoMonitors(val ordinal : StackTraceOrdinal,val parentMonitorId : Int,val childMonitorId : Int ) extends StackTraceModelAbstract {
  
   def nameInternal(viewTyp : ModelFacade) = "<<" + parentMonitorId + " -> " + childMonitorId + ">> " + ordinal.name(viewTyp.stackTraceGraph) ;

  def icon(modelFacade : ModelFacade)  = IconRepository.MONITOR;
  
  def children( modelFacade : ModelFacade)  = Nil;
  
  def nameWithHtmlInternal(viewTyp : ModelFacade) =  StringEscapeUtils.escapeHtml( "<<" + parentMonitorId + " -> " + childMonitorId + ">> " ) + ordinal.nameWithBoldName(viewTyp)  ; 

}