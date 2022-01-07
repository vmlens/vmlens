package com.vmlens.trace.agent.bootstrap.interleave.domain.syncAction;

/**
 * Represents the begin of a thread or task. A pseudo element used to parallelize
 * multiple started threads during a test run.
 * Is the first sync action of a thread for newly started threads.
 * <p>Order:
 * <ul>
 *     <li>Alternate order with each other ThreadBegin</li>
 *     <li>Fixed order with ThreadStart</li>
 * </ul>
 */

public class ThreadBegin {
}
