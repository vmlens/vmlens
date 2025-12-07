package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.ClassModel
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedAllMethods
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.GetReadWriteLockMethod
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}

case class GetReadWriteLock(name : String) extends ClassModel {
  
  override def create(): PackageOrClass = new PackageOrClass(name,
    PreAnalyzedAllMethods.SINGLETON,
    Array(new PreAnalyzedMethod("readLock", "()Ljava/util/concurrent/locks/Lock;", GetReadWriteLockMethod.GET_READ_WRITE_LOCK),
      new PreAnalyzedMethod("writeLock", "()Ljava/util/concurrent/locks/Lock;", GetReadWriteLockMethod.GET_READ_WRITE_LOCK)))

}
