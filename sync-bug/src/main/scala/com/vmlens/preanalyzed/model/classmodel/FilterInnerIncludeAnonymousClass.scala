package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.ClassTypeFilterInnerIncludeAnonymous
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}

case class FilterInnerIncludeAnonymousClass(name : String)  extends ClassModel {
  
  override def create(): PackageOrClass =
    new PackageOrClass(name, ClassTypeFilterInnerIncludeAnonymous.SINGLETON, Array.ofDim[PreAnalyzedMethod](0));
  

}
