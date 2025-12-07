package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.ClassTypeVmlensApi
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}

case class VMLensApi(name : String) extends ClassModel {
  override def create(): PackageOrClass = new PackageOrClass(name,
    ClassTypeVmlensApi.SINGLETON,
    Array.ofDim[PreAnalyzedMethod](0))
}
