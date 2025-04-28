package com.vmlens.preanalyzed.model

/**
 * The memory effects for accesses and updates of atomics generally follow the rules for volatiles, as stated in The Java Language Specification (17.4 Memory Model):
 *
 * get has the memory effects of reading a volatile variable.
 * set has the memory effects of writing (assigning) a volatile variable.
 * lazySet has the memory effects of writing (assigning) a volatile variable except that it permits reorderings with subsequent (but not previous) memory actions that do not themselves impose reordering constraints with ordinary non-volatile writes. Among other usage contexts, lazySet may apply when nulling out, for the sake of garbage collection, a reference that is never accessed again.
 * weakCompareAndSet atomically reads and conditionally writes a variable but does not create any happens-before orderings, so provides no guarantees with respect to previous or subsequent reads and writes of any variables other than the target of the weakCompareAndSet.
 * compareAndSet and all other read-and-update operations such as getAndIncrement have the memory effects of both reading and writing volatile variables. 
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html
 * 
 */


sealed trait AtomicNonBlockingMethodType {

}

case class Read() extends AtomicNonBlockingMethodType;

case class Write() extends AtomicNonBlockingMethodType;

case class ReadWrite() extends AtomicNonBlockingMethodType;

case class NotYetImplemented() extends AtomicNonBlockingMethodType;

