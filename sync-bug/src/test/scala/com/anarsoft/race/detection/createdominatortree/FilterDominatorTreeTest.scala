package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FilterDominatorTreeTest extends AnyFlatSpec with Matchers {

  "filter " should "filter branch with only method calls but not with sync block" in {
    // Expected
    val expectedContext = CreateGraphStackContext();
    val expectedBuilder = TreeTestBuilder(expectedContext, 1);

    expectedBuilder.method(1, () => {
      expectedBuilder.syncBlock(3, () => {})
    });
    
    // Given
    val context = CreateGraphStackContext();
    val builder = TreeTestBuilder(context,1);

    builder.method(1, () => {
      builder.syncBlock(3, () => {} )
      builder.method(5, () => {})
    }  );
    val filter = new FilterDominatorTree(context.root,context.graph);

    // When
     val filteredGraph = filter.filter();

    // Then
    CompareGraph.shouldBe(filteredGraph,expectedContext.graph);
  }
  
}