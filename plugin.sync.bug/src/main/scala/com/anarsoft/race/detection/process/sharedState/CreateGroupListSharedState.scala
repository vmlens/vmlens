package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result._

class CreateGroupListSharedState(val fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade, val alwaysProcessRoot : Boolean,val stackTraceGraphStateAnnotation : StackTraceGraphStateAnnotation)  extends CreateGroupList[MethodWithSharedStateBuilder] {
  
  def  stackTraceGraphPackageAnnotation =  stackTraceGraphStateAnnotation;
  
  
   def createBuilder(methodOrdinal : MethodOrdinal, group : Int,stackTraceOrdinal: StackTraceOrdinal, stackTraceGraph: StackTraceGraph) = 
     {
         val methodModel =  methodOrdinal.getMethodModel(   stackTraceGraph );
     
     
          new MethodWithSharedStateBuilder(methodOrdinal, group , methodModel.isThreadSafe , methodModel.isStateless,methodModel.stateNotFiltered, fieldAndArrayPerStackTraceFacade)
     }
  
   
    def processChilds(parent :MethodWithSharedStateBuilder ) = 
    {
      ! parent.isThreadSafe &&  !  parent.isStateless;
      
      
      
    }
   
}