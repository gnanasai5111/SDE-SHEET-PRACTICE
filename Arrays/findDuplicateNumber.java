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

Optimised approach(Linked list cycle and Flyods Tortoise and hare Algorithm)

Fast - hare 
slow - tortoise

Use a fast and slow pointer. And interate through the array till both fast and slow pointer intersect.

Consider the distance from start to duplicate value is P.And rest of the path from duplicate value is c.
consider the distance from intersection point to duplicate is x.

so for slow pointer = p+c-x
        fast pointer=p+2c-x
    2*slow=fast
    2*(p+c-x)=p+2c-x
    by solving you get p=x
    so that is why we make fast pointer from starting again so that distance between start to duplicate and from intersection to duplicate
    is same

class Solution {
    public int findDuplicate(int[] nums) {
        int slow=nums[0];
        int fast=nums[0];
        do{
            slow=nums[slow];
            fast=nums[nums[fast]];
        }while(slow!=fast);
        fast=nums[0];
        while(slow!=fast){
            slow=nums[slow];
            fast=nums[fast];
        }
        return slow;      
    }
}

Time complexity - o(N)
Space complexity - o(1)
    
