A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]

Brute force approach(Recursion and Backtracking)

- Generate all permuations
- Sort the permutations 
- find the next permuation in the sorted permuatation by comapring withNums if matched values are  in last index then return first permutatin


class Solution {
    public boolean checkIfEqual(List<Integer> inner,int nums[]){
        for(int i=0;i<inner.size();i++){
            if(inner.get(i)!=nums[i]){
                return false;
            }
       
        }
        return true;
    }
    public void copyValues(List<Integer> inner,int nums[]){
        for(int i=0;i<inner.size();i++){
            nums[i]=inner.get(i);    
        }
    }
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
    public void nextPermutation(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        ArrayList<Integer> inner=new ArrayList<>();
        boolean marked[]=new boolean[nums.length];

        generateRecursions(0,nums,inner,marked,res);
        Collections.sort(res, (a, b) -> {
        for (int i = 0; i < nums.length; i++) {
            if (a.get(i) != b.get(i)) {
            return Integer.compare(a.get(i), b.get(i));
            }
        }
        return 0;
        });
        for(int i=res.size()-1;i>=0;i--){
            if(checkIfEqual(res.get(i),nums)){
                if(i==res.size()-1){
                    copyValues(res.get(0),nums);
                }
                else{
                    copyValues(res.get(i+1),nums);
                }
                break;
            }
        }    
    }
}

Time complexity - o(N!*N)
Space complexity - o(N)

You can further optimise the space complexity to o(1) by generating permutations in different way

Optimised approach :

1. Find a breakpoint .It means find the index from back of the array when nums[i]>nums[i-1] and assign start=i-1
2.If such a break-point does not exist i.e.then reverse the array
3. If a break-point exists:
Find the smallest number i.e. > start and in the right half of index start and swap arr[i] and arr[start].
Reverse the entire right half(i.e. from index start+1 to n-1). And finally, return the array.

class Solution {
    public void reverse(int start,int end,int nums[]){
        while(start<end){
            int temp=nums[start];
            nums[start]=nums[end];
            nums[end]=temp;
            start++;
            end--;
        }
    }
    public void nextPermutation(int[] nums) {
        int start=-1;
        for(int i=nums.length-1;i>0;i--){
            if(nums[i]>nums[i-1]){
                start=i-1;
                break;
            }
        }
        if(start!=-1){
            for(int i=nums.length-1;i>=start;i--){
                if(nums[i]>nums[start]){
                    int temp=nums[start];
                    nums[start]=nums[i];
                    nums[i]=temp;
                    break;
                }
            }
            reverse(start+1,nums.length-1,nums);
        }
        else{
            reverse(0,nums.length-1,nums);
        }
    }
}

Time complexity - o(N)
Space complexity - o(1)
