# java-concurrent
Thread, synchronized, LockSupport, AbstractQueuedSynchronizer, ReentrantLock, Condition, BlockingQueue, ThreadPool
1. JMM -Java Memory Model 
三特性 
synchronized 
volatile Double check 单例模式 
2. Thread 
Object wait() / notify() / notyfyAll() 用wait/notify实现BlockingQueue, 比较juc BlockingQueue 
Thread sleep() / yield() / join() Fork/Join案例 
Interrupt 响应中断案例 
3. CAS -Compare And Swap 
4. LockSupport park 和 unpark 
5. AQS -AbstractQueuedSynchronizer 
AQS内部 Node / acquire() / release() AQS实现独占锁 
ReentrantLock synchronized比较 两种锁模型 读写锁案例 
Condition BlockingQueue 
6. ThreadPoolExecutor 
参数图 
源码解析 
注意事项
