package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.{DoNotTraceInClass, DoNotTraceInContainsClassName}

class DoNotTraceInContains(name : String) extends ClassModel {
  def take(className: String) : Boolean = className.equals(name);

  override def create(): PackageOrClass = new PackageOrClass(name, DoNotTraceInContainsClassName.SINGLETON, Array.ofDim[PreAnalyzedMethod](0))

}