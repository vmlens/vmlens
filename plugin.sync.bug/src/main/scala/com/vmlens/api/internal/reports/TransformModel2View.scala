package com.vmlens.api.internal.reports

import com.anarsoft.race.detection.model.result._;
import java.io.File;
import com.vmlens.api.internal.reports.element._;
import  com.vmlens.api._;
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

object TransformModel2View {
  
  
  def createInconsistentOwner4State() =
  {
      
  }
    
  
  def transformAll(modelFacade :  ModelFacadeAll) =
  {
  
    val seq =   (modelFacade.races ++ modelFacade.deadlocks).toSeq;
  
 
    val issues =   seq.map(  x =>   new ReportElementIssue(x,modelFacade ) ).sortBy( x => x.name   );
    
    var index = 0;
    
    for (elem <- issues )
    {
      elem.index = index;
      index = index + 1;
    }
   
    
    val elements =   modelFacade.loopId2Result.createNameAndResult() .map( x =>     
             new ReportInterleaveLoop(x._1 , x._2 , modelFacade)).sortBy( x => x.name   )
    
    index = 0;
    
    for (elem <- elements )
    {
      elem.index = index;
      index = index + 1;
    }
   
    
     
         new ViewResult( issues ,  elements );
    
  }
  
  
}