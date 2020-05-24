## 学习总结



### 数组、链表、跳表

数组查询快，增删慢（需要移动元素）

链表查询慢，增删快（前提是先查询到元素）

跳表是链表的加速版，给链表添加了索引，提高查询速度

跳表时间复杂度：索引几层，就往下跳几次，当链表长度为n，每三个节点一个索引，最高层节点个数=n/(2^h)  h = logn

跳表空间复杂度：索引额外需要n个空间，n/2 + n?4 + n/8...，所以是O(n)



### 栈、队列、双端队列、优先队列

栈：先进后出，只能从顶端插入移除元素

队列：先进先出，只能从队头移除元素，队尾插入元素

双端队列：两端都能插入删除元素

优先队列：插入元素时排好序，取出元素时按照优先顺序取出



## 分析Queue和Priority Queue的源码

Queue

提供 add 和 offer 方法添加元素，如果容量已满，add会抛出一个 IllegalStateException 异常，offer返回false

提供 remove 和 poll 方法移除并返回元素，如果队列为空，remove会抛出一个 NoSuchElementException 异常，poll返回null;

提供 element 和 peek方法返回队列头部元素，如果队列为空，element会抛出一个 NoSuchElementException 异常，peek返回null;

### Priority Queue

优先队列，从队中取出元素的时候先取优先级最高的

底层用数组保存元素

```
transient Object[] queue; // non-private to simplify nested class access
```

默认容量11

```
private static final int DEFAULT_INITIAL_CAPACITY = 11;
```

最大元素数量

```
private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
```

扩容规则

```
int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                 (oldCapacity + 2) :
                                 (oldCapacity >> 1));
```

插入元素，k = size + 1，先把元素放在size+1的位置，然后和它的父元素比较大小，如果比父元素小，交换位置

```
private void siftUp(int k, E x) {
    if (comparator != null)
        siftUpUsingComparator(k, x);
    else
        siftUpComparable(k, x);
}
@SuppressWarnings("unchecked")
private void siftUpComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (key.compareTo((E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = key;
}
@SuppressWarnings("unchecked")
private void siftUpUsingComparator(int k, E x) {
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (comparator.compare(x, (E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = x;
}
```

移除元素，取出0位置的元素，k = 0，x是最后一个元素，取最后一个元素和队首左右子节点进行比较

```
private void siftDown(int k, E x) {
    if (comparator != null)
        siftDownUsingComparator(k, x);
    else
        siftDownComparable(k, x);
}
@SuppressWarnings("unchecked")
private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        if (key.compareTo((E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = key;
}
@SuppressWarnings("unchecked")
private void siftDownUsingComparator(int k, E x) {
    int half = size >>> 1;
    while (k < half) {
        int child = (k << 1) + 1;
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            comparator.compare((E) c, (E) queue[right]) > 0)
            c = queue[child = right];
        if (comparator.compare(x, (E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = x;
}
```







