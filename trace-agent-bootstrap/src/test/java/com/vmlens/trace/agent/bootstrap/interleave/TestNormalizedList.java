package com.vmlens.trace.agent.bootstrap.interleave;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.actualAccess.LockAccess;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockEnter;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockExit;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.NormalizedAccess;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.NormalizedThread;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList;
import com.vmlens.trace.agent.bootstrap.interleave.operation.LockEnterOrExit;
import com.vmlens.trace.agent.bootstrap.interleave.operation.MonitorExit;
import com.vmlens.trace.agent.bootstrap.interleave.operation.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;

import org.apache.commons.lang3.mutable.MutableBoolean;
import gnu.trove.list.linked.TLinkedList;

public class TestNormalizedList {

//	@Test
//	public void oneVolatileFieldsInterleaveFacade() {
//		InterleaveFacade interleaveFacade = new InterleaveFacade();
//
//		interleaveFacade.afterThreadStart(0,1);
//		
//		interleaveFacade.afterOperation(0, new VolatileFieldAccess(1, MemoryAccessType.IS_READ));
//		interleaveFacade.afterOperation(0, new VolatileFieldAccess(1, MemoryAccessType.IS_WRITE));
//
//		interleaveFacade.afterOperation(1, new VolatileFieldAccess(1, MemoryAccessType.IS_READ));
//		interleaveFacade.afterOperation(1, new VolatileFieldAccess(1, MemoryAccessType.IS_WRITE));
//
//		Map<int[], MutableBoolean> expected = new HashMap<int[], MutableBoolean>();
//		expected.put(new int[] { 0, 0, 1, 1 }, new MutableBoolean(false));
//		expected.put(new int[] { 1, 1, 0, 0 }, new MutableBoolean(false));
//		expected.put(new int[] { 0, 1, 1, 0 }, new MutableBoolean(false));
//		
//		
//		
//	
//
//		while (interleaveFacade.advance()) {
//
//			CommandList commandList = interleaveFacade.currentCommands;
//
//			for (Entry<int[], MutableBoolean> entry : expected.entrySet()) {
//				if (Arrays.equals(entry.getKey(), commandList.threadIndex)) {
//					entry.getValue().setTrue();
//				}
//				
//				System.out.println(Arrays.toString(commandList.threadIndex));
//				
//			}
//			
//			interleaveFacade.afterOperation(0, new VolatileFieldAccess(1, MemoryAccessType.IS_READ));
//			interleaveFacade.afterOperation(0, new VolatileFieldAccess(1, MemoryAccessType.IS_WRITE));
//
//			interleaveFacade.afterOperation(1, new VolatileFieldAccess(1, MemoryAccessType.IS_READ));
//			interleaveFacade.afterOperation(1, new VolatileFieldAccess(1, MemoryAccessType.IS_WRITE));
//
//		}
//
//		for (Entry<int[], MutableBoolean> entry : expected.entrySet()) {
//			assertTrue(entry.getValue().booleanValue());
//		}
//
//	}

	@Test
	public void oneVolatileFields() {
		TLinkedList<LockAccess> lockList = new TLinkedList<LockAccess>();
		NormalizedThread[] threadList = new NormalizedThread[2];

		TLinkedList<NormalizedAccess> operations = new TLinkedList<NormalizedAccess>();

		operations.add(new NormalizedAccess(new VolatileFieldAccess(1, MemoryAccessType.IS_READ)));
		operations.add(new NormalizedAccess(new VolatileFieldAccess(1, MemoryAccessType.IS_WRITE)));

		threadList[0] = new NormalizedThread(operations);
		threadList[1] = new NormalizedThread(operations);

		NormalizedList normalizedList = NormalizedList.create(threadList, lockList);

		RelationList relationList = normalizedList.relationMap.create();

		Map<int[], MutableBoolean> expected = new HashMap<int[], MutableBoolean>();
		expected.put(new int[] { 0, 0, 1, 1 }, new MutableBoolean(false));
		expected.put(new int[] { 1, 1, 0, 0 }, new MutableBoolean(false));
		expected.put(new int[] { 0, 1, 1, 0 }, new MutableBoolean(false));

		while (!relationList.isDone()) {
			TLinkedList<CommandList> commands = relationList.create();
			Iterator<CommandList> iter = commands.iterator();

			while (iter.hasNext()) {
				CommandList commandList = iter.next();

				for (Entry<int[], MutableBoolean> entry : expected.entrySet()) {
					if (Arrays.equals(entry.getKey(), commandList.threadIndex)) {
						entry.getValue().setTrue();
					}
				}

			}

		}

		for (Entry<int[], MutableBoolean> entry : expected.entrySet()) {
			assertTrue(entry.getValue().booleanValue());
		}

	}

}
