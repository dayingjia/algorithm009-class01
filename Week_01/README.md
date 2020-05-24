学习笔记

## 算法练习

### remove duplicates from sorted array

```
    public int removeDuplicates(int[] nums) {
        if(nums.length <= 1) return nums.length;
        int i = 0;
        for(int j = 1; j < nums.length; j++){
            if(nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
```



### merge two sorted lists

```
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode prevHead = new ListNode(-1);
        ListNode prev = prevHead;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prevHead.next;
    }
}
```

### merge sorted array

```
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] nums3 = new int[m+n];
        while(i < m && j < n){
            nums3[k++] = nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++];
        }
        if(i < m){
            System.arraycopy(nums1,i,nums3,i+j,m+n-i-j);
        }
        if(j < n){
            System.arraycopy(nums2,j,nums3,i+j,m+n-i-j);
        }
        System.arraycopy(nums3,0,nums1,0,m+n);
    }
```



## 用add first或add last这套新的API改写Deque的代码







## 分析Queue和Priority Queue的源码



