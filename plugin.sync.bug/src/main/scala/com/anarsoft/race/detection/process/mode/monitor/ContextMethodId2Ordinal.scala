package com.anarsoft.race.detection.process.mode.monitor

import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap

trait ContextMethodId2Ordinal {
  
  
   var  methodId2Ordinal :  ModelKey2OrdinalMap[Int] = null;
   
   
    def initializeContextMethodId2Ordinal()
  {
     methodId2Ordinal =  ModelKey2OrdinalMap[Int]();

  }
  
}