Given an array of distinct integers candidates and a target integer target, 
return a list of all unique combinations of candidates where the chosen numbers sum to target.
You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the 
frequency of at least one of the chosen numbers is different.
The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations
for the given input.

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.


Approach :

Whenever the problem is related to picking up elements from an array to form a combination, 
start thinking about the “pick and non-pick” approach.

class Solution {
    public void getAllCombSum(int index,int target,int[] candidates,List<List<Integer>> res,List<Integer> inner){
        if(index==candidates.length){
            if(target==0){
                res.add(new ArrayList<>(inner));
            }
            return;
        }
        if (candidates[index] <= target) {
        inner.add(candidates[index]);
        getAllCombSum(index,target-candidates[index],candidates,res,inner);
        inner.remove(inner.size()-1);
        }
        getAllCombSum(index+1,target,candidates,res,inner);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        getAllCombSum(0,target,candidates,res,new ArrayList<>());
        return res;
    }
}

Time Complexity: O(2^t * k) where t is the target, k is the average length
Space Complexity: O(k*x), k is the average length and x is the no. of combinations
