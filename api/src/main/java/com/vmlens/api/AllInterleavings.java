package com.vmlens.api;

/**
 * 
 * The class AllInterleavings let you test all thread interleavings for your test. Enclose your test in a while loop to iterate through all thread interleavings like in the following example:
 * 
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
 * 
 * }</pre>
 *
 */

public class AllInterleavings implements AutoCloseable {

	/**
     * The name shown in the interleave reportbuilder.
	 * 
	 */
	public final String name;
	
	
	/**
	 * see {@link AllInterleavingsBuilder#showStatementsWhenSingleThreaded} for a description and how to set this value.
	 * 
	 */
	public final boolean showStatementsWhenSingleThreaded;
	
	
	/**
	 * see {@link AllInterleavingsBuilder#showStatementsInExecutor} for a description and how to set this value.
	 * 
	 */
	public final boolean showStatementsInExecutor;
	
	
	/**
	 * see {@link AllInterleavingsBuilder#showNonVolatileSharedMemoryAccess} for a description and how to set this value.
	 * 
	 */
	public final boolean showNonVolatileSharedMemoryAccess;
	
	/**
	 * see {@link AllInterleavingsBuilder#maximumRuns} for a description and how to set this value.
	 * 
	 */
	public final int maximumRuns;
	

	/**
	 * see {@link AllInterleavingsBuilder#maximumSynchronizationActionsPerThread} for a description and how to set this value.
	 * 
	 */
	public final int maximumSynchronizationActionsPerThread;
	

	/**
	 * see {@link AllInterleavingsBuilder#removeAtomicAnnotation} for a description and how to set this value.
	 * 
	 */
	public final Class[] removeAtomicAnnotationFromClassArray;
	
	
	/**
	 * Creates a new AllInterleavings instance. To set non default values use {@link AllInterleavingsBuilder}
	 * 
	 *
     * @param name The name shown in the interleave reportbuilder.
	 */
	
	public AllInterleavings(String name) {
		this( name , false , false , false , 5000 , 2000 , new Class[0] );
	}




	
	

	AllInterleavings(String name, boolean showStatementsWhenSingleThreaded, boolean showStatementsInExecutor,
			boolean showNonVolatileSharedMemoryAccess, int maximum_run_count, int maximum_operation_per_thread_count,Class[] testClassArray) {
		super();
		this.name = name;
		this.showStatementsWhenSingleThreaded = showStatementsWhenSingleThreaded;
		this.showStatementsInExecutor = showStatementsInExecutor;
		this.showNonVolatileSharedMemoryAccess = showNonVolatileSharedMemoryAccess;
		this.maximumRuns = maximum_run_count;
		this.maximumSynchronizationActionsPerThread = maximum_operation_per_thread_count;
		this.removeAtomicAnnotationFromClassArray = testClassArray;
	}



	/**
	 * 
	 * Create a new {@link AllInterleavingsBuilder}.
	 *
     * @param name The name shown in the interleave reportbuilder.
	 * @return A new {@link AllInterleavingsBuilder}
	 */
	
	public static AllInterleavingsBuilder builder(String name)
	{
		return new AllInterleavingsBuilder(name);
	}


	/**
	 * Return true if there are still thread interleavings to be executed and select the next thread interleaving.
	 * Otherwise returns false.
	 * 
	 * @return true if  there are still thread interleavings to be executed otherwise false.
	 */

	public  boolean hasNext()
	{
		return hasNext(this);
	}

	
	/**
	 * closes this instance
	 * 
	 */
	
	public void close()  {
		close(this);
	}
	
	
	
	private static boolean hasNext(Object object)
	{
		return false;
	}
	
	
	private static void close(Object obj)
	{
		
	}
	
	
	
	
	
}
