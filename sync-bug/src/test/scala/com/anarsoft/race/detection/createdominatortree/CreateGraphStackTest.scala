package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexRoot}
import com.anarsoft.race.detection.report.element.runelementtype.ReportLockType.REENTRANT_LOCK
import com.anarsoft.race.detection.testfixture.dominatortree.DominatorTreeObjectMother
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}
import org.jgrapht.traverse.DepthFirstIterator
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable

class CreateGraphStackTest extends AnyFlatSpec with Matchers {

  "CreateGraphStack" should "not filter method calls" in {
    val alreadyAdded = new mutable.HashSet[DominatorTreeVertex]()
    val graph = new DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge]);


    val root = new VertexRoot();
    graph.addVertex(root)
    val createGraphStack = new CreateGraphStack(root,1);
    createGraphStack.methodEnter(5)
    createGraphStack.methodEnter(7)
    createGraphStack.methodEnter(7)
    createGraphStack.methodExit()
    createGraphStack.methodEnter(5)


    createGraphStack.lockEnter(REENTRANT_LOCK, 1);
    createGraphStack.lockExit(REENTRANT_LOCK,1,graph,alreadyAdded)


    CompareGraph.shouldBe(graph,DominatorTreeObjectMother.oneLockCallGraph())

  }



}