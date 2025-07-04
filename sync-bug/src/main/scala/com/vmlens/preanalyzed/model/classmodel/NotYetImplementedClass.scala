package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.ClassNotYetImplemented
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}

case class NotYetImplementedClass(name : String) extends ClassModel {
  def take(className: String) : Boolean = className.equals(name);

  override def create(): PackageOrClass = new PackageOrClass(name, ClassNotYetImplemented.SINGLETON, Array.ofDim[PreAnalyzedMethod](0))
}
