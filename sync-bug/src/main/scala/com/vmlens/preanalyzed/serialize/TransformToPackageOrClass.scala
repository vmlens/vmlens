package com.vmlens.preanalyzed.serialize

import com.vmlens.preanalyzed.model.*
import com.vmlens.preanalyzed.model.lockoperation.LockOperation
import com.vmlens.preanalyzed.model.classmodel.{AtomicReadWriteLock, GetReadWriteLock, Include, ThreadModel, VMLensApi}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.{ClassNotYetImplemented, ClassTypeAllStartWith, ClassTypeFilter, ClassTypeThreadPool, ClassTypeVmlensApi, DoNotTraceInClass, PreAnalyzedAllMethods, PreAnalyzedSpecificMethods}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.{PackageOrClass, PreAnalyzedMethod, methodtypeimpl}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.{ArrayNonBlockingMethod, GetReadWriteLockMethod, NonBlockingMethod, NotYetImplementedMethod, ThreadJoin, ThreadPoolJoin, ThreadPoolStart, ThreadStart}

import scala.collection.mutable.ArrayBuffer

class TransformToPackageOrClass {

  def transform(list: List[ClassModel]): List[PackageOrClass] = {
    val result = new ArrayBuffer[PackageOrClass]
    for (elem <- list) {
      result.append(elem.create())
    }
    result.toList;
  }
  
}
