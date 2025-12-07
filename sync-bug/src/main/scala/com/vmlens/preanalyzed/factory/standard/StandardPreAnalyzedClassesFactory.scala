package com.vmlens.preanalyzed.factory.standard

import com.vmlens.preanalyzed.factory.standard.AtomicIntegerFieldUpdaterImpl.atomicIntegerFieldUpdaterImpl
import com.vmlens.preanalyzed.factory.standard.ReflectField.reflectField
import com.vmlens.preanalyzed.model.PreAnalyzedList
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType

object StandardPreAnalyzedClassesFactory {

  def standardClasses(): PreAnalyzedList =  PreAnalyzedList(classList());
  
  def classList() : List[ClassWithMethodToMethodType] = {
    List[ClassWithMethodToMethodType](reflectField(),atomicIntegerFieldUpdaterImpl())
  }
  

}
