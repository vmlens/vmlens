package com.vmlens.preanalyzed.serialize

import com.vmlens.preanalyzed.model.*
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.{ClassNotYetImplemented, ClassTypeAllStartWith, ClassTypeFilter, ClassTypeThreadPool, ClassTypeVmlensApi, DoNotTraceInClass, PreAnalyzedAllMethods, PreAnalyzedSpecificMethods}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod, methodtypeimpl}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.{ArrayNonBlockingMethod, GetReadWriteLockMethod, NonBlockingMethod, NotYetImplementedMethod, ThreadJoin, ThreadPoolJoin, ThreadPoolStart, ThreadStart}

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
          case NotYetImplementedClass(name) => {
            new PackageOrClass(name, ClassNotYetImplemented.SINGLETON, Array.ofDim[PreAnalyzedMethod](0))
          }
          case DoNotTraceIn(name) => {
            new PackageOrClass(name, DoNotTraceInClass.SINGLETON, Array.ofDim[PreAnalyzedMethod](0))
          }
          case ThreadPool(name, methods : List[ThreadPoolMethod]) => {
            new PackageOrClass(name, ClassTypeThreadPool.SINGLETON, threadPoolMethod(methods))
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
    method match {
      case AtomicArrayNonBlockingMethod(name, desc, methodType) => {
        addAtomicArrayMethod(name, desc, methodType, buffer)
      }
      case AtomicClassNonBlockingMethod(name, desc, methodType) => {
        addAtomicClassMethod(name, desc, methodType, buffer)
      }

    }
  }

  private def addAtomicArrayMethod(name: String, desc: String, methodType: AtomicNonBlockingMethodType,
                                   buffer : ArrayBuffer[PreAnalyzedMethod]): Unit = {
    methodType match {
      case Read() => {
        buffer.append(new PreAnalyzedMethod(name, desc, ArrayNonBlockingMethod.ARRAY_NON_BLOCKING_READ))

      }
      case Write() => {
        buffer.append(new PreAnalyzedMethod(name, desc, ArrayNonBlockingMethod.ARRAY_NON_BLOCKING_WRITE))

      }
      case ReadWrite() => {
        buffer.append(new PreAnalyzedMethod(name, desc, ArrayNonBlockingMethod.ARRAY_NON_BLOCKING_READ_WRITE))
      }

      case
        NotYetImplemented() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NotYetImplementedMethod.SINGLETON))

      }
    }
  }

  private def addAtomicClassMethod(name: String, desc: String, methodType: AtomicNonBlockingMethodType,
                                   buffer : ArrayBuffer[PreAnalyzedMethod]) : Unit = {
    methodType match {
      case Read() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NonBlockingMethod.NON_BLOCKING_READ))

      }
      case Write() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NonBlockingMethod.NON_BLOCKING_WRITE))

      }
      case ReadWrite() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NonBlockingMethod.NON_BLOCKING_READ_WRITE))
      }

      case
        NotYetImplemented() => {
        buffer.append(new PreAnalyzedMethod(name, desc, NotYetImplementedMethod.SINGLETON))

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


  private def lockMethodsToArray(lockType: LockType, methods: List[LockMethod]): Array[PreAnalyzedMethod] = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      buffer.append(new PreAnalyzedMethod(elem.name, elem.desc, javaLockMethod(lockType, elem.lockOperation)))
    }
    buffer.toArray;
  }

  private def javaLockMethod(lockType: LockType, lockOperation: LockOperation) : methodtypeimpl.LockMethod = {
    (lockOperation, lockType) match {
      case (LockEnter(), ReadLock()) => {
        methodtypeimpl.LockMethod.ENTER_READ_LOCK;
      }
      case (LockEnter(), WriteLock()) => {
        methodtypeimpl.LockMethod.ENTER_WRITE_LOCK;
      }
      case (LockEnter(), ReentrantLock()) => {
        methodtypeimpl.LockMethod.ENTER_REENTRANT_LOCK;
      }
      case (LockExit(), ReadLock()) => {
        methodtypeimpl.LockMethod.EXIT_READ_LOCK;
      }
      case (LockExit(), WriteLock()) => {
        methodtypeimpl.LockMethod.EXIT_WRITE_LOCK;
      }
      case (LockExit(), ReentrantLock()) => {
        methodtypeimpl.LockMethod.EXIT_REENTRANT_LOCK;
      }

    }
  }

  private def threadPoolMethod(methods : List[ThreadPoolMethod])  : Array[PreAnalyzedMethod]  = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      elem match {
        case com.vmlens.preanalyzed.model.ThreadStart(name , desc ) => {
          buffer.append(new PreAnalyzedMethod(name, desc, ThreadPoolStart.SINGLETON ))
        }
        case JoinAll(name , desc)  => {
          buffer.append(new PreAnalyzedMethod(name, desc, ThreadPoolJoin.JOIN_ALL))
        }
      }
    }
    buffer.toArray;
  }
  

}
