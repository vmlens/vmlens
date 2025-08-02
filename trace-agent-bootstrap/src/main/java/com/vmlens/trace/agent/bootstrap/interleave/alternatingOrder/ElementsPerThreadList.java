package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

public class ElementsPerThreadList<ELEMENT> {

    private final ElementsPerThread<ELEMENT>[] threadArray;
    private final int elementCount;

    public ElementsPerThreadList(ElementsPerThread<ELEMENT>[] threadArray, int elementCount) {
        this.threadArray = threadArray;
        this.elementCount = elementCount;
    }

    public ElementsPerThread<ELEMENT>[] threadArray() {
        return threadArray;
    }

    public void reset() {
        for( ElementsPerThread<ELEMENT> list : threadArray) {
            list.reset();;
        }
    }

    public boolean isEmpty() {
        for(ElementsPerThread<ELEMENT> element : threadArray) {
            if(! element.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int elementCount() {
        return elementCount;
    }
}
