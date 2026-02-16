# Problem

Iterator needs to be reset if we recreate the order tree because of a cycle

# Solution

AlternatingOrderContainerIterator -> OrderListIterator -> MixedRadixIterator
AlternatingOrderContainerIterator -> CalculatedRunFactory


CalculatedRunFactory cycle detection, calls reset on OrderListIterator