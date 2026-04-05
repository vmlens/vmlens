package com.vmlens.api.testbuilder.internal.callkey;

/**
 * A unique identifier for each call added to the testbuilder.
 * Used to filter double tes cases when distribute the calls to the thread
 * For example ABA and BAA are the same when executed by three threads.
 *
 * Also used to generate the label of the ConcurrentCall
 */
public interface CallKey {

    String getLabel();
    int automaticTestType();

}
