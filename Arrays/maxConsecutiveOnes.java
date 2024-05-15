Given a binary array nums, return the maximum number of consecutive 1's in the array.
  
Example 1:

Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

Brute Force :

Traverse the array.For each digit check how many consecutive ones are there by traverseing the array.
check max after each iteration.
Repeat this process for all elements
  

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max=0;
        for(int i=0;i<nums.length;i++){
            int count=nums[i]==1?1:0;
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]!=nums[j] || nums[i]==0){
                    break;
                }
                else{
                    count++;
                }
            }
            max=Math.max(max,count);
        }
        return max;
        
    }
}
Time complexity - o(N^2)
Space complexity - o(1)

Optimised approach :

We maintain a variable count that keeps a track of the number of consecutive 1’s while traversing the array.
The other variable max_count maintains the maximum number of 1’s, in other words, it maintains the answer.

We start traversing from the beginning of the array. Since we can encounter either a 1 or 0 there can be two situations:-

If  the value at the current index is equal to 1 we increase the value of count by one. 
After updating  the count variable if it becomes more than the max_count  update the max_count.
If the value at the current index is equal to zero we make the variable count as 0 since there are no more consecutive ones.

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max=0;
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==1){
                count++;
            }
            else{
                count=0;
            }
            max=Math.max(max,count);
        }
        return max;
        
    }
}
Time complexity - o(N)
Space complexity - o(1)
