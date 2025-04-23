package com.vmlens.preanalyzed.serialize

import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.{ClassTypeAllStartWith, ClassTypeFilter, ClassTypeVmlensApi, PreAnalyzedAtomicReadWriteLock, PreAnalyzedSpecificMethods}
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.{GetReadWriteLockMethod, ThreadJoin, ThreadStart}
import com.anarsoft.trace.agent.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}
import com.vmlens.preanalyzed.factory.PreAnalyzedFactory
import com.vmlens.preanalyzed.model.*

import java.io.{DataOutputStream, FileOutputStream}
import scala.collection.mutable.ArrayBuffer

class TransformToPackageOrClass {

  def transform(list: List[PreAnalyzed]): List[PackageOrClass] = {
    val result = new ArrayBuffer[PackageOrClass]
    for (elem <- list) {
      val packageOrClass =
        elem match {
          case Filter(name) => {
            new PackageOrClass(name, ClassTypeFilter.SINGLETON, Array.ofDim[PreAnalyzedMethod](0))
          }
          case Include(name) => {
            new PackageOrClass(name, ClassTypeAllStartWith.SINGLETON, Array.ofDim[PreAnalyzedMethod](0))
          }
          case ThreadModel(name) => {
            new PackageOrClass(name,
              PreAnalyzedSpecificMethods.SINGLETON,
              Array(new PreAnalyzedMethod("start", "()V", ThreadStart.SINGLETON),
                new PreAnalyzedMethod("join", "()V", ThreadJoin.SINGLETON)));
          }
          case VMLensApi(name) => {
            new PackageOrClass(name,
              ClassTypeVmlensApi.SINGLETON,
              Array.ofDim[PreAnalyzedMethod](0))
          }
          case GetReadWriteLock(name) => {
            new PackageOrClass(name,
              PreAnalyzedAtomicReadWriteLock.SINGLETON,
              Array(new PreAnalyzedMethod("readLock", "()Ljava/util/concurrent/locks/Lock;", GetReadWriteLockMethod.GET_READ_WRITE_LOCK),
                new PreAnalyzedMethod("writeLock", "()Ljava/util/concurrent/locks/Lock;", GetReadWriteLockMethod.GET_READ_WRITE_LOCK)))
          }
          case Lock(name, lockType, methods) => {
            new PackageOrClass(name, PreAnalyzedAtomicReadWriteLock.SINGLETON, lockMethodsToArray(lockType, methods))

          }
        }
      result.append(packageOrClass)
    }
    result.toList;
  }


  def lockMethodsToArray(lockType: LockType, methods: List[LockMethod]): Array[PreAnalyzedMethod] = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      buffer.append(new PreAnalyzedMethod(elem.name, elem.desc, javaLockMethod(lockType, elem.lockOperation)))
    }
    buffer.toArray;
  }

  def javaLockMethod(lockType: LockType, lockOperation: LockOperation): com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod = {
    (lockOperation, lockType) match {
      case (LockEnter(), ReadLock()) => {
        com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod.ENTER_READ_LOCK;
      }
      case (LockEnter(), WriteLock()) => {
        com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod.ENTER_WRITE_LOCK;
      }
      case (LockEnter(), ReentrantLock()) => {
        com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod.ENTER_REENTRANT_LOCK;
      }
      case (LockExit(), ReadLock()) => {
        com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod.EXIT_READ_LOCK;
      }
      case (LockExit(), WriteLock()) => {
        com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod.EXIT_WRITE_LOCK;
      }
      case (LockExit(), ReentrantLock()) => {
        com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod.EXIT_REENTRANT_LOCK;
      }

    }
  }
  

}
