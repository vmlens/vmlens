package com.anarsoft.race.detection.createdominatortree
import org.jgrapht.*
import org.jgrapht.graph.*
import org.jgrapht.traverse.DepthFirstIterator

import java.util
import java.util.{HashSet, Hashtable, Vector, Set as JSet}

/**
 * Compute Dominators of a graph, following:
 * A Simple, Fast Dominance Algorithm
 * (Cooper, Keith D. and Harvey, Timothy J. and Kennedy, Ken).
 */
class Dominators[V, E](
                        val graph: Graph[V, E],
                        val vertexPreOrder: util.Vector[V]
                      ) {

  private val preOrderMap = new util.Hashtable[V, Int]()
  private var idom: util.Hashtable[V, V] = _

  // initialize preorder map
  for (i <- 0 until vertexPreOrder.size()) {
    preOrderMap.put(vertexPreOrder.get(i), i)
  }

  protected def getOrder(vertex: V): Int =
    preOrderMap.get(vertex)

  protected def getIDom(vertex: V): V = {
    if (idom == null) computeDominators()
    idom.get(vertex)
  }

  protected def computeDominators(): Unit = {
    if (idom != null) return

    idom = new util.Hashtable[V, V]()
    val entry = vertexPreOrder.firstElement()

    idom.put(entry, entry)

    if (!graph.incomingEdgesOf(entry).isEmpty)
      throw new AssertionError(
        "The entry of the flow graph is not allowed to have incoming edges"
      )

    var changed = true
    while(changed) {
      changed = false

      val it = vertexPreOrder.iterator()
      while (it.hasNext) {
        val v = it.next()
        if (v != entry) {
          val oldIdom = getIDom(v)
          var newIdom: V = null.asInstanceOf[V]

          val preds = graph.incomingEdgesOf(v).iterator()
          while (preds.hasNext) {
            val e = preds.next()
            val pre = graph.getEdgeSource(e)

            if (getIDom(pre) != null) {
              if (newIdom == null)
                newIdom = pre
              else
                newIdom = intersectIDoms(pre, newIdom)
            }
          }

          if (newIdom == null)
            throw new AssertionError(s"newIDom == null !, for $v")

          if (oldIdom != newIdom) {
            idom.put(v, newIdom)
            changed = true
          }
        }
      }
    }  
  }

  private def intersectIDoms(v1: V, v2: V): V = {
    var a = v1
    var b = v2

    while (a != b) {
      if (getOrder(a) < getOrder(b))
        b = getIDom(b)
      else
        a = getIDom(a)
    }
    a
  }

  /**
   * Immediate dominators.
   */
  def getIDoms: util.Hashtable[V, V] = {
    computeDominators()
    idom
  }

  /**
   * Check whether a node dominates another one.
   */
  def dominates(dominator: V, dominated: V): Boolean = {
    computeDominators()

    if (dominator == dominated) return true

    var dom = getIDom(dominated)
    while (
      dom != null &&
        getOrder(dom) >= getOrder(dominator) &&
        dom != dominator
    ) {
      dom = getIDom(dom)
    }
    dominator == dom
  }

  def getStrictDominators(n: V): JSet[V] = {
    computeDominators()

    val strictDoms = new util.HashSet[V]()
    var dominated = n
    var iDom = getIDom(n)

    while (iDom != dominated) {
      strictDoms.add(iDom)
      dominated = iDom
      iDom = getIDom(dominated)
    }
    strictDoms
  }

  /**
   * Build the dominator tree.
   */
  def getDominatorTree: SimpleDirectedGraph[V, DefaultEdge] = {
    computeDominators()

    val domTree =
      new SimpleDirectedGraph[V, DefaultEdge](classOf[DefaultEdge])

    val it = graph.vertexSet().iterator()
    while (it.hasNext) {
      val node = it.next()
      domTree.addVertex(node)

      val iDom = getIDom(node)
      if (iDom != null && node != iDom) {
        domTree.addVertex(iDom)
        domTree.addEdge(iDom, node)
      }
    }
    domTree
  }
}

object Dominators {

  /**
   * Dominators using default DFS preorder traversal
   */
  def apply[V, E](graph: Graph[V, E], entry: V): Dominators[V, E] =
    new Dominators(graph, dfsPreOrder(graph, entry))

  private def dfsPreOrder[V, E](
                                 graph: Graph[V, E],
                                 entry: V
                               ): Vector[V] = {
    val iter = new DepthFirstIterator[V, E](graph, entry)
    iter.setCrossComponentTraversal(false)

    val trav = new util.Vector[V]()
    while (iter.hasNext) {
      trav.add(iter.next())
    }
    trav
  }
}

