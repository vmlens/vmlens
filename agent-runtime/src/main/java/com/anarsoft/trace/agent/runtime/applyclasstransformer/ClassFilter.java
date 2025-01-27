package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.util.Enumeration;
import java.util.Properties;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class ClassFilter {

    private final TLinkedList<TLinkableWrapper<String>> alwaysTake;
    private final TLinkedList<TLinkableWrapper<String>> filter;

    public ClassFilter(TLinkedList<TLinkableWrapper<String>> alwaysTake,
                       TLinkedList<TLinkableWrapper<String>> filter) {
        this.alwaysTake = alwaysTake;
        this.filter = filter;
    }

    public static ClassFilter create(Properties properties) {
        TLinkedList<TLinkableWrapper<String>> alwaysTake = new TLinkedList<>();
        TLinkedList<TLinkableWrapper<String>> filter = new TLinkedList<>();

        Enumeration<Object> enumeration = properties.keys();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            if (key.startsWith("take.")) {
                String value = properties.getProperty(key);
                alwaysTake.add(wrap(value.replace('.', '/')));
            }
            if (key.startsWith("filter.")) {
                String value = properties.getProperty(key);
                filter.add(wrap(value.replace('.', '/')));
            }
        }
        return new ClassFilter(alwaysTake, filter);
    }

    public boolean take(String className) {
        if (className.startsWith("java/lang/ThreadLocal")) {
            return false;
        }
        for (TLinkableWrapper<String> element : alwaysTake) {
            if (className.startsWith(element.element())) {
                return true;
            }
        }
        for (TLinkableWrapper<String> element : filter) {
            if (className.startsWith(element.element())) {
                return false;
            }
        }
        return true;
    }
}
