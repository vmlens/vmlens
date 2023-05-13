package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;

import java.util.Arrays;

public class OrderArrays {
    public final  LeftBeforeRight[] fixedOrderArray;
    public final  AlternatingOrderElement[] alternatingOrderArray;

    public OrderArrays(LeftBeforeRight[] fixedOrderArray, AlternatingOrderElement[] alternatingOrderArray) {
        this.fixedOrderArray = fixedOrderArray;
        this.alternatingOrderArray = alternatingOrderArray;

        Arrays.sort(this.fixedOrderArray);
        Arrays.sort(this.alternatingOrderArray);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderArrays that = (OrderArrays) o;

        if (!Arrays.equals(fixedOrderArray, that.fixedOrderArray)) return false;
        return Arrays.equals(alternatingOrderArray, that.alternatingOrderArray);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(fixedOrderArray);
        result = 31 * result + Arrays.hashCode(alternatingOrderArray);
        return result;
    }

    public void debug(AgentLogger agentLogger) {
        if (agentLogger.isDebugEnabled()) {
            // ToDo improve formatting for large arrays
            agentLogger.debug(Arrays.toString(fixedOrderArray));
            agentLogger.debug(Arrays.toString(alternatingOrderArray));
        }
    }

    @Override
    public String toString() {
        return "OrderArrays{" +
                "fixedOrderArray=" + Arrays.toString(fixedOrderArray) +
                ", alternatingOrderArray=" + Arrays.toString(alternatingOrderArray) +
                '}';
    }
}
