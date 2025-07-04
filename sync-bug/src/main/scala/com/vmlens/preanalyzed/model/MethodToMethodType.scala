package com.vmlens.preanalyzed.model

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.AbstractMethodType

class MethodToMethodType(val name : String, val desc : String, val abstractMethodType : AbstractMethodType) {

}

object MethodToMethodType {
  
  def toArray(list : List[MethodToMethodType]) : Array[PreAnalyzedMethod] = {
    val array = Array.ofDim[PreAnalyzedMethod](list.length)
    var index = 0;
    for( elem <-  list) {
      array(index) = new PreAnalyzedMethod(elem.name,elem.desc,elem.abstractMethodType);
      index = index + 1;
    }
    array
  }
  
}
