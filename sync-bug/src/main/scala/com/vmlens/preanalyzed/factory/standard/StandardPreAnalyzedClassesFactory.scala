package com.vmlens.preanalyzed.factory.standard

import com.vmlens.preanalyzed.factory.standard.AtomicIntegerFieldUpdaterImpl.atomicIntegerFieldUpdaterImpl
import com.vmlens.preanalyzed.factory.standard.AtomicLongCASUpdater.atomicLongCASUpdater
import com.vmlens.preanalyzed.factory.standard.AtomicLongFieldUpdater.atomicLongFieldUpdater
import com.vmlens.preanalyzed.factory.standard.AtomicLongLockedUpdater.atomicLockedUpdater
import com.vmlens.preanalyzed.factory.standard.AtomicReferenceFieldUpdater.atomicReferenceFieldUpdater
import com.vmlens.preanalyzed.factory.standard.AtomicReferenceFieldUpdaterImpl.atomicReferenceFieldUpdaterImpl
import com.vmlens.preanalyzed.factory.standard.ReflectField.reflectField
import com.vmlens.preanalyzed.model.PreAnalyzedList
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType

object StandardPreAnalyzedClassesFactory {

  def standardClasses(): PreAnalyzedList =  PreAnalyzedList(classList());
  
  def classList() : List[ClassWithMethodToMethodType] = {
    List[ClassWithMethodToMethodType](reflectField(),
      atomicIntegerFieldUpdaterImpl(),
      atomicLongCASUpdater(),
      atomicLockedUpdater(),
      atomicLongFieldUpdater(),
      atomicReferenceFieldUpdater(),
      atomicReferenceFieldUpdaterImpl()
    )
  }
  

}
