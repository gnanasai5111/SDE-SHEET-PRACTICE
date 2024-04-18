You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
representing the number of elements in nums1 and nums2 respectively.
Merge nums1 and nums2 into a single array sorted in non-decreasing order.
The final sorted array should not be returned by the function, but instead be stored inside the array nums1. 
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, 
and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.

Brute Force :

Copy All the elements of both arrays into a single array and sort it

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        Arrays.sort(nums1);       
    }
}

Time complexity -  O((n+m)log⁡(n+m))
Space complexity - o(1)

Better approach :

Take two pointers one at each array .
Compare values at two pointers whichever is less push to new array and increment the index of that pointer.
Repeat this process till any of the pointer reached end first.
Then iterate through rest of elements and push to the res.

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k=0;
        int i=0;
        int j=0;
        int res[]=new int[nums1.length];
        while(i<m && j<n){
            if(nums1[i]<nums2[j]){
                res[k]=nums1[i];
                i++;
            }
            else{
                res[k]=nums2[j];
                j++;
            }
            k++;
        }
        while(i<m){
            res[k]=nums1[i];
            k++;
            i++;
        }
        while(j<n){
            res[k]=nums2[j];
            k++;
            j++;
        }
        for(int x=0;x<res.length;x++){
            nums1[x]=res[x];
        }
        
        
    }
}

Time Complexity: O(n+m) + O(n+m)
Space complexity - o(n+m)


