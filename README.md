# Priority-Queue
Forthisassignment,youwillwritetwoimplementationsofaPriority Queue.Forthis ADT, removal operations always return the object in the queue of highest priority that has been in the queue the longest. That is, no object of a given priority is ever removed as long as the queue contains one or more object of a higher priority. Within a given priority FIFO order must be preserved.
Your implementations will be:
1. OrderedArray 2. UnorderedArray
Both implementations must have identical behavior, and must implement the PriorityQueue interface (provided). The implementations must have two constructors, a default constructor with no arguments that uses the DEFAULT_MAX_CAPACITY constant from the PriorityQueue interface, and a constructor that takes a single integer parameter that represents the maximum capacity of the priority queue.
