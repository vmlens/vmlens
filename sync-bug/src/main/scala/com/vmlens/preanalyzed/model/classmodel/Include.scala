package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.ClassTypeAllStartWith
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}

case class Include(name : String) extends ClassModel {
  
  override def create(): PackageOrClass =  
    new PackageOrClass(name, ClassTypeAllStartWith.SINGLETON, Array.ofDim[PreAnalyzedMethod](0))
}
