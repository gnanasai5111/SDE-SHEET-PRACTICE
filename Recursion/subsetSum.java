Given a list arr of n integers, return sums of all subsets in it. Output sums can be printed in any order.

Example 1:

Input:
n = 2
arr[] = {2, 3}
Output:
0 2 3 5
Explanation:
When no elements is taken then Sum = 0.
When only 2 is taken then Sum = 2.
When only 3 is taken then Sum = 3.
When element 2 and 3 are taken then 
Sum = 2+3 = 5.

First approach :

start with index 0 and do recursive call to generate all subsets

class Solution {
    public void generateSubsetSums(int index,ArrayList<Integer> res,ArrayList<Integer> arr,int sum){
        res.add(sum);
        for(int i=index;i<arr.size();i++){
            generateSubsetSums(i+1,res,arr,sum+arr.get(i));
        }
    }
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        // code here
        ArrayList<Integer> res=new ArrayList<>();
        generateSubsetSums(0,res,arr,0);
        return res;
    }
}

Time complexity -o(2^n)
Space complexity - o(1)

Second approach :

The main idea is that on every index you have two options either to select the element to add it to your subset(pick) or not select the element at that index 
and move to the next index

class Solution {
    public void generateSubsetSums(int index,ArrayList<Integer> res,ArrayList<Integer> arr,int sum){
        if(index==arr.size()){
            res.add(sum);
            return;
        }
        generateSubsetSums(index+1,res,arr,sum+arr.get(index));
        generateSubsetSums(index+1,res,arr,sum);
    }
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        // code here
        ArrayList<Integer> res=new ArrayList<>();
        generateSubsetSums(0,res,arr,0);
        return res;
    }
}

Time complexity -o(2^n)
Space complexity - o(1)
