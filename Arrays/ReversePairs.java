


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
