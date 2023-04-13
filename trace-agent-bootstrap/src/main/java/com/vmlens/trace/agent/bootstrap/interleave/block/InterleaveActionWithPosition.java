package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.WithPosition;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRunElement;


public class InterleaveActionWithPosition implements BlockBuilder , WithPosition, CalculatedRunElement {
    private final InterleaveAction interleaveAction;
    private final Position position;

    public InterleaveActionWithPosition(InterleaveAction interleaveAction, Position position) {
        this.interleaveAction = interleaveAction;
        this.position = position;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public int threadIndex() {
        return position.threadIndex;
    }

    @Override
    public BlockBuilderKey key() {
        return interleaveAction.blockBuilderKey();
    }

    @Override
    public void start(KeyToThreadIdToElementList<BlockKey, Block> result) {
        Block block = new Block(this,this);
        result.put(interleaveAction.blockKey(),block);
    }

    @Override
    public void add(BlockBuilder next, KeyToThreadIdToElementList<BlockKey, Block> result) {
        Block block = new Block(this,this);
        result.put(interleaveAction.blockKey(),block);
    }

    @Override
    public boolean startsAlternatingOrder(BlockElement other) {
        return interleaveAction.startsAlternatingOrder(other.interleaveAction());
    }

    @Override
    public boolean startsFixedOrder(BlockElement other) {
        return interleaveAction.startsFixedOrder(other.interleaveAction(), other.threadIndex());
    }

    @Override
    public InterleaveAction interleaveAction() {
        return interleaveAction;
    }
}
