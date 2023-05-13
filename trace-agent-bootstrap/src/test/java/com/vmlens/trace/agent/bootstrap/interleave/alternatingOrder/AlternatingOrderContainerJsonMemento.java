package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.google.gson.Gson;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArrays;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ActualRunJsonMemento;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class AlternatingOrderContainerJsonMemento {
    private LeftBeforeRight[] fixedOrderArray;
    private AlternatingOrderElement[] alternatingOrderArray;
    private Position[] actualRun;

    public AlternatingOrderContainerJsonMemento() {
    }

    private AlternatingOrderContainerJsonMemento(LeftBeforeRight[] fixedOrderArray, AlternatingOrderElement[] alternatingOrderArray, Position[] actualRun) {
        this.fixedOrderArray = fixedOrderArray;
        this.alternatingOrderArray = alternatingOrderArray;
        this.actualRun = actualRun;
    }

    public static String toJson(AlternatingOrderContainer container) {
        AlternatingOrderContainerJsonMemento memento = new AlternatingOrderContainerJsonMemento(container.orderArrays().fixedOrderArray,
                container.orderArrays().alternatingOrderArray, toArray(container.actualRun()));
        return new Gson().toJson(memento);

    }

    public static AlternatingOrderContainer load(String name) {
        try {
            InputStream inputStream = ActualRunJsonMemento.class.getResourceAsStream("/alternatingOrderContainer/" + name + ".json");
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            return fromJson(json);
        } catch (IOException exp) {
            throw new RuntimeException(exp);
        }
    }

    private static AlternatingOrderContainer fromJson(String json) {
        AlternatingOrderContainerJsonMemento memento = new Gson().fromJson(json, AlternatingOrderContainerJsonMemento.class);

        ThreadIndexToElementList<Position> run = new ThreadIndexToElementList<Position>();
        for (Position pos : memento.actualRun) {
            run.add(pos);
        }
        return new AlternatingOrderContainer(new OrderArrays(memento.fixedOrderArray, memento.alternatingOrderArray), run);
    }

    private static Position[] toArray(ThreadIndexToElementList<Position> run) {
        List<Position> result = new LinkedList<>();
        for (TLinkableWrapper<TLinkedList<TLinkableWrapper<Position>>> list : run) {
            for (TLinkableWrapper<Position> elem : list.element) {
                result.add(elem.element);
            }
        }
        return result.toArray(new Position[0]);
    }
}
