package com.vmlens.report.runelementtype;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKeyVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.FutureKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.PhaserKey;

public class BarrierKeyTextBuilder implements BarrierKeyVisitor {
    
    private String text = "";
    
    @Override
    public void visit(FutureKey futureKey) {
        text = "Future@" + futureKey.objecthashcode();
    }

    @Override
    public void visit(PhaserKey phaserKey) {
        text = "Phaser@" + phaserKey.objecthashcode();
    }
    
    public String build() {
        return text;
    }
}
