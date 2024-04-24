
Given an integer array nums, return the number of reverse pairs in the array.

A reverse pair is a pair (i, j) where:

0 <= i < j < nums.length and
nums[i] > 2 * nums[j].

Example 1:

Input: nums = [1,3,2,3,1]
Output: 2
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1

Brute Force Approach :

Loop through the array and for each element tarverse the array again from i+1 to n-1 and check if a[i]>2*a[j]

class Solution {
    public int reversePairs(int[] nums) {
        int count=0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]>(long)2*nums[j]){
                    count++;
                }
            }
        }
        return count;
        
    }
}

Time complexity - o(N^2)
Space complexity - o(1)


Optimised approach (Merge sort technique)

In order to solve this problem we will use the merge sort algorithm like we used in the problem count inversion
with a slight modification of the merge() function.
But in this case, the same logic will not work. In order to understand this, we need to deep dive into the merge() function.

class Solution {
    public void merge(int start,int mid,int end,int nums[]){
        int left=start;
        int right=mid+1;
        ArrayList<Integer> sub=new ArrayList<>();
         while(left<=mid && right<=end){
            if(nums[left]>nums[right]){
                sub.add(nums[right]);
                right++;
            }
            else{
                sub.add(nums[left]);
                left++;
            }
        }
        while(left<=mid){
            sub.add(nums[left]);
            left++;
        }
        while(right<=end){
            sub.add(nums[right]);
            right++;
        }
        for(int i=start;i<=end;i++){
            nums[i]=sub.get(i-start);
        }
    }
    public int countPairs(int start,int mid,int end,int nums[]){
        int right=mid+1;
        int count=0;
        for(int i=start;i<=mid;i++){
            while(right<=end && nums[i]>(long)2*nums[right]){
                right++;
            }
            count+=right-mid-1;
        }
        return count;
    }
    public int divide(int start,int end,int nums[]){
        int count=0;
        if(start==end){
            return count;
        }
        int mid=(start+end)/2;
        count+=divide(start,mid,nums);
        count+=divide(mid+1,end,nums);
        count+=countPairs(start,mid,end,nums);
        merge(start,mid,end,nums);
        return count;
    }
    public int reversePairs(int[] nums) {
       return divide(0,nums.length-1,nums);
        
    }
}

Time complexity - o(NlogN)
Space complexity - o(N)
