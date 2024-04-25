Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Brute Force:

Traverse the array for each element traverse the array and check if sum is equal to target and return indexes

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
        
    }
}

Time complexity - o(N^2)
Space complexity - o(1)


Better approach : (Two Pass )

Using a hashmap store all numbers .

Traverse the array again and check whether target-nums[i] and  exist in hashmap or not and same number should not be repeated so 
use this condition map.get(target-nums[i])!=i

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i]) && map.get(target-nums[i])!=i){
                return new int[]{map.get(target-nums[i]),i};
            }
        }
        return new int[]{};
        
    }
}

Time complexity - o(N)
Space complexity - o(N)

Optimised approach (one pass)

same approach as previous one but we just use one pass to traverse array

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> res=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(res.containsKey(target-nums[i])){
                return new int[] {res.get(target-nums[i]),i};
            }
            else{
            res.put(nums[i],i);
            }
        }

        return new int[]{};
        
    }
}
