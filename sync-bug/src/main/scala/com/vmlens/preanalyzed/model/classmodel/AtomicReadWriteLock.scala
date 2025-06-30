package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.{MethodWithLock, ClassModel, ReadLock, WriteLock, ReadOrWriteLockType}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedAllMethods
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl

import scala.collection.mutable.ArrayBuffer

case class AtomicReadWriteLock(name : String, methods : List[MethodWithLock]) extends ClassModel {
  def take(className: String) : Boolean = className.equals(name);

  override def create(): PackageOrClass = new PackageOrClass(name, PreAnalyzedAllMethods.SINGLETON, methodWithLockToArray(methods))


  private def methodWithLockToArray(methods: List[MethodWithLock]): Array[PreAnalyzedMethod] = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      buffer.append(new PreAnalyzedMethod(elem.name, elem.desc, javaMethodWithLock(elem.lockType)))
    }
    buffer.toArray;
  }

  private def javaMethodWithLock(lockType: ReadOrWriteLockType): methodtypeimpl.MethodWithLock = {
    lockType match {
      case ReadLock() => {
        methodtypeimpl.MethodWithLock.METHOD_WITH_READ_LOCK;
      }
      case WriteLock() => {
        methodtypeimpl.MethodWithLock.METHOD_WITH_WRITE_LOCK
      }
    }
  }
}
