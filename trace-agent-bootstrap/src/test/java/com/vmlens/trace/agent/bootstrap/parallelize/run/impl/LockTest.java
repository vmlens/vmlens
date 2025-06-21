package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

/**
 * Lock exit is special:
 *      beforeLockExit(Event)
 *              Send Event
 *      after(thread index)
 *              next step in actual run
 *              without send event
 *
 */

public class LockTest {
}
