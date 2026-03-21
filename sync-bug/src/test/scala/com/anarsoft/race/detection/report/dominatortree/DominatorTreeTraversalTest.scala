package com.anarsoft.race.detection.report.dominatortree

import com.anarsoft.race.detection.createdominatortree.{CreateGraphStackContext, TreeTestBuilder}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import scala.collection.mutable.ArrayBuffer

class DominatorTreeTraversalTest extends AnyFlatSpec with Matchers {

  "TreeTraversal " should "call ReportCallback for each element" in {
    // Given
    val expected = new ArrayBuffer[DominatorTreeVertex]();
    val context = CreateGraphStackContext();
    val builder = TreeTestBuilder(context, 1);
    expected.append(builder.method(1, () => {
      expected.append(builder.method(2, () => {
        expected.append(builder.syncBlock(3, () => {}))
      }))
    }));
    expected.append(context.root)

    val reportCallbackMock = new ReportCallbackMock();
    
    // When 
    new DominatorTreeTraversal().traverse(reportCallbackMock,context.graph,context.root);

    // Then
    reportCallbackMock.result should be(expected.reverse)
  }
  
}
