# Problem

we have the following types of elements:
* simple alternatives
* choices which lead to a list of alternatives 
* elements in a cycle

# Solution 

simple alternatives are stored in a list which can be iterated through a permutation iterator iterating using 
integer modulo n

choices and elements in a cycle are modeled as a order list with x alternatives

# Result

## Combined Iterator

We need a combined Iterator which can iterate over the list and over the tree.
Probably best to iterate first over the tree and then the list.

## OrderTreeBuilder -> OrderListBuilder


## OrderList


## OrderTree


we basically have a list of n order alternatives with x different orders





# Tasks

## Building

## Iterating

## Cycle removal





