package com.anarsoft.race.detection.model.result

import com.vmlens.api.internal._
import com.vmlens.api._;
import org.apache.commons.lang.StringEscapeUtils

class StackTraceModelWithOneMonitor(val ordinal : StackTraceOrdinal,val monitorId : Int )  extends StackTraceModelAbstract  {
  
    def nameInternal(viewTyp : ModelFacade) = "<<" + monitorId + ">> " +  ordinal.name(viewTyp.stackTraceGraph) ;

  def icon(modelFacade : ModelFacade)  = IconRepository.MONITOR;
  
  def children( modelFacade : ModelFacade)  = Nil;
  
   
  def nameWithHtmlInternal(viewTyp : ModelFacade) = StringEscapeUtils.escapeHtml( "<<" + monitorId + ">> " ) + ordinal.nameWithBoldName(viewTyp)  ;

}