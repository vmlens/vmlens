package com.vmlens.api;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.vmlens.api.AllInterleavingsBuilder.*;

/**
 * 
 * The class AllInterleavings is the main entry point to write deterministic unit tests for multithreaded Java.
 * To make a test deterministic, surround the tested code with a while loop to iterate over all thread interleavings.
 * <p>
 * For example:
 * <pre>{@code 
 * try (AllInterleavings allInterleavings =
    new AllInterleavings("ConcurrencyTestUniqueId");) {
    while (allInterleavings.hasNext()) {
        firstId  = 0L;
        secondId = 0L;
        UniqueId uniqueId = new UniqueId();
        Thread first = new Thread(() -> {
            firstId = uniqueId.nextId();
        });
        first.start();
        secondId = uniqueId.nextId();
        first.join();
        assertTrue(firstId != secondId);
    }
  }
 * }</pre>
 *
 *
 * The loop body must be deterministic and safe for repeated
 * execution. In every iteration, all threads must be explicitly started
 * and joined (or otherwise cleanly terminated) to ensure that all possible
 * thread interleavings can be evaluated. Similar all thread pools used should be created
 * and shutdown inside the loop.
 * <p>
 * @see com.vmlens.api.AllInterleavingsBuilder AllInterleavingsBuilder to configure this class
 * @see com.vmlens.api.Runner Runner to run a runnable in a separate thread. Runner takes care of the start and join of the thread
 */
public class AllInterleavings implements AutoCloseable, Iterable<Interleaving>, Iterator<Interleaving> {

	private final static String ERROR_MESSAGE_PART_1 = "The vmlens java agent is not configured.";
	private final static String ERROR_MESSAGE_PART_2 = "See https://vmlens.com/docs/ for configuring the vmlens java agent.";
	private final boolean throwExceptionWhenNoAgent;

	public final int maximumIterations;
	public final int synchronizationActionsLoopThreshold;
	public final int unsynchronizedOperationsLoopThreshold;
	public final int reportAsSummaryThreshold;
	public final boolean traceInterleaveActions;
	private int index;

	public final List<AbstractMap.SimpleImmutableEntry<String,String>> intentionalDataRaces;

	/**
     * The name shown in the report.
	 * 
	 */
	public final String name;

	/**
	 * Creates a new AllInterleaving instance.
	 *
     * @param name The name shown in the report.
	 */
	public AllInterleavings(String name) {
		this(name,false);
	}


	public AllInterleavings(String name, boolean throwExceptionWhenNoAgent) {
		this(name,
				throwExceptionWhenNoAgent,
				MAXIMUM_ITERATIONS ,
				500 ,
				5000,
				REPORT_AS_SUMMARY_THRESHOLD,
				false,
				new LinkedList<>());
	}


	AllInterleavings(String name,
                     boolean throwExceptionWhenNoAgent,
                     int maximumIterations,
                     int synchronizationActionsLoopThreshold,
                     int unsynchronizedOperationsLoopThreshold,
                     int reportAsSummaryThreshold,
					 boolean traceInterleaveActions,
                     List<AbstractMap.SimpleImmutableEntry<String, String>> intentionalDataRaces) {
        this.name = name;
		this.throwExceptionWhenNoAgent = throwExceptionWhenNoAgent;
        this.maximumIterations = maximumIterations;
        this.synchronizationActionsLoopThreshold = synchronizationActionsLoopThreshold;
        this.unsynchronizedOperationsLoopThreshold = unsynchronizedOperationsLoopThreshold;
        this.reportAsSummaryThreshold = reportAsSummaryThreshold;
        this.traceInterleaveActions = traceInterleaveActions;
        this.intentionalDataRaces = intentionalDataRaces;
    }

	/**
	 * Tells VMLens to not trace the statements called inside the runnable.
	 * Useful if you want to collect or check results from messages.
	 * Using this method you avoid that VMLens generates interleavings for the those checks
	 * <p>
	 * Example:
	 * <pre>{@code
		String result = growableArrayBlockingQueue.poll();
		if (result != null) {
		doNotTrace(() -> readValues.add(result));
		}
	 * }</pre>
	 * The function call readValues.add does not get traced and therefore does not lead to
	 * additional thread interleavings.
	 *
	 * @param runnable
	 */

	public static void doNotTrace(Runnable runnable) {
		startDoNotTrace();
		try{
			runnable.run();
		}
		finally {
			stopDoNotTrace();
		}
		
	}


	/**
	 * Return true if there are still thread interleaving to be executed and select the next thread interleaving,
	 * otherwise returns false.
	 * 
	 * @return true if  there are still thread interleaving to be executed otherwise false.
	 */
	public  boolean hasNext() {
		return hasNext(this);
	}

	@Override
	public Interleaving next() {
		Interleaving interleaving =  new Interleaving(index);
		index++;
		return interleaving;
	}

	/**
	 * closes this instance
	 */
	public void close()  {
		close(this);
	}

	@Override
	public Iterator<Interleaving> iterator() {
		return this;
	}
	
	public void automaticTestSuccess(int id, String className) {
		automaticTestSuccess(this,id,className);
	}

	public void automaticTestMethod(int id, int automaticTestMethodId, int automaticTestType) {
		automaticTestMethod(this,id,automaticTestMethodId, automaticTestType);
	}

	private static void startDoNotTrace() {
	}

	private static void stopDoNotTrace() {
	}

	private boolean hasNext(Object object) {
		if(throwExceptionWhenNoAgent) {
			throw new RuntimeException(ERROR_MESSAGE_PART_1 + " " + ERROR_MESSAGE_PART_2);
		}
		System.err.println(ERROR_MESSAGE_PART_1);
		System.err.println(ERROR_MESSAGE_PART_2);
		return false;
	}

	private void close(Object obj) {
		
	}

	private void automaticTestSuccess(Object obj, int id, String className) {

	}


	private void automaticTestMethod(Object obj, int id,  int automaticTestMethodId , int automaticTestType) {

	}

	
	
	
	
}
