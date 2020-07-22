package com.vmlens.api

import com.anarsoft.race.detection.model.description._;
import com.vmlens.api.internal.reports.Model2View


case class MethodDescription(val   qualifiedClassName : String,val  methodName : String, val methodDesc : String) {
  
    def fullName() = qualifiedClassName + "." + methodName;
  
  
  def getDisplayName() =
   MethodModelFromTrace.getFullName(qualifiedClassName , methodName ) +   ParseDescription.createArgumentString(methodDesc)
 
   
   def getNameWithBold() =
   {
        MethodModelFromTrace.getFullNameWithBoldMethodName(qualifiedClassName , methodName ) +  Model2View.makeBreakable( ParseDescription.createArgumentString(methodDesc))
   }
       
   
   
  
}