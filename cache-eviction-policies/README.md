This module based on [Grokking course about software design](https://www.educative.io/courses/grokking-the-system-design-interview/3j6NnJrpp5p)

Most common cache eviction policies:
## First In First Out (FIFO): 
The cache evicts the first block accessed first without any regard to how often or how many times it was accessed before.
![](src/main/resources/FIFO.png)

## Last In First Out (LIFO): 
The cache evicts the block accessed most recently first without any regard to how often or how many times it was accessed before.

## Least Recently Used (LRU): 
Discards the least recently used items first.
![https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU)](src/main/resources/LRU.png)

## Most Recently Used (MRU): 
Discards, in contrast to LRU, the most recently used items first.
![https://en.wikipedia.org/wiki/Cache_replacement_policies#/media/File:Mruexample.png](src/main/resources/MRU.png)

## Least Frequently Used (LFU): 
Counts how often an item is needed. Those that are used least often are discarded first.
![](src/main/resources/LFU.png)

References
[Interview Cake](https://www.interviewcake.com/concept/java/lru-cache)