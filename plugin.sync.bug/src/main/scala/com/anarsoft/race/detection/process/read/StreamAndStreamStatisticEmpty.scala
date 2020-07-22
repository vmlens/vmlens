package com.anarsoft.race.detection.process.read

class StreamAndStreamStatisticEmpty[EVENT] extends StreamAndStreamStatistic[EVENT]{
  
     var currentSlidingWindowId = 1; 
  
    def max()     = 0;
    def current() = currentSlidingWindowId;
    
     def execute( readCallback : ReadCallback[EVENT] )
     {
       currentSlidingWindowId = currentSlidingWindowId + 1;
       
        // readCallback.readSlidingWindowId( 0  );
     }
  
  
}