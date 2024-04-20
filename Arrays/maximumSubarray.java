Given an integer array nums, find the subarray with the largest sum, and return its sum.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Brute force :

Traverse through all subarrays and find max value

class Solution {
    public int maxSubArray(int[] nums) {
        int max=nums[0];
        for(int i=0;i<nums.length;i++){
            int sum=0;
            for(int j=i;j<nums.length;j++){
                sum=sum+nums[j];
                if(sum>max){
                    max=sum;
                }
            }
            
        }
        return max;
        
    }
}

Time complexity - o(N*2) - Time limit exceeded
Space complexity -o(1)

Optimised approah : Kadanes algorithm

The intuition of the algorithm is not to consider the subarray as a part of the answer if its sum is less than 0.
A subarray with a sum less than 0 will always reduce our answer and so this type of subarray cannot be a part of the 
subarray with maximum sum.

class Solution {
    public int maxSubArray(int[] nums) {
        int max=Integer.MIN_VALUE;
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum=sum+nums[i];
            if(sum>max){
                max=sum;
            }
            if(sum<0){
                sum=0;
            }        
        }
        return max;        
    }
}    

Time complexity - o(N)
Space complexity -o(1)
