# java.util.concurrent 源码阅读

atomic包
1. ~~AtomicBoolean~~
2. ~~AtomicInteger~~
3. ~~AtomicIntegerArray~~
4. AtomicIntegerFieldUpdater
5. ~~AtomicLong~~
6. ~~AtomicLongArray~~
7. AtomicLongFieldUpdater
8. ~~AtomicMarkableReference~~
9. ~~AtomicReference~~
10. ~~AtomicReferenceArray~~
11. AtomicReferenceFieldUpdater
12. ~~AtomicStampedReference~~
13. DoubleAccumulator
14. DoubleAdder
15. LongAccumulator
16. LongAdder
17. Striped64

---
locks包
1. ~~AbstractOwnableSynchronizer~~
2. AbstractQueuedLongSynchronizer
3. ~~AbstractQueuedSynchronizer~~
   1. 东西太多，还是看不太懂
   2. 力求做到
      1. 了解整体结构，全部方法源码大概过一遍；能应付面试 / 对平时开发设计有所裨益
      2. 在使用其各种实现时，能熟练使用
4. ~~Condition~~
5. ~~Lock~~
6. ~~LockSupport~~
7. ~~ReadWriteLock~~
8. ~~ReentrantLock~~
9. ReentrantReadWriteLock
10. StampedLock

---
1. ~~AbstractExecutorService~~
2. ~~ArrayBlockingQueue~~
   1. 内部的一些复杂实现没看，比如迭代器
3. ~~BlockingDeque~~
4. ~~BlockingQueue~~
5. ~~BrokenBarrierException~~
6. ~~Callable~~
7. CancellationException
8. CompletableFuture
9. CompletionException
10. ~~CompletionService~~
11. CompletionStage
12. ~~ConcurrentHashMap~~
    1. 没心情看过去，6k行。。。，大概看了下头部的那部分文档
13. ConcurrentLinkedDeque
14. ~~ConcurrentLinkedQueue~~
    1. 头尾节点默认创建都是带null元素的node（因此存在特殊操作，首次新增/移除并不会立马）
    2. 自旋+CAS
15. ConcurrentMap
16. ConcurrentNavigableMap
17. ConcurrentSkipListMap
18. ConcurrentSkipListSet
19. ~~CopyOnWriteArrayList~~
20. ~~CopyOnWriteArraySet~~
    1. 用 ReentrantLock在修改数据集上内部的Object数据总是创建新的数组（相比与常用的ArrayList，它会在适当的时间扩容）
21. ~~CountDownLatch~~
22. CountedCompleter
23. ~~CyclicBarrier~~
24. Delayed
25. DelayQueue
26. Exchanger
27. ExecutionException
28. ~~Executor~~
29. ~~ExecutorCompletionService~~
30. ~~Executors~~
31. ~~ExecutorService~~
32. ~~Flow（非jdk8）~~
33. ForkJoinPool
34. ForkJoinTask
35. ForkJoinWorkerThread
36. ~~Future~~
37. ~~FutureTask~~
38. ~~Helpers（非jdk8）~~
39. ~~LinkedBlockingDeque~~
40. LinkedBlockingQueue
41. LinkedTransferQueue
42. Phaser
43. PriorityBlockingQueue
44. RecursiveAction
45. RecursiveTask
46. RejectedExecutionException
47. RejectedExecutionHandler
48. ~~RunnableFuture~~
49. RunnableScheduledFuture
50. ScheduledExecutorService
51. ScheduledFuture
52. ScheduledThreadPoolExecutor
53. ~~Semaphore~~
54. ~~SubmissionPublisher（非jdk8）~~
55. SynchronousQueue
56. ~~ThreadFactory~~
57. ThreadLocalRandom
58. ~~ThreadPoolExecutor~~
    1. 看情况招聘工人，让工人一直自觉去领任务，在某些情况下需要辞退工人
59. ~~TimeoutException~~
60. ~~TimeUnit~~
61. TransferQueue