Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


Approach 1:

We generate all possible combinations starting from index 0.
Here we use a marked array to keep track of already visited combinations

class Solution {
    public void generateRecursions(int index,int nums[],ArrayList<Integer> inner,boolean marked[],List<List<Integer>> res){
        if(inner.size()==nums.length){
            res.add(new ArrayList<>(inner));
        }
        for(int i=0;i<nums.length;i++){
            if(!marked[i]){
                marked[i]=true;
                inner.add(nums[i]);
                generateRecursions(i,nums,inner,marked,res); 
                marked[i]=false;
                inner.remove(inner.size()-1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        ArrayList<Integer> inner=new ArrayList<>();
        boolean marked[]=new boolean[nums.length];
        generateRecursions(0,nums,inner,marked,res);
        return res;      
    }
}

Time complexity - o(N! x N)
Space complexity - o(N)

Better approach :

We generate all possible combinations starting from index 0.
Here instead of using marked array we use swapping in place to avoid extra space

class Solution {
    public void swap(int i, int j, int nums[]){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    public void generateRecursions(int index,int nums[],List<List<Integer>> res){
        if(index==nums.length){
            ArrayList<Integer> inner=new ArrayList<>();
            for(int i=0;i<nums.length;i++){
                inner.add(nums[i]);
            }
            res.add(inner);
        }
        for(int i=index;i<nums.length;i++){
                swap(index,i,nums);
                generateRecursions(index+1,nums,res); 
                swap(index,i,nums);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        generateRecursions(0,nums,res);
        return res;      
    }
}

Time complexity - o(N! x N)
Space complexity - o(1)
