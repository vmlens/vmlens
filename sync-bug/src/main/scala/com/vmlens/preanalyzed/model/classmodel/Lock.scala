package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.lockoperation.LockOperation
import com.vmlens.preanalyzed.model.{LockMethod, LockType, ClassModel}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.PreAnalyzedAllMethods
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl

import scala.collection.mutable.ArrayBuffer

case class Lock(name : String, lockType : LockType, methods : List[LockMethod]) extends ClassModel {
  
  override def create(): PackageOrClass = new PackageOrClass(name, PreAnalyzedAllMethods.SINGLETON, lockMethodsToArray(lockType, methods))

  private def javaLockMethod(lockType: LockType, lockOperation: LockOperation): methodtypeimpl.AbstractMethodType = {
    lockOperation.create(lockType);
  }

  private def lockMethodsToArray(lockType: LockType, methods: List[LockMethod]): Array[PreAnalyzedMethod] = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      buffer.append(new PreAnalyzedMethod(elem.name, elem.desc, javaLockMethod(lockType, elem.lockOperation)))
    }
    buffer.toArray;
  }

}
