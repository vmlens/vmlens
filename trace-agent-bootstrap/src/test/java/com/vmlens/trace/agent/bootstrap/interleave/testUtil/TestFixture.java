package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.Monitor;
import com.vmlens.trace.agent.bootstrap.testfixture.FixtureBuilder;
import com.vmlens.trace.agent.bootstrap.testfixture.ResultTestBuilder;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;
import static com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition.ep;
import static com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlock.db;
import static com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockOrMonitorEnterImpl.enter;
import static com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockOrMonitorExit.exit;

public class TestFixture {


    public static Pair<MapOfBlocks, TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>>> monitor() {
        ResultTestBuilder resultTestBuilderForBlock = new ResultTestBuilder();
        FixtureBuilder builder = new FixtureBuilder(resultTestBuilderForBlock);
        builder.beginThread(0);
        //   builder.monitorEnter(0);
        //builder.monitorExit(0);
        builder.beginThread(1);
        //builder.monitorEnter(0);
        //builder.monitorExit(0);

        Monitor monitor = new Monitor(0);

        MapOfBlocks blockContainer = new MapOfBlocks();
        blockContainer.addDependent(monitor.key(), db(ep(enter(monitor), p(0, 0)), ep(exit(monitor), p(0, 1))));
        blockContainer.addDependent(monitor.key(), db(ep(enter(monitor), p(1, 0)), ep(exit(monitor), p(1, 1))));


        // Fixme
        return new ImmutablePair<>(blockContainer, null);
    }


    public static TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> chainedMonitor() {
        ResultTestBuilder resultTestBuilderForBlock = new ResultTestBuilder();
        FixtureBuilder builder = new FixtureBuilder(resultTestBuilderForBlock);
        builder.beginThread(0);
        //   builder.monitorEnter(0);
        //   builder.monitorEnter(0);
        //   builder.monitorExit(0);
        //   builder.monitorExit(0);
        // Fixme
        return null;
    }

    private static AlternatingOrderElement withInverse(Position left, Position right) {
        return new AlternatingOrderElement(new LeftBeforeRight(left, right), new LeftBeforeRight(right, left));
    }


}
