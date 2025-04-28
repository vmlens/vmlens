package com.vmlens.preanalyzed.serialize

import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.{ClassTypeAllStartWith, ClassTypeFilter, ClassTypeVmlensApi, PreAnalyzedAllMethods, PreAnalyzedSpecificMethods}
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.{GetReadWriteLockMethod, NonBlockingMethod, NotYetImplementedMethod, ThreadJoin, ThreadStart}
import com.anarsoft.trace.agent.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}
import com.vmlens.preanalyzed.model.{LockType, *}

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
              PreAnalyzedAllMethods.SINGLETON,
              Array(new PreAnalyzedMethod("readLock", "()Ljava/util/concurrent/locks/Lock;", GetReadWriteLockMethod.GET_READ_WRITE_LOCK),
                new PreAnalyzedMethod("writeLock", "()Ljava/util/concurrent/locks/Lock;", GetReadWriteLockMethod.GET_READ_WRITE_LOCK)))
          }
          case Lock(name, lockType, methods) => {
            new PackageOrClass(name, PreAnalyzedAllMethods.SINGLETON, lockMethodsToArray(lockType, methods))
          }
          case AtomicReadWriteLock(name, methods) => {
            new PackageOrClass(name, PreAnalyzedAllMethods.SINGLETON, methodWithLockToArray(methods))
          }
          case AtomicNonBlocking(name, methods) => {
            new PackageOrClass(name, PreAnalyzedAllMethods.SINGLETON, atomicNonBlockingMethodArray(methods))
          }
          }
      result.append(packageOrClass)
    }
    result.toList;
  }

  private def atomicNonBlockingMethodArray(methods: List[AtomicNonBlockingMethod]): Array[PreAnalyzedMethod] = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      addNonBlocking(elem,buffer);
    }
    buffer.toArray;
  }

  private def addNonBlocking(method : AtomicNonBlockingMethod,  buffer : ArrayBuffer[PreAnalyzedMethod]) : Unit = {
    method.methodType match {
      case Read() => {
        buffer.append(new PreAnalyzedMethod(method.name, method.desc,  NonBlockingMethod.NON_BLOCKING_READ))

      }
      case Write() => {
        buffer.append(new PreAnalyzedMethod(method.name, method.desc,  NonBlockingMethod.NON_BLOCKING_WRITE))

      }
      case ReadWrite() => {
        buffer.append(new PreAnalyzedMethod(method.name, method.desc, NonBlockingMethod.NON_BLOCKING_READ_WRITE))
      }

      case
        NotYetImplemented()  =>{
        buffer.append(new PreAnalyzedMethod(method.name, method.desc, NotYetImplementedMethod.SINGLETON))

      }


    }

  }


  private def methodWithLockToArray(methods : List[MethodWithLock]) : Array[PreAnalyzedMethod] = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      buffer.append(new PreAnalyzedMethod(elem.name, elem.desc, javaMethodWithLock(elem.lockType)))
    }
    buffer.toArray;
  }

  private def javaMethodWithLock(lockType: ReadOrWriteLockType): com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodWithLock = {
    lockType match {
      case ReadLock() => {
        com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodWithLock.METHOD_WITH_READ_LOCK;
      }
      case WriteLock() => {
        com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodWithLock.METHOD_WITH_WRITE_LOCK
      }
    }
  }


  private def lockMethodsToArray(lockType: LockType, methods: List[LockMethod]): Array[PreAnalyzedMethod] = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      buffer.append(new PreAnalyzedMethod(elem.name, elem.desc, javaLockMethod(lockType, elem.lockOperation)))
    }
    buffer.toArray;
  }

  private def javaLockMethod(lockType: LockType, lockOperation: LockOperation) : com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod = {
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
