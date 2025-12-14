package com.vmlens.report.input.run;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKeyVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.FutureKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.PhaserKey;

public class BarrierKeyTextBuilder implements BarrierKeyVisitor {
    
    private String text = "";
    
    @Override
    public void visit(FutureKey futureKey) {
        text = "Future" ;
    }

    @Override
    public void visit(PhaserKey phaserKey) {
        text = "Phaser";
    }
    
    public String build() {
        return text;
    }
}
