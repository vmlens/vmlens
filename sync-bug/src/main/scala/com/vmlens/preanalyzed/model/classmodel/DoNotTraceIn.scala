package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.DoNotTraceInClass
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}

case class DoNotTraceIn(name : String) extends ClassModel {
  
  override def create(): PackageOrClass = new PackageOrClass(name, DoNotTraceInClass.SINGLETON, Array.ofDim[PreAnalyzedMethod](0))
}
