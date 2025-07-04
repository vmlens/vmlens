package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.ClassTypeFilter
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}

case class Filter(name : String) extends ClassModel {
  def take(className: String) : Boolean = className.startsWith(name);

  override def create(): PackageOrClass =  
    new PackageOrClass(name, ClassTypeFilter.SINGLETON, Array.ofDim[PreAnalyzedMethod](0));
}
