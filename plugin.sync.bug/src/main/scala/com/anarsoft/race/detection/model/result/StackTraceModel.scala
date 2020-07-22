package com.anarsoft.race.detection.model.result

import com.vmlens.api.internal._
import com.vmlens.api._;

class StackTraceModel(val ordinal : StackTraceOrdinal) extends StackTraceModelAbstract  {
  
  def nameInternal(viewTyp : ModelFacade) = ordinal.name(viewTyp.stackTraceGraph);

  def icon(modelFacade : ModelFacade)  = null;
  
  def children( modelFacade : ModelFacade)  = Nil;
 
  
    def nameWithHtmlInternal(viewTyp : ModelFacade) = ordinal.nameWithBoldName(viewTyp);

     
}