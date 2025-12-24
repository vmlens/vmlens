package com.vmlens.preanalyzed.factory.publicmethods

import com.vmlens.preanalyzed.factory.publicmethods.AtomicIntegerFieldUpdaterImpl.atomicIntegerFieldUpdaterImpl
import com.vmlens.preanalyzed.factory.publicmethods.AtomicLongCASUpdater.atomicLongCASUpdater
import com.vmlens.preanalyzed.factory.publicmethods.AtomicLongFieldUpdater.atomicLongFieldUpdater
import com.vmlens.preanalyzed.factory.publicmethods.AtomicLongLockedUpdater.atomicLockedUpdater
import com.vmlens.preanalyzed.factory.publicmethods.AtomicMarkableReference.atomicMarkableReference
import com.vmlens.preanalyzed.factory.publicmethods.AtomicReferenceFieldUpdater.atomicReferenceFieldUpdater
import com.vmlens.preanalyzed.factory.publicmethods.AtomicReferenceFieldUpdaterImpl.atomicReferenceFieldUpdaterImpl
import com.vmlens.preanalyzed.factory.publicmethods.ReflectField.reflectField
import com.vmlens.preanalyzed.model.PreAnalyzedList
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType

object PreAnalyzedPublicMethodClassesFactory {

  def classesWithPublicMethods(): PreAnalyzedList =  PreAnalyzedList(classList());
  
  def classList() : List[ClassWithMethodToMethodType] = {
    List[ClassWithMethodToMethodType](reflectField(),
      atomicIntegerFieldUpdaterImpl(),
      atomicLongCASUpdater(),
      atomicLockedUpdater(),
      atomicLongFieldUpdater(),
      atomicReferenceFieldUpdater(),
      atomicReferenceFieldUpdaterImpl(),
      atomicMarkableReference()
    )
  }
  

}
