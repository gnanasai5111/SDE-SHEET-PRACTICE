Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Brute force :

A straightforward but basic solution is to examine consecutive sequences for each element in the given array.
For every element x, we will try to find the consecutive elements, x+1, x+2, x+3, and so on using the linear search algorithm. 
Thus, we will check the length of the longest consecutive subsequence we can build for every element x


class Solution {
    public boolean linearSearch(int val,int nums[],int index){
        for(int i=index;i<nums.length;i++){
            if(nums[i]==val){
                return true;
            }
        }
        return false;
    }
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int count=0,max=0;
        for(int i=0;i<nums.length;i++){
            count=1;
            int val=nums[i];
            while(linearSearch(val+1,nums,i)){
                count++;
                val=val+1;
            }
            max=Math.max(count,max);
        }
        return max;
        
    }
}

Time complexity - o(N^3)
Space complexity - o(1)


Better approach (Sorting)

Sort the array and check if a[i]=a[i-1]+1 and find max consecutive length

class Solution {
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int count=0,max=0;
        for(int i=0;i<nums.length;i++){
            if(i==0 || nums[i]==nums[i-1]+1){
                count++;
                max=Math.max(max,count);
            }
            else if(nums[i]==nums[i-1]){
                continue;
            }
            else{
                count=1;
            }
        }
        return max;
        
    }
}

Time complexity - o(NlogN)
Space complexity - o(1)


Optimised approach 

We will adopt a similar approach to the brute-force method but with optimizations in the search process. 
Instead of searching sequences for every array element as in the brute-force approach,
we will focus solely on finding sequences only for those numbers that can be the starting numbers of the sequences.
This targeted approach narrows down our search and improves efficiency.

We will do this with the help of the Set data structure.

How to identify if a number can be the starting number for a sequence:

First, we will put all the array elements into the set data structure.
If a number, num, is a starting number, ideally, num-1 should not exist. 
So, for every element, x, in the set, we will check if x-1 exists inside the set. :
If x-1 exists: This means x cannot be a starting number and we will move on to the next element in the set.
If x-1 does not exist: This means x is a starting number of a sequence.
So, for number, x, we will start finding the consecutive elements.
How to search for consecutive elements for a number, x:

Instead of using linear search, we will use the set data structure itself to search for the elements x+1, x+2, x+3, and so on.
Thus, using this method we can narrow down the search and optimize the approach.

class Solution {
    public int longestConsecutive(int[] nums) {
        int count=0,max=0;
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        for(int i=0;i<nums.length;i++){
            count=1;
            if(set.contains(nums[i]-1)){
                continue;
            }
            else{
                int x=nums[i];
                while(set.contains(x+1)){
                    count++;
                    x=x+1;
                }
                max=Math.max(max,count);
            }
        }
        return max;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)
