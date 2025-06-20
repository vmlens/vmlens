package com.vmlens.inttest.interleave.future;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class CompletableFutureTest {

    public void test() {
        Future<String> future = new CompletableFuture<>();
        new HashMap().keySet().parallelStream();
    }


}
