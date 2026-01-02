package com.vmlens.preanalyzed.factory.protectedmethods

import com.vmlens.preanalyzed.factory.protectedmethods.FutureFactory.futureTask
import com.vmlens.preanalyzed.model.PreAnalyzedList
import com.vmlens.preanalyzed.model.classmodel.ClassWithMethodToMethodType

object PreAnalyzedProtectedMethodClassesFactory {

  def classesWithProtectedMethods(): PreAnalyzedList = PreAnalyzedList(classList());

  def classList(): List[ClassWithMethodToMethodType] = {
    List[ClassWithMethodToMethodType](futureTask())
  }
  
  
}
