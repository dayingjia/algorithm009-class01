学习笔记



动态规划核心：

重叠子问题，最优子结构，状态转移方程



重点是列出状态转移方程

647.回文子串

定义dp方程  

```
dp[i][j] = dp[i + 1][j - 1]
```

  

```
class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int res = n;//因为单个字符也算回文，所以最小回文长度为字符串长度
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;//初始化对角线单个字符也属于回文
        }
        //当i = n - 1,j = i + 1 = n，没有开始遍历
        //当i = n - 2,j = i + 1 = n - 1 ，比较最后两个字符是否相等，若相等，属于回文
        //当i = n - 3,j = i + 1 = n - 2 ，比较倒数第二个字符和倒数第三个字符是否相等，若相等，属于回文
        //j++ ，比较倒数第三个字符和最后一个字符是否相等，如果相等，因为中间夹的单个字符属于回文，所以这三个字符属于回文
        //以此类推，当i = 0，j从1遍历到最后一个字符，如果i和j中间夹的字符串是回文，并且i和j的字符相等，则表示整个字符串都是回文
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }
}
```



76.最小覆盖子串

这里用了滑动窗口

```
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < 1 || t.length() < 1 || s.length() < t.length()) return "";
        HashMap<Character,Integer> tmap = new HashMap<>();
        for(char tc : t.toCharArray()) {
            tmap.put(tc,tmap.getOrDefault(tc,0) + 1);
        }
        HashMap<Character,Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (tmap.containsKey(c)) {
                window.put(c,window.getOrDefault(c,0) + 1);
                if (tmap.get(c) == window.get(c)) { //应该用equals
                    valid++;
                }
            }
            //当前left 和 right 间找到了一个符合要求的字符串，需要收缩窗口
            while (valid == window.keySet().size()) {//这里应该为tmap
            	//如果新找到的字符串长度比旧的小，则开始更新窗口长度
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (tmap.containsKey(d)) {
                	//当删除了一个有效字符，表示这里的start是字符起始点，这里valid--，退出循环，外部循环right将继续往后查找，直到最后一个字符
                    if (tmap.get(d) == window.get(d)) {//应该用equals
                        valid--;
                    }
                    window.put(d,window.getOrDefault(d,0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start,len);
    }
}
```

120 最小路径和

从下往上递推，当前最小路径等于当前点的大小加上左路径或者右路径较小的一个

```
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                A[j] = Math.min(A[j],A[j + 1]) + triangle.get(i).get(j);
            }
        }
        return A[0];
    }
}
```

