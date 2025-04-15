package com.vmlens.api;

/**
 * 
 * Use this builder to build AllInterleaving instances with a non default configuration. Example:
 * <pre>{@code 
try (AllInterleaving testUpdate = AllInterleaving.builder("TestShowSharedMemory").showStatementsWhenSingleThreaded().showNonVolatileSharedMemoryAccess().build();)  {
	while( testUpdate.hasNext()  ) {   
			
	}
}
 * }</pre>
 * 
 * 
 * 
 * 
 */


public class AllInterleavingBuilder {

	private  final String name;
	private  boolean showStatementsWhenSingleThreaded;
	private  boolean showStatementsInExecutor;
	private  boolean showNonVolatileSharedMemoryAccess;
	
	private  int maximum_run_count = 5000;
	private  int maximum_operation_per_thread_count = 2000;
	private  Class[] testClassArray = new Class[0];
	
	
	AllInterleavingBuilder(String name) {
		super();
		this.name = name;
	}
	
	
	/**
	 * 
	 * Builds a new instance of the class AllInterleavings with the given properties.
	 * 
	 * @return new instance of the class AllInterleavings with the given properties.
	 */
	
	public AllInterleaving build()
	{
		return new AllInterleaving( name,  showStatementsWhenSingleThreaded,  showStatementsInExecutor,
				showNonVolatileSharedMemoryAccess,  maximum_run_count,  maximum_operation_per_thread_count,testClassArray);
	}
	
	
	/**
	 * 
	 * Removes the atomic annotation from the given class.
	 * 
	 * @param cl The class for which the annotation should be removed.
	 * @return This builder  
	 * 
	 */
	public AllInterleavingBuilder removeAtomicAnnotation(Class cl)
	{
		 Class[] n = new Class[testClassArray.length + 1];
		 for(int i = 0 ; i < testClassArray.length ; i++)
		 {
			 n[i] = testClassArray[i];
		 }
		 
		 n[testClassArray.length] = cl;
		 testClassArray = n; 
		
		return this;
	}
	
	/**
     * Show statements in the control reportbuilder also when only one thread is currently running.
	 * 
	 * Statements are synchronization actions and when {@link #showNonVolatileSharedMemoryAccess} was called also non volatile memory access. 
	 *
	 *
	 * @return This builder
	 */
	public AllInterleavingBuilder showStatementsWhenSingleThreaded()
	{
		showStatementsWhenSingleThreaded = true;
		return this;
		
	}
	
	/**
     * Show statements in the control reportbuilder when inside one of the methods of classes implementing java.util.concurrent.Executor 
	 * like java.util.concurrent.ThreadPoolExecutor or java.util.concurrent.ForkJoinPool
	 * 
	 * Statements are synchronization actions and when {@link #showNonVolatileSharedMemoryAccess} was called also non volatile memory access. 
	 * 
	 * @return This builder
	 */
	public AllInterleavingBuilder showStatementsInExecutor()
	{
		showStatementsInExecutor = true;
		return this;
		
	}
	
	
	/**
	 *
     * Also show non volatile memory access in the control reportbuilder. 
	 * 
	 * @return This builder
	 */
	public AllInterleavingBuilder showNonVolatileSharedMemoryAccess()
	{
		showNonVolatileSharedMemoryAccess = true;
		return this;
		
	}
	
	/**
	 * 
	 * AllInterleavings terminates when no more interleavings are available or the maximumRuns count is reached. Set the maximum to -1 to only terminate when no more interleavings are available. 
	 * Default value when not set through this method: 5000
	 * 
	 * @param maximum The new maximum run count
	 * @return This builder
	 */
	public AllInterleavingBuilder maximumRuns(int maximum)
	{
		maximum_run_count = maximum;
		return this;
		
	}
	
	/**
	 * If a thread executes more than the given maximum synchronization actions it gets terminated by
	 * an exception. Set the maximum to -1 to never terminate a thread with this mechanism. Default value when not set through this method: 2000
	 * 
	 * 
	 * @param maximum The new maximum synchronization actions per thread.
	 * @return This builder
	 */

	public AllInterleavingBuilder maximumSynchronizationActionsPerThread(int maximum)
	{
		maximum_operation_per_thread_count = maximum;
		return this;
		
	}
	
}
