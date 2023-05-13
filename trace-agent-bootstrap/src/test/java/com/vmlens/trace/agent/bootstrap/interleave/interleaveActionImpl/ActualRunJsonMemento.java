package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class ActualRunJsonMemento {
    private static final String ACTION_NAME_VOLATILE_FIELD_ACCESS = "volatileFieldAccess";
    private int fieldId;
    private int operation;
    private int threadIndex;
    private String actionName;

    public static String toJson(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> list) {
        List result = new LinkedList();
        for (TLinkableWrapper<InterleaveActionWithPositionFactory> elem : list) {
            result.add(of(elem));

        }
        return new Gson().toJson(result);
    }

    public static TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> load(String name)  {
        try{
            InputStream inputStream = ActualRunJsonMemento.class.getResourceAsStream("/actualRun/" + name + ".json");
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        return fromJson(json);
        }
        catch(IOException exp) {
            throw new RuntimeException(exp);
        }
    }

    private static TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>>  fromJson(String json) {
        TypeToken<List<ActualRunJsonMemento>> collectionType = new TypeToken<List<ActualRunJsonMemento>>() {
        };
        List<ActualRunJsonMemento> serializedList = new Gson().fromJson(json, collectionType);
        TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> result = new TLinkedList<>();
        for (ActualRunJsonMemento elem : serializedList) {
            elem.add(result);
        }
        return result;
    }

    private static ActualRunJsonMemento
    of(TLinkableWrapper<InterleaveActionWithPositionFactory> element) {
        if (element.element instanceof InterleaveActionWithPositionFactoryImpl) {
            InterleaveActionWithPositionFactoryImpl factory = (InterleaveActionWithPositionFactoryImpl) element.element;
            Gson gson = new Gson();
            ActualRunJsonMemento memento =
                    gson.fromJson(gson.toJson(factory.interleaveAction()), ActualRunJsonMemento.class);
            memento.threadIndex = factory.threadIndex();
            if (factory.interleaveAction() instanceof VolatileFieldAccess) {
                memento.actionName = ACTION_NAME_VOLATILE_FIELD_ACCESS;
                return memento;
            }
            throw new RuntimeException("unknown interleaveAction class: " + factory.interleaveAction().getClass());
        }
        throw new RuntimeException("unknown factory class: " +  element.element.getClass());
    }

    private void add(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> result) {
        InterleaveActionWithPositionFactory factory = null;
        if(actionName.equals(ACTION_NAME_VOLATILE_FIELD_ACCESS)) {
            factory = new InterleaveActionWithPositionFactoryImpl(new VolatileFieldAccess(fieldId,operation),threadIndex);
        }
        result.add(new TLinkableWrapper<>(factory));
    }

}
