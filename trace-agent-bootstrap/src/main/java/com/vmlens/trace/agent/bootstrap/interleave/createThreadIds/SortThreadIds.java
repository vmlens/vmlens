package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction;
import static com.vmlens.trace.agent.bootstrap.interleave.syncAction.TLinkableForSyncAction.linked;
import gnu.trove.list.linked.TLinkedList;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.PartialOrder.LEFT_COMES_BEFORE;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.PartialOrder.LEFT_COMES_AFTER;
import static com.vmlens.trace.agent.bootstrap.interleave.createThreadIds.PartialOrder.UNDEFINED;

public class SortThreadIds {
	
	public TLinkableForSyncAction[] sort(TLinkableForLeftBeforeRight[] order, TLinkableForSyncAction[] syncActions) {
		return sort(new PartialOrder(order),syncActions);
	}

	/**
	 * 
	 * fill first thread sort according to partial order return the sorted array or
	 * null if sort contains cycles.
	 * 
	 * 
	 * algorithm: start wirh first thread for next thread insert beginning at the
	 * first element of the list. at each insert if LEFT_COMES_AFTER check if
	 * prevoius elements with different thread index all are before the new element
	 * 
	 * index -> element in array addAt -> start of the list, or element after
	 * current added
	 * 
	 */

	public TLinkableForSyncAction[] sort(PartialOrder partialOrder, TLinkableForSyncAction[] syncActions) {

		int currentThreadIndex = 0;
		int arrayIndex = 0;
		TLinkedList<TLinkableForSyncAction> result = new TLinkedList<TLinkableForSyncAction>();

		while (currentThreadIndex == 0) {

			result.add(linked(syncActions[arrayIndex].syncAction));

			arrayIndex++;
			currentThreadIndex = syncActions[arrayIndex].syncAction.threadIndex;

		}

		TLinkableForSyncAction addAt = result.getFirst();

		for (int i = arrayIndex; i < syncActions.length; i++) {

			TLinkableForSyncAction toBeAdded = linked(syncActions[i].syncAction);

			if (toBeAdded.syncAction.threadIndex != currentThreadIndex) {
				addAt = result.getFirst();
				currentThreadIndex = toBeAdded.syncAction.threadIndex;
			}

			addAt = addToList(toBeAdded, result, addAt, partialOrder);

		}

		TLinkableForSyncAction[] array = result.toUnlinkedArray(new TLinkableForSyncAction[0]);

		// check for loops

		if (containsLoop(array, partialOrder)) {
			return null;
		}

		return array;
	}

	private boolean containsLoop(TLinkableForSyncAction[] array, PartialOrder partialOrder) {

		for (int i = 0; i < array.length; i++) {

			if (containsWrongOrder(i, array, partialOrder)) {
				return true;
			}

		}

		return false;
	}

	private boolean containsWrongOrder(int forPosition, TLinkableForSyncAction[] array, PartialOrder partialOrder) {
		for (int j = forPosition; j < array.length; j++) {

			if (partialOrder.isLeftBeforeRight(array[j].syncAction, array[forPosition].syncAction)) {
				return true;
			}

		}

		return false;
	}

	private TLinkableForSyncAction addToList(TLinkableForSyncAction toBeAdded,
			TLinkedList<TLinkableForSyncAction> result, TLinkableForSyncAction start, PartialOrder partialOrder) {
		TLinkableForSyncAction addAt = start;

		while (true) {
			switch (partialOrder.getSort(addAt, toBeAdded)) {

			case LEFT_COMES_BEFORE: {

				addAt = result.getNext(addAt);

				if (addAt == null) {
					result.addLast(toBeAdded);
					return toBeAdded;
				}

				break;
			}

			case LEFT_COMES_AFTER: {

				result.addBefore(addAt, toBeAdded);

				return addAt;
			}

			case UNDEFINED: {

				addAt = result.getNext(addAt);

				if (addAt == null) {
					result.addLast(toBeAdded);
					return toBeAdded;
				}

				break;
			}

			}
		}
	}

}
