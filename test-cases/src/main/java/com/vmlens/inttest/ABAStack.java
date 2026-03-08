package com.vmlens.inttest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

public class ABAStack {


    private final ThreadLocal<Queue<Node>> recycledNodes = new ThreadLocal<>()  {
        @Override
        protected Queue<Node> initialValue() {
            return new LinkedList<>();
        }
    };
    private AtomicReference<Node> head = new AtomicReference<>();

    public void push(int value) {
        Node newNode  = recycledNodes.get().poll();
        if(newNode == null) {
            newNode = new Node(value);
        }
        Node oldHead;
        do {
            oldHead = head.get();
            newNode.next = oldHead;
        } while (!head.compareAndSet(oldHead, newNode));
    }

    public Node pop() {
        Node oldHead;
        Node newHead;
        do {
            oldHead = head.get();
            if (oldHead == null) return null;
            recycledNodes.get().add(oldHead);
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        return oldHead;
    }

}
