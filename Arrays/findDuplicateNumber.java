Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2

Brute Force :

Take an HashMap and store the number frequency.If any frequency already exists return the number.

class Solution {
    public int findDuplicate(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                return nums[i];
            }
            else{
                map.put(nums[i],1);
            }
        }
        return -1;      
    }
}

Time complexity - o(N)
Space complexity - o(N)

Optmised space complexity -

Sort the array. Traverse the array and check if nums[i] and nums[i+1] are same so you can find duplicate 

class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]==nums[i+1]){
                return nums[i];
            }
        }
        return -1;      
    }
}

Time complexity - o(NLogN)
Space complexity - o(N)
