package com.anarsoft.race.detection.dominatortree

import com.anarsoft.race.detection.dominatortree.key.{DefaultVertexKey, StateVertexKey}
import org.jgrapht.Graph
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}

import scala.collection.mutable

class NormalizeVertex {

  val graph = new DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge])
  val root = new VertexRoot();
  graph.addVertex(root);
  
  private val defaultVertexMap = new mutable.HashMap[DefaultVertexKey,DefaultVertex]();
  private val stateVertexMap = new mutable.HashMap[StateVertexKey, VertexState]();


  def addDefault(key : DefaultVertexKey) : DefaultVertex  = {
    defaultVertexMap.get(key) match {

      case None => {
        val x = key.create();
        defaultVertexMap.put(key,x);
        graph.addVertex(x);
        x;
      }
      case Some(x) => x;
    }
    
    
  }
  def addState( key : StateVertexKey) : VertexState = {
    stateVertexMap.get(key) match {
      case None => {
        val x = key.create();
        stateVertexMap.put(key, x);
        graph.addVertex(x);
        x;
      }
      case Some(x) => x;
    }
    
  }
  
  def getRoot : DominatorTreeVertex  = root;

}
