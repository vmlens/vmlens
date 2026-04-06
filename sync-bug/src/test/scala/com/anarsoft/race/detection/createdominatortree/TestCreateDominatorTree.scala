package com.anarsoft.race.detection.createdominatortree

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TestCreateDominatorTree extends AnyFlatSpec with Matchers {

  "CreateDominatorTree " should "create the dominator tree for a simple tree" in {
    // Given
    val context = CreateGraphStackContext();
    val builder = TreeTestBuilder(context, 1);
    builder.method(1, () => {
      builder.method(2, () => {
        builder.syncBlock(3, () => {})
      })
    });

    // When 
    val dominatorTree = new CreateDominatorTree(context.normalizeVertex.graph,context.normalizeVertex.root).buildDominatorTree();
    
    // Then
     CompareGraph.shouldBe(dominatorTree, context.normalizeVertex.graph);

  }


}
