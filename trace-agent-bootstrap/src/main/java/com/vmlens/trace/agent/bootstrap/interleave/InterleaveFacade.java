package com.vmlens.trace.agent.bootstrap.interleave;

import java.util.Iterator;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.interleave.operation.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.interleave.operation.ThreadStarted;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import gnu.trove.list.linked.TLinkedList;

public class InterleaveFacade {

	
	private final TLinkedList<TLinkableWrapper<NormalizedList>>  allNormalizedLists =  new TLinkedList<TLinkableWrapper<NormalizedList>>();
	private final TLinkedList<TLinkableWrapper<NormalizedList>>  openNormalizedLists =  new TLinkedList<TLinkableWrapper<NormalizedList>>();
	
	// pro run:
	private  ActualList actualList = new ActualList();
	private  TLinkedList<CommandList> currentNormalizedList = new TLinkedList<CommandList>();
	public  CommandList currentCommands = new CommandList();
	private RelationList current = null;
	
	private int startedThreadCount = 0; 
	
	public boolean isSecondRun = false;
	
	
	
	public void lockOperation(int threadIndex, LockOperation operation) {
		
	//	if(startedThreadCount > 0)
		{
			actualList.addLock(threadIndex , operation);
			
		}
	}

	
	
	
	
	public void afterOperation(int threadIndex, OperationTyp actualAccess)
	{
		
		if(startedThreadCount > 0)
		{
			
			actualList.add(threadIndex , actualAccess);
			currentCommands.after(threadIndex , actualAccess);
		}

	
		if(actualAccess instanceof ThreadJoin)
		{
			startedThreadCount--;
		}
		
		
	}

	
	
	
	
	

	public void incrementCurrentIndex() {
		currentCommands.incrementCurrentIndex();
	}


	
	public void afterThreadStart(int startingIndex, int startedIndex) 
	{
		startedThreadCount++;
		
		afterOperation(startingIndex , new ThreadStarted( startingIndex ,  startedIndex)  );
	
	
		
	}
	
	
	
	public int activeThreadIndex()
	{
		return currentCommands.activeThreadIndex();
	}
	
	
	
	public void newThreadBegan( int startedIndex)
	{
		actualList.newThreadBegan(startedIndex);
	}
	
	
	public  void secondRun()
	{
		
		isSecondRun = true;
		startedThreadCount = 0;
		
		

		NormalizedList newList = actualList.normalize();
		 actualList = new ActualList();
		
		boolean alreadyThere = false;
		
		Iterator<TLinkableWrapper<NormalizedList>> it = allNormalizedLists.iterator();
		
		
		while( it.hasNext() )
		{
			NormalizedList current = it.next().element;
			
			if( current.isSame(newList) )
			{
				alreadyThere = true;
				break;
			}
			
		}
		
		if( !  alreadyThere)
		{
			if(ParallizeFacade.ENABLE_LOGGING ||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING )
			{
				AgentLogCallback.log( "new list added ");
			}
			
			
			allNormalizedLists.add(new TLinkableWrapper<NormalizedList>(newList));
			openNormalizedLists.add(new TLinkableWrapper<NormalizedList>(newList));
		}
		
		
		
	}
	
	
	/**
	 * 
	 * aus actual list neue normalized list erzeugen und mit allNormalizedLists vergleichen
	 *  		-> wenn nicht vorhanden zu openNormalizedLists hinzufügen
	 *  
	 * wenn currentNormalizedList empty ist element aus openNormalizedLists nehmen und erzeugen
	 * 
	 * 
	 * @return
	 */
	
	
	public boolean advance()
	{
		isSecondRun = false;
		startedThreadCount = 0;
		
		if(ParallizeFacade.ENABLE_LOGGING)
		{
			AgentLogCallback.log( "advance ");
		}
		
		
		NormalizedList newList = actualList.normalize();
		 actualList = new ActualList();
		
		boolean alreadyThere = false;
		
		Iterator<TLinkableWrapper<NormalizedList>> it = allNormalizedLists.iterator();
		
		
		while( it.hasNext() )
		{
			NormalizedList current = it.next().element;
			
			if( current.isSame(newList) )
			{
				alreadyThere = true;
				break;
			}
			
		}
		
		if( !  alreadyThere)
		{
			if(ParallizeFacade.ENABLE_LOGGING ||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING )
			{
				AgentLogCallback.log( "new list added ");
			}
			
			
			allNormalizedLists.add(new TLinkableWrapper<NormalizedList>(newList));
			openNormalizedLists.add(new TLinkableWrapper<NormalizedList>(newList));
		}
		
		
		while( ! openNormalizedLists.isEmpty()  ||  ! currentNormalizedList.isEmpty() || current != null )
		{
			if(  ! currentNormalizedList.isEmpty()  )
			{
				
			
				
				currentCommands = currentNormalizedList.removeFirst();
				
				if(currentCommands == null)
				{
						AgentLogCallback.logError( "currentCommands == null");
					
				}
				

				if(ParallizeFacade.ENABLE_LOGGING)
				{
					AgentLogCallback.log( "currentCommands " + currentCommands);
				}
			
				
				return true;

			}
			
			if( current != null )
			{
				if(!  current.isDone() )
				{
					currentNormalizedList = current.create();
				}
				else
				{
					current = null;
				}
			}
			
			
			if( ! openNormalizedLists.isEmpty()  && current == null)
			{
				current  = openNormalizedLists.removeFirst().element.relationMap.create();	
				
				
				
				currentNormalizedList = current.create();
							
				
			}
			
			
			if( current != null )
			{
				if( current.isDone() )
				{
					current = null;
				}
			}
			
			
		}
		
		
		
		
		
		
		
		return false;
	}





//	public void onLockEnterExit(int index) {
//		
//		if(startedThreadCount > 0)
//		{
//			actualList.onLockEnterExit(index);
//		}
//		
//	}


	



	
	
}
