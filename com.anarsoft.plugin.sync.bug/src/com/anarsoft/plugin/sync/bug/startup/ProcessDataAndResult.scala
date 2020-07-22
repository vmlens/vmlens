package com.anarsoft.plugin.sync.bug.startup


import com.anarsoft.race.detection.model.result.ModelFacade
import java.util.concurrent.SynchronousQueue



class ProcessDataAndResult() {
 
  val result = new SynchronousQueue[Either[ModelFacade,Throwable]]
  
  private var receivedFlag = false;
  private val LOCK = new Object();
  
  def waitTillReceived()
  {
    LOCK.synchronized
    {
      while(  !  receivedFlag) 
      {
        LOCK.wait();
      }
      
      
      
      
    }
    
    
    
  }
  
  
  
  def received()
  {
     LOCK.synchronized
    {
        receivedFlag = true;
        LOCK.notifyAll();
        
        
    }
  }
  
  
  
  
}