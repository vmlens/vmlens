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
        this(new TLinkedList<>(), 0);
    }

    public ThreadIndexToElementList<ELEMENT> safeClone() {
        TLinkedList<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> clone = new
                TLinkedList<>();
        for (TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>> oneThread : threadList) {
            TLinkedList<TLinkableWrapper<ELEMENT>> clonedThread =
                    new TLinkedList<>();
            for (TLinkableWrapper<ELEMENT> element : oneThread.element()) {
                clonedThread.add(new TLinkableWrapper<ELEMENT>(element.element()));
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
        threadList.get(element.threadIndex()).element().add(new TLinkableWrapper(element));
    }

    public int getPositionAtThreadIndex(int threadIndex) {
        if( threadList.size() <= threadIndex ) {
            return 0;
        }
        return threadList.get(threadIndex).element().size();
    }

    public int maxThreadIndex() {
        return threadList.size() -1;
    }

    public int elementCount() {
        return elementCount;
    }

    public ELEMENT getAtIndex(int index) {
        return threadList.get(index).element().get(0).element();
    }

    // For Tests
    public ELEMENT getElementAtIndex(int index, int n) {
        return threadList.get(index).element().get(n).element();
    }


    public ELEMENT getAndRemoveAtIndex(int index) {
        return threadList.get(index).element().removeFirst().element();
    }

    public void popIfNotEmpty(int threadIndex) {
        if( threadList.size() <= threadIndex ) {
            return;
        }
        TLinkedList<TLinkableWrapper<ELEMENT>> list = threadList.get(threadIndex).element();
        if(list.isEmpty()) {
            return;
        }
        list.removeFirst();
    }

    public boolean isEmptyAtIndex(int index) {
        return threadList.get(index).element().isEmpty();
    }

    public boolean isEmpty() {
        for(TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>> list : threadList) {
            if (!list.element().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> iterator() {
        return threadList.iterator();
    }

    public Iterator<ELEMENT> elementIterator() {
        return new ElementIterator<>( iterator());
    }

    public TLinkedList<TLinkableWrapper<ELEMENT>> listAt(int threadIndex) {
        if(threadIndex >= threadList.size()) {
            return null;
        }
        return threadList.get(threadIndex).element();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadIndexToElementList<?> that = (ThreadIndexToElementList<?>) o;

        if (elementCount != that.elementCount) return false;
        return threadList.equals(that.threadList);
    }

    @Override
    public int hashCode() {
        int result = threadList.hashCode();
        result = 31 * result + elementCount;
        return result;
    }

    Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ELEMENT>>>> iteratorStartingAt(int threadIndex) {
        return threadList.listIterator(threadIndex);
    }

}
