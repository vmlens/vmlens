package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.AbstractClassType

class ClassModelWithoutMethodDescription(name : String, abstractClassType : AbstractClassType) extends ClassModel   {

  def take(className: String): Boolean = className.equals(name);

  override def create(): PackageOrClass = new PackageOrClass(name, abstractClassType, Array.ofDim[PreAnalyzedMethod](0))


}
