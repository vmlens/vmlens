package com.vmlens.trace.agent.bootstrap.stack;

import java.util.Iterator;

public interface LockOrMonitorStack<ELEMENT> extends Stack<ELEMENT> {

    Iterator<ELEMENT> iterator();

}
