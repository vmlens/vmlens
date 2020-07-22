package com.anarsoft.race.detection.process.partialOrder

trait ContextCreatePartialOrder {
      var partialOrder : PartialOrder  = null;
    var partialOrder4SlidingWindowIdBuilder : PartialOrderBuilder = null;
    
    
       
   def initializeContextCreatePartialOrder()
   {
       partialOrder = PartialOrder();
   }
    
}