package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.anarsoft.race.detection.testfixture.dominatortree.DominatorTreeObjectMother
import org.jgrapht.graph.DefaultEdge
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DominatorsTest extends AnyFlatSpec with Matchers {

  "Dominators" should "create a dominator tree" in {
    val dominatorTree = Dominators[DominatorTreeVertex, DefaultEdge](DominatorTreeObjectMother.oneLockCallGraph(),
      DominatorTreeObjectMother.ROOT).getDominatorTree

    CompareGraph.shouldBe(dominatorTree, DominatorTreeObjectMother.dominatorTreeOneLock())
  }


}
