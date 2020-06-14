学习笔记

455 分发饼干

目标是满足数量多的孩子，孩子需求越少，越能满足更多的孩子

```
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int j = 0;
        int sLength = s.length;
        if (sLength == 0) return 0;
        int res = 0;
        for (int i = 0; i < g.length; i++) {
            while (j < sLength - 1 && s[j] < g[i]) {
                j++;
            }
            if (j < sLength && g[i] <= s[j]) {
                res++;
                j++;
            }
        }
        return res;
    }
}
```

55跳跃游戏

```
class Solution {
    public boolean canJump(int[] nums) {
        int most = 0;
        for (int i = 0; i < nums.length; i++) {
            if ( i <= most) {
                most = Math.max(most,i + nums[i]);    
                if (most > nums.length) return true;
            }
        }
        return false;
    }
}
```

860柠檬水找零

一开始没想到这道题用纯判断就能做出来，以为有高深的算法，没想到搜了题解好像多数都是判断做的，没看到其他解法

```
    public boolean lemonadeChange(int[] bills) {
        int count5 = 0;
        int count10 = 0;
        int count20 = 0;
        for (int bill : bills) {
            switch (bill){
                case 5:
                    count5++;
                    break;
                case 10:
                    if (count5 > 0) {
                        count5--;
                        count10++;
                    } else {
                        return false;
                    }
                    break;
                case 20:
                    if (count5 > 0 && count10 > 0) {
                        count5--;
                        count10--;
                        count20++;
                    } else if (count5 > 2) {
                        count5 -= 3;
                        count20++;
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
```

200 岛屿数量

这道题一开始做忽略了岛屿会拐弯的情况，只检测了横向，竖向，和斜向，开始看题解也不理解为什么 -1 的情况也要检测 ，看到错误用例才意识到问题

```
class Solution {
    int row;
    int col;
    public int numIslands(char[][] grid) {
        row = grid.length;
        if (row < 1) return 0;
        col = grid[0].length;
        if (col < 1) return 0;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    resetGrid(grid,i,j);
                }
            }
        }
        return count;
    }

    private void resetGrid(char[][] grid,int i, int j) {
        if (i < 0 || j < 0 || i > row - 1 || j > col - 1 || grid[i][j] == '0') return;
        grid[i][j] = '0'; 
        resetGrid(grid,i + 1,j);
        resetGrid(grid,i - 1,j);
        resetGrid(grid,i,j + 1);
        resetGrid(grid,i,j - 1);
    }
}
```



