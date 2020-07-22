package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result.StackTraceOrdinal
import org.roaringbitmap.RoaringBitmap;
import com.anarsoft.race.detection.model.result.StackTraceGraph
import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap
import  com.anarsoft.race.detection.model.description.MethodModel

abstract class AbstractStackTraceData {
  
  
  def packageIdSet : RoaringBitmap;
  
  var visited = true;
  
 
  
  def addPackageId(methodModel : MethodModel, packageName2Ordinal : ModelKey2OrdinalMap[String])
  {
   methodModel.rootPackageName() match
    {
      
      case None =>
        {
          
        }
      
      case Some(p) =>
        {
          packageIdSet.add( packageName2Ordinal.getOrAddOrdinal(p));
        }
      
    }  
  }
  
  
 
  
  
}