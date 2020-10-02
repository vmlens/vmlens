package com.anarsoft.race.detection.model.result

import com.vmlens.api.internal._
import com.vmlens.api._;

class StackTraceModel(val ordinal : StackTraceOrdinal) extends StackTraceModelAbstract  {
  
  def nameInternal(viewTyp : ModelFacadeAll) = ordinal.name(viewTyp.stackTraceGraph);

  def icon(ModelFacadeAll : ModelFacadeAll)  = null;
  
  def children( ModelFacadeAll : ModelFacadeAll)  = Nil;
 
  
    def nameWithHtmlInternal(viewTyp : ModelFacadeAll) = ordinal.nameWithBoldName(viewTyp);

     
}