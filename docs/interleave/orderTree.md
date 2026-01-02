# Problem

we need to map a tree to a list.
For deadlocks we have deadlock yes, no -> lock alternatives or deadlock alternatives

the selection which node gets selected depends on a single true,false list

# Solution 

we have alternatives consisting of two alternatives
and choices which have two alternatives which again can have two alternatives
later on we might have two choices (e.g. 4 alternatives)


only choices and alternatives outside of the choice are list elements
if we have a choice both alternatives need the same length

# Structure of builder

TreeBuilderNode -> { choice , either } : methods

# Structure of tree

ListElement <- { ListElementChoice , ListElementEither }
ListElement is only used for building

OrderTreeNode <- { ChoiceAlternative , ListElement }
processing of the order tree happens based on OrderTreeNode

# build process

NodeBuilder.build() : ListElement
