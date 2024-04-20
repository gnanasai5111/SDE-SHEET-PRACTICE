Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:

Input: nums = [3,2,3]
Output: 3

Brute force :

We will run a loop that will select the elements of the array one by one.
Now, for each element, we will run another loop and count its occurrence in the given array.
If any element occurs more than the floor of (N/2), we will simply return it.

class Solution {
    public int majorityElement(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int count=0;
            for(int j=0;j<nums.length;j++){
                if(nums[i]==nums[j]){
                    count++;
                }
            }
            if(count>nums.length/2){
                return nums[i];
            }
        }
        return -1;
        
    }
}

Time complexity - o(N*2)
Space complexity - o(N)

Better approach :

Count the occurences of elements and check if count exceeds the total length/2 and return the element 
  
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for(Integer i:map.keySet()){
            if(map.get(i)>nums.length/2){
                return i;
            }
        }
        return -1;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)

Better approach :

Sort the array. And check the count of consecutive similar elements if it exceeds nums.length/2 return the number

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int count=0;
        int res=-1;
        for(int i=0;i<nums.length;i++){
            if(i==0){
                count=1;
                res=nums[i];
            }
            else if(nums[i]==nums[i-1]){
                count++;
            }
            else{
                count=1;
                res=nums[i];
            }
            if(count>nums.length/2){
                return res;

            }

        }
        return -1;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)

Optmised approach : Boyer-Moore Voting Algorithm

If the array contains a majority element, its occurrence must be greater than the floor(N/2). 
Now, we can say that the count of minority elements and majority elements is equal up to a certain point in the array.
So when we traverse through the array we try to keep track of the count of elements and the element itself for which we
are tracking the count. 

After traversing the whole array, we will check the element stored in the variable.
If the question states that the array must contain a majority element, the stored element will be that one but 
if the question does not state so, then we need to check if the stored element is the
majority element or not. If not, then the array does not contain any majority element.

This alogorithm works because everyone get cancelled out when count equals to 0 so last element we get is the one with max count

class Solution {
    public int majorityElement(int[] nums) {
        int ele=-1;
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(count==0){
                count=1;
                ele=nums[i];
            }
            else if(ele==nums[i]){
                count++;
            }
            else{
                count--;
            }
           
        }
        count=0;
        for(int i=0;i<nums.length;i++){
            if(ele==nums[i]){
                count++;
            }
        }
        if(count>nums.length/2){
            return ele;
        }
        return -1;
        
    }
}

Time complexity - o(N)
Space complexity - o(1)
