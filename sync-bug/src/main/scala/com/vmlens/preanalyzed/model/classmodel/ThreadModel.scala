package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedSpecificMethods
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.{ThreadJoin, ThreadStart}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}

case class ThreadModel(name : String) extends ClassModel {
  def take(className: String) : Boolean = className.equals(name);

  override def create(): PackageOrClass = new PackageOrClass(name,
    PreAnalyzedSpecificMethods.SINGLETON,
    Array(new PreAnalyzedMethod("start", "()V", ThreadStart.SINGLETON),
      new PreAnalyzedMethod("join", "()V", ThreadJoin.SINGLETON)));
}
