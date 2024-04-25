Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Brute Force 

 Here, we will check all possible quadruplets using 4 loops(as we did in the 3-sum problem) and among them,
we will consider the ones whose sum is equal to the given target. 
And before considering them as our answer we need to sort the quadruplets in ascending order.

class Solution {
    public List<Integer> sort(int a,int b,int c,int d){
        List<Integer> sortedValues=new ArrayList<>();
        sortedValues.add(a);
        sortedValues.add(b);
        sortedValues.add(c);
        sortedValues.add(d);
        Collections.sort(sortedValues);
        return sortedValues;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    for(int l=k+1;l<nums.length;l++){
                        if(nums[i]+nums[j]+nums[k]+nums[l]==target){                          
                            res.add(sort(nums[i],nums[j],nums[k],nums[l]));
                        }
                    }
                }
            }
        }
        return new ArrayList<>(new HashSet<>(res));
        
    }
}

Time complexity - o(N^4)
Space compelxity - o(2* size of res) - beacause we are using set data structure
