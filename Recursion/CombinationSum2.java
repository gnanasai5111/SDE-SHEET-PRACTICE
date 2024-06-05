Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]

Approach :
Use pick and not pick approach and also use hashset to avoid duplicates

class Solution {
    public void getAllCombSum(int index,int target,int[] candidates,HashSet<List<Integer>> res,List<Integer> inner){
        if(index==candidates.length || target==0){
            if(target==0){
                res.add(new ArrayList<>(inner));
            }
            return;
        }
        inner.add(candidates[index]);
        getAllCombSum(index+1,target-candidates[index],candidates,res,inner);
        inner.remove(inner.size()-1);
        getAllCombSum(index+1,target,candidates,res,inner);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        HashSet<List<Integer>> res=new HashSet<>();
        Arrays.sort(candidates);
        getAllCombSum(0,target,candidates,res,new ArrayList<>());
        return new ArrayList<>(res);
    }
}

Time complexity - o(2^n)+o(nlogn)
Space complexity- o(k*x)

Optimised approach :

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(candidates);
        combo(candidates,0,target,res,new ArrayList<Integer>());
        return res;
        
    }
    public void combo(int[] candidates,int index, int target,List<List<Integer>> res,ArrayList<Integer> inner){
        if(target==0){
            res.add(new ArrayList<>(inner));
            return ;
        }
        for(int i=index;i<candidates.length;i++){
            if(i>index && candidates[i]==candidates[i-1]){
                continue;
            }
            if(candidates[i]>target){
                break;
            }
            inner.add(candidates[i]);
            combo(candidates,i+1,target-candidates[i],res,inner);
            inner.remove(inner.size()-1);
        }


    }
}

Time complexity - o(2^n)+o(nlogn)
Space complexity- o(k*x)
