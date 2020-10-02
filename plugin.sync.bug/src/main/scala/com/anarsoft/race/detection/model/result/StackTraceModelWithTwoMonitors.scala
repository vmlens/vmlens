package com.anarsoft.race.detection.model.result

import com.vmlens.api.internal._
import com.vmlens.api._;
import org.apache.commons.lang.StringEscapeUtils


class StackTraceModelWithTwoMonitors(val ordinal : StackTraceOrdinal,val parentMonitorId : Int,val childMonitorId : Int ) extends StackTraceModelAbstract {
  
   def nameInternal(viewTyp : ModelFacadeAll) = "<<" + parentMonitorId + " -> " + childMonitorId + ">> " + ordinal.name(viewTyp.stackTraceGraph) ;

  def icon(ModelFacadeAll : ModelFacadeAll)  = IconRepository.MONITOR;
  
  def children( ModelFacadeAll : ModelFacadeAll)  = Nil;
  
  def nameWithHtmlInternal(viewTyp : ModelFacadeAll) =  StringEscapeUtils.escapeHtml( "<<" + parentMonitorId + " -> " + childMonitorId + ">> " ) + ordinal.nameWithBoldName(viewTyp)  ; 

}