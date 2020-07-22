package com.anarsoft.race.detection.model.description

import  com.vmlens.api.internal.reports.Model2View

class FieldModel4Template(val packageName : String, val  className : String)  extends FieldModel {
  
    def getClassName() =  packageName + "."  + className;

    
     def getDisplayName() =  packageName + "."  + className;
     
      def getSearchData() = None;
  
      
     def getNameWithBold() =       Model2View.makeBreakable(   packageName + "." )   + "<strong>"  + className  + "</strong>";
  
}