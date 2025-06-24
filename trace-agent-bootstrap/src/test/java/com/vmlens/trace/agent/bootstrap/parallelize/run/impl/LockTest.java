package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

/**
 * Lock exit is special:
 *      beforeLockExit(Event)
 *              Send Event
 *      after(thread index)
 *              next step in actual run
 *              without send event
 *
 *
 *  to solve the follwoing scenario
 *
 *  monitor enter
 *                  try monitor enter (blocked)
 *  monitor exit allowed
 *          execute both add to actual run + send event
 *  after(thread index)
 */

public class LockTest {
}
