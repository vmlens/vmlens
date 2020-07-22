package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import scala.collection.mutable.HashMap
import com.netflix.nfgraph.build._;
import com.netflix.nfgraph.util._;
import com.netflix.nfgraph.spec.NFPropertySpec._;
import com.netflix.nfgraph.spec._;
import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap
import org.roaringbitmap.IntConsumer
import com.anarsoft.trace.agent.runtime.util.AntPatternMatcher
import scala.collection.mutable.Stack

class StepAnnotateStackTraceGraphWithStateAndCheckStateless extends SingleStep[ContextSharedState] {

  def isNotFiltered(aggregateId: Int, context: ContextSharedState, except: Array[String], methodOrdinalFilter: HashSet[Int], stackTraceGraph: StackTraceGraph) =
    {
      val name = context.fieldAndArrayPerStackTraceFacade.stacktraceAggreagateOrdinal2LocationInClass(aggregateId).location.getName(context.fieldAndArrayFacade, context.stackTraceGraph);

      var take = true;

      for (x <- except) {
        if (name.equals(x)) {
          take = false;
        }

      }

      if (take) {
        stackTraceGraph.foreachStacktraceOrdinalForMemoryAggregate(aggregateId, (so) => {

          stackTraceGraph.formHereToRoot(so, (soToTest) => {

            if (methodOrdinalFilter.contains(stackTraceGraph.getMethodModelOrdinalForStackTraceNodeOrdinal(soToTest).ordinal)) {
              take = false;
            }

          })

        })

      }

      take

    }

  def buildClassName2MethodOrdinalSet(stackTraceGraph: StackTraceGraph) =
    {
      val name2Set = new HashMap[String, HashSet[Int]]
      val superClass2Child = new HashMap[String, HashSet[String]]

      for (i <- 0 until stackTraceGraph.methodOrdinal2Model.length) {
        val m = stackTraceGraph.methodOrdinal2Model(i)
        val set = name2Set.getOrElseUpdate(m.className, new HashSet[Int]())
        set.add(i)

        m.classModelOption match {

          case None =>
            {

            }
          case Some(x) =>
            {
              val set = superClass2Child.getOrElseUpdate(x.superName, new HashSet[String]())
              set.add(m.className)

              for (i <- x.interfaces) {
                val set4I = superClass2Child.getOrElseUpdate(i, new HashSet[String]())
                set4I.add(m.className)

              }

            }

        }

      }

      Tuple2(name2Set, superClass2Child);
    }

  /*
   * procedure DFS-iterative(G, v) is
    let S be a stack
    S.push(v)
    while S is not empty do
        v = S.pop()
        if v is not labeled as discovered then
            label v as discovered
            for all edges from v to w in G.adjacentEdges(v) do
                S.push(w)


      https://en.wikipedia.org/wiki/Depth-first_search
   */

  def getAllClasses(className: String, superClass2Child: HashMap[String, HashSet[String]]) =
    {
      val result = new HashSet[String]
      val stack = new Stack[String]
      stack.push(className)

      while (!stack.isEmpty) {
        val current = stack.pop();
        if (!result.contains(current)) {
          result.add(current);
          superClass2Child.get(current) match {
            case None =>
              {

              }

            case Some(x) =>
              {
                for (c <- x) {
                  stack.push(c)
                }

              }

          }

        }

      }

      result;

    }

  def execute(context: ContextSharedState) {

    val annotateStackTraceGraph = new AnnotateStackTraceGraphAlgo4State(context.fieldAndArrayPerStackTraceFacade, context.fieldAndArrayFacade);
    val packageName2Ordinal = ModelKey2OrdinalMap[String]();

    val array = annotateStackTraceGraph.annotate(context.stackTraceGraph, packageName2Ordinal)
    val notStateless = new ArrayBuffer[NotStateless]

    val stackTraceSchema = new NFGraphSpec(
      new NFNodeSpec(
        StackTraceGraphStateAnnotation.NODE_STACK_TRACE,

        new NFPropertySpec(StackTraceGraphStateAnnotation.REL_STACK_TRACE_2_MEMORY_AGGREGATE, StackTraceGraphStateAnnotation.NODE_MEMORY_AGGREGATE, MULTIPLE | COMPACT),
        new NFPropertySpec(StackTraceGraphStateAnnotation.REL_STACK_TRACE_2_PACKAGE, StackTraceGraphStateAnnotation.NODE_ROOT_PACKAGE, MULTIPLE | COMPACT)),

      new NFNodeSpec(StackTraceGraphStateAnnotation.NODE_MEMORY_AGGREGATE),
      new NFNodeSpec(StackTraceGraphStateAnnotation.NODE_ROOT_PACKAGE));

    val buildGraph = new NFBuildGraph(stackTraceSchema);

    val buildResult = buildClassName2MethodOrdinalSet(context.stackTraceGraph);

    val className2MethodOrdinalSet = buildResult._1
    val superClass2Child = buildResult._2

    for (i <- 0 until array.length) {
      val model = array(i);

      model.memoryAggregateIdSet.forEach(new IntConsumer() {
        def accept(id: Int) {

          buildGraph.addConnection(StackTraceGraphStateAnnotation.NODE_STACK_TRACE, i, StackTraceGraphStateAnnotation.REL_STACK_TRACE_2_MEMORY_AGGREGATE, id)
        }

      })

      if (model.methodModel.isStateless && !model.memoryAggregateIdSet.isEmpty()) {

        val methodOrdinalFilter = new HashSet[Int]

        for (parent <- model.methodModel.except) {

          val allClasses = getAllClasses(parent, superClass2Child);

          for (c <- allClasses) {
            className2MethodOrdinalSet.get(c) match {

              case None =>
                {

                }

              case Some(x) =>
                {
                  methodOrdinalFilter ++= x;
                }

            }
          }

        }

        model.memoryAggregateIdSet.forEach(new IntConsumer() {
          def accept(id: Int) {
            if (isNotFiltered(id, context, model.methodModel.except, methodOrdinalFilter, context.stackTraceGraph)) {
              notStateless.append(new NotStateless(new StackTraceOrdinal(i), id));
              model.methodModel.stateNotFiltered = true;
            }

          }
        })

      }

      model.packageIdSet.forEach(new IntConsumer() {
        def accept(id: Int) {
          buildGraph.addConnection(StackTraceGraphStateAnnotation.NODE_STACK_TRACE, i, StackTraceGraphStateAnnotation.REL_STACK_TRACE_2_PACKAGE, id)
        }

      })

    }

    val packageArray = Array.ofDim[String](packageName2Ordinal.currentOrdinalId + 1)

    for (elem <- packageName2Ordinal.modelKey2Ordinal) {
      packageArray(elem._2) = elem._1;
    }

    context.notStateless = notStateless;
    context.stackTraceGraphStateAnnotation = new StackTraceGraphStateAnnotation(buildGraph.compress(), packageArray);

  }

}