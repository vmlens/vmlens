package com.anarsoft.race.detection.inttest

import com.vmlens.report.assertion.LeftBeforeRight
import com.vmlens.report.assertion.OnEventNoOp
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.{MonitorKey, ReentrantLockKey}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.anarsoft.race.detection.process.run.ProcessRunImpl
import com.vmlens.trace.agent.bootstrap.barriertype.{BarrierKeyTypeFuture, BarrierTypeNotify, BarrierTypeWait}

import scala.collection.mutable

class BarrierEventIntTest  extends AnyFlatSpec with Matchers {

  /**
   * Memory consistency effects: Actions taken by the asynchronous computation happen-before actions following the corresponding Future.get()
   * in another thread.
   *
   * see https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html
   *
   */
  "future set before get" should "lead to a partial order" in {
    // Given
    val runTestBuilder = new RunDataTestBuilder(1, 1);
    val firstThread = runTestBuilder.createEventTestBuilder(0);
    val secondThread = runTestBuilder.createEventTestBuilder(1);
    val hashCode = 100L;

    val set0 = firstThread.barrier(BarrierTypeNotify.SINGLETON,BarrierKeyTypeFuture.SINGLETON,hashCode)
    val get1 = secondThread.barrier(BarrierTypeWait.SINGLETON,BarrierKeyTypeFuture.SINGLETON,hashCode)

    // When
    val collected = new mutable.HashSet[LeftBeforeRight]();
    val data = runTestBuilder.build();
    new ProcessRunImpl(new CollectLeftBeforeRight(collected), new OnEventNoOp()).process(data);

    // Expected
    val expected = new mutable.HashSet[LeftBeforeRight]();
    expected.add(new LeftBeforeRight(set0, get1))

    // Then
    collected should be(expected)
  }


}
