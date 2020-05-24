class Solution {
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
}