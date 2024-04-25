Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] 
such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.


Brute force :

 Here, we will check all possible triplets using 3 loops and among them,
we will consider the ones whose sum is equal to the given target i.e. 0.
And before considering them as our answer we need to sort the triplets in ascending order so that we can consider only the unique ones.

class Solution {
     public List<Integer> sort(int a,int b,int c){
        List<Integer> sortedValues=new ArrayList<>();
        sortedValues.add(a);
        sortedValues.add(b);
        sortedValues.add(c);
        Collections.sort(sortedValues);
        return sortedValues;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                   if(nums[i]+nums[j]+nums[k]==0){
                    res.add(sort(nums[i],nums[j],nums[k]));
                   }
                }
            }
        }
        return new ArrayList<>(new HashSet<>(res));
        
    }
}

Time complexity - o(N^3)
Space complexity -  O(2 * no. of the unique triplets) 


Better approach :

In the previous approach, we utilized 3 loops, but now our goal is to reduce it to 2 loops.
To achieve this, we need to find a way to calculate arr[k] since we intend to eliminate the third loop (k loop).
To calculate arr[k], we can derive a formula as follows: 

arr[k] = target - (arr[i]+arr[j]) = 0-(arr[i]+arr[j]) = -(arr[i]+arr[j]) 
You can use Hashset to check whether the set contains -(arr[i]+arr[j])

class Solution {
     public List<Integer> sort(int a,int b,int c){
        List<Integer> sortedValues=new ArrayList<>();
        sortedValues.add(a);
        sortedValues.add(b);
        sortedValues.add(c);
        Collections.sort(sortedValues);
        return sortedValues;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            HashSet<Integer> set=new HashSet<>();
            for(int j=i+1;j<nums.length;j++){
                int sum=nums[i]+nums[j];
                if(set.contains(-1*sum) ){
                    res.add(sort(nums[i],nums[j],-sum));
                }
                else{
                    set.add(nums[j]);
                }
            }
        }
        return new ArrayList<>(new HashSet<>(res));
        
    }
}

Time complexity - o(N^2)
Space complexity -  O(2 * no. of the unique triplets) 
