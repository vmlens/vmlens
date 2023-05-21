package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

/**
 * responsible for the storage of elements for each thread index
 * hides how the relation between thredindex and element list is stored
 */
public class ThreadIndexToElementList<ELEMENT extends WithThreadIndex> implements
        Iterable<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>>, ThreadIndexToMaxPosition {

    private ThreadIndexToElementList(TLinkedList<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> threadList,
                                     int elementCount) {
        this.threadList = threadList;
        this.elementCount = elementCount;
    }

    private final TLinkedList<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> threadList;
    private int elementCount;

    public ThreadIndexToElementList() {
        this(new TLinkedList<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>>(), 0);
    }

    public ThreadIndexToElementList<ELEMENT> safeClone() {
        TLinkedList<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> clone = new
                TLinkedList<>();
        for (TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>> oneThread : threadList) {
            TLinkedList<TLinkableWrapper<ELEMENT>> clonedThread =
                    new TLinkedList<>();
            for (TLinkableWrapper<ELEMENT> element : oneThread.element) {
                clonedThread.add(new TLinkableWrapper<ELEMENT>(element.element));
            }
            clone.add(new TLinkableWrapper<>(clonedThread));
        }
        return new ThreadIndexToElementList(clone, elementCount);
    }

    public void add(ELEMENT element) {
        elementCount++;
        while(threadList.size() <= element.threadIndex()) {
            threadList.add(new TLinkableWrapper(new TLinkedList()));
        }
        threadList.get(element.threadIndex()).element.add(new TLinkableWrapper(element));
    }

    public int getPositionAtThreadIndex(int threadIndex) {
        if( threadList.size() <= threadIndex ) {
            return 0;
        }
        return  threadList.get(threadIndex).element.size();
    }

    public int maxThreadIndex() {
        return threadList.size() -1;
    }

    public int elementCount() {
        return elementCount;
    }

    public ELEMENT getAtIndex(int index) {
        return threadList.get(index).element.get(0).element;
    }

    public ELEMENT getAndRemoveAtIndex(int index) {
        return threadList.get(index).element.removeFirst().element;
    }

    public boolean isEmptyAtIndex(int index) {
        return threadList.get(index).element.isEmpty();
    }

    public boolean isEmpty() {
        for(TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>> list : threadList) {
            if(! list.element.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> iterator() {
        return threadList.iterator();
    }
    Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> iteratorAt(int threadIndex) {
        return threadList.listIterator(threadIndex);
    }

    private final class ThreadIndexIterator implements Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> {
        private int currentIndex;
        public ThreadIndexIterator(int currentIndex) {
            this.currentIndex = currentIndex;
        }
        @Override
        public boolean hasNext() {
            return currentIndex < threadList.size();
        }
        @Override
        public TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>> next() {
            TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>> result = threadList.get(currentIndex);
            currentIndex++;
            return result;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
