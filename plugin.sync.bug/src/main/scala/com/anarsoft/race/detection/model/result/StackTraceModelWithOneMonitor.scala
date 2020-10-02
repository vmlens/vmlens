package com.anarsoft.race.detection.model.result

import com.vmlens.api.internal._
import com.vmlens.api._;
import org.apache.commons.lang.StringEscapeUtils

class StackTraceModelWithOneMonitor(val ordinal : StackTraceOrdinal,val monitorId : Int )  extends StackTraceModelAbstract  {
  
    def nameInternal(viewTyp : ModelFacadeAll) = "<<" + monitorId + ">> " +  ordinal.name(viewTyp.stackTraceGraph) ;

  def icon(ModelFacadeAll : ModelFacadeAll)  = IconRepository.MONITOR;
  
  def children( ModelFacadeAll : ModelFacadeAll)  = Nil;
  
   
  def nameWithHtmlInternal(viewTyp : ModelFacadeAll) = StringEscapeUtils.escapeHtml( "<<" + monitorId + ">> " ) + ordinal.nameWithBoldName(viewTyp)  ;

}