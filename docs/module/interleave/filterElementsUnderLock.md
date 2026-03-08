# Problem

elements under a lock do not need to uesed for calculation the alternating order.
By also using them for calculation leads to performance problems, see for example akka.


# Solution

use a dominator tree using the locks and monitors. Use only thos elements wich are directly accessable 
from the root node