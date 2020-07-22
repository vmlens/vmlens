package com.anarsoft.race.detection.process.method

import com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId
import com.anarsoft.race.detection.model.method.StackTraceGraphBuilder

class MethodFlowPerThreadEmpty extends MethodFlowPerThread {
  
  
    val EMPTY_StackTraceOrdinalAndMethodId = new StackTraceOrdinalAndMethodId(0,0,None);
  
  
    def getStackTraceOrdinal(methodCounter : Int , methodId : Int , stackTraceTree : StackTraceTree )  = 
    {
         stackTraceTree.methodId2RootNode.get(methodId) match
                {
                  
                  case None =>
                    {
                       val newOrdinal = stackTraceTree.getStackTraceOrdinalInternal(methodId ,  StackTraceGraphBuilder.PARENT_ORDINAL_OF_ROOT  );
                     
                    
                       stackTraceTree.methodId2RootNode.put(methodId, newOrdinal);
                       
                    new StackTraceOrdinalAndMethodId(newOrdinal , methodId,None);
                                     
                      
                    }
                  
                  case Some(r) =>
                    {
                         new StackTraceOrdinalAndMethodId(r , methodId , None );               
                      
                    }
                  
                }
      
      
      
    }
  
    def hasStackTraceOrdinal(methodCounter : Int)  = false;
    
    def getStackTraceOrdinal(methodCounter : Int , methodId : Int , stackTraceIncomplete : Boolean , stackTraceTree : StackTraceTree ) = 
       getStackTraceOrdinal(methodCounter : Int , methodId : Int , stackTraceTree : StackTraceTree )  ;
    
    def getStackTraceOrdinal(methodCounter : Int , stackTraceTree : StackTraceTree  ) = EMPTY_StackTraceOrdinalAndMethodId;
}