package com.vmlens.inttest.nonvolatile.innerclass;

import java.util.concurrent.atomic.AtomicReference;

public class ParentInnerClass {

    public abstract static class ParentInner {

        private final transient AtomicReference<Node> head = new AtomicReference();

        public void set() {
            Node current = new Node("test");
            head.set(current);
            current.thomasFieldOne = new Node("next");
        }

        public void get() {
            Node current = head.get();
            if(current!= null) {
                Node next = current.thomasFieldOne;
            }
        }

        protected static class Node {
            final String value;
            Node thomasFieldOne;

            Node(String value) {
                this.value = value;
            }
        }

    }

}
