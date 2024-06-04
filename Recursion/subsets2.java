Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]


First approach :

At every index, we make a decision whether to pick or not pick the element at that index.
This will help us in generating all possible combinations but does not take care of the duplicates.
Hence we will use a set to store all the combinations that will discard the duplicates.


class Solution {
    public void generateSubset(int index,HashSet<List<Integer>> res,int[] nums,List<Integer> inner){
        if(index==nums.length){
            ArrayList<Integer> updated =  new ArrayList<>(inner);
            Collections.sort(updated);
            res.add(updated);
            return;
        }
        inner.add(nums[index]);
        generateSubset(index+1,res,nums,inner);
        inner.remove(inner.size()-1);
        generateSubset(index+1,res,nums,inner);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashSet<List<Integer>> res=new HashSet<>();
        List<Integer> inner=new ArrayList<>();
        generateSubset(0,res,nums,inner);
        return new ArrayList<>(res);
    }
}

Time Complexity: O(2^n)+o(nlogn)
Space Complexity: O(2^n * k)

Better approach :

Sort the input array.
Make a recursive function that takes the input array ,the current subset,the current index and a list of list/ vector of vectors
to contain the answer.
Try to make a subset of size n during the nth recursion call and consider elements from every index while generating the combinations. 
Only pick up elements that are appearing for the first time during a recursion call to avoid duplicates.
Once an element is picked up, move to the next index.The recursion will terminate when the end of array is reached.
While returning backtrack by removing the last element that was inserted.

class Solution {
    public void generateSubset(int index,List<List<Integer>> res,int[] nums,List<Integer> inner){
        res.add(new ArrayList<>(inner));
        for(int i=index;i<nums.length;i++){
            if(i!=index &&  nums[i]==nums[i-1]){
                continue;
            }
            inner.add(nums[i]);
            generateSubset(i+1,res,nums,inner);
            inner.remove(inner.size()-1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> inner=new ArrayList<>();
        generateSubset(0,res,nums,inner);
        return new ArrayList<>(res);
    }
}

Time Complexity:O(2^n) 
Space Complexity: O(2^n * k) - for storing answer
