package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.preanalyzed.model.MethodToMethodType
import com.vmlens.preanalyzed.model.MethodToMethodType.toArray
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PackageOrClass
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedAllMethods

class ClassWithMethodToMethodType(name : String, methods : List[MethodToMethodType]) extends ClassModel {
  def take(className: String): Boolean = className.equals(name);

  override def create(): PackageOrClass = {
    new PackageOrClass(name, PreAnalyzedAllMethods.SINGLETON, toArray(methods))
  }
}
