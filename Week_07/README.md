学习笔记



Trie树

核心是N叉树，效率比哈希表高，经常被用于文本词频统计

从根节点到叶子节点表示一个字符串

空间换时间



并查集

找到节点的根节点，如果根节点一致，表示它们属于一个组



剪枝

剪枝是在暴力的基础上提前排除不可能的路径

用剪枝的前提是想明白哪些路径是不可能的，八皇后题目中指定了对角线和行列都不能有重复元素，这就是剪枝的条件



双向BFS

双向BFS是单向BFS的优化

从节点数小的一端开始，减少判断次数



启发式搜索

启发式搜索使用优先队列，从概率高的节点开始搜索