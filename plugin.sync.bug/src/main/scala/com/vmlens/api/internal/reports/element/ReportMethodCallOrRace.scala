package com.vmlens.api.internal.reports.element

import com.vmlens.api.internal.reports._
import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.process.method.MethodEvent
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.IconRepository


class ReportMethodCallOrRace(val event : MethodCallOrRace, val modelFacade : ModelFacadeAll , val  space : Int) extends ReportElement[ContextReport] {
  
   def name() = event.name(modelFacade)
 
   
   
    def spacerImage() =
    {
      val list = new ArrayBuffer[ReportText]
      
      for(i <- 0 until space )
      {
        list.append(  new ReportText(  "img/" + IconRepository.EMPTY.getName() + ".png"   ) )
        
      }
      
       
      
      list;
    }
   
   
   
   
   
   
   
   
      def imagePath=
   {
   
     val icon = event.icon();
    
  
    if( icon == null )
    {
      null;
    }
    else
    {
      "img/" + icon.getName() + ".png" 
    }
  
   }
   
  
   
     def initialize( contextReport : ContextReport) 
       {
      
       }
  
}