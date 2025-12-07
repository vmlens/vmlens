package com.vmlens.preanalyzed.model.classmodel

import com.vmlens.preanalyzed.model.{JoinAll, ClassModel, ThreadPoolMethod}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.ClassTypeThreadPool
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.{ThreadPoolJoin, ThreadPoolStart}

import scala.collection.mutable.ArrayBuffer


case class ThreadPool(name : String, methods : List[ThreadPoolMethod]) extends ClassModel {
  override def create(): PackageOrClass = new PackageOrClass(name, ClassTypeThreadPool.SINGLETON, threadPoolMethod(methods))

  private def threadPoolMethod(methods: List[ThreadPoolMethod]): Array[PreAnalyzedMethod] = {
    val buffer = new ArrayBuffer[PreAnalyzedMethod]();
    for (elem <- methods) {
      elem match {
        case com.vmlens.preanalyzed.model.ThreadStart(name, desc) => {
          buffer.append(new PreAnalyzedMethod(name, desc, ThreadPoolStart.SINGLETON))
        }
        case JoinAll(name, desc) => {
          buffer.append(new PreAnalyzedMethod(name, desc, ThreadPoolJoin.JOIN_ALL))
        }
      }
    }
    buffer.toArray;
  }
}
