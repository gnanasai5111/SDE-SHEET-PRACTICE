You are given an array/list ‘ARR’ of ‘N’ positive integers and an integer ‘K’.
Your task is to check if there exists a subset in ‘ARR’ with a sum equal to ‘K’.

Note: Return true if there exists a subset with sum equal to ‘K’. Otherwise, return false.

For Example :
If ‘ARR’ is {1,2,3,4} and ‘K’ = 4, then there exists 2 subsets with sum = 4. These are {1,3} and {4}. Hence, return true.


First approach : Recursion

public class Solution {
    public static boolean isSubSetSumEqualToK(int index,int arr[],int k,int sum){
        if(sum==k){
            return true;
        }
        if(index==arr.length){
            return false;
        }     
        for(int i=index;i<arr.length;i++){
            if(isSubSetSumEqualToK(i+1,arr,k,sum+arr[i])){
                return true;
            }
        }
        return false;
    }
    public static boolean subsetSumToK(int n, int k, int arr[]){
        return isSubSetSumEqualToK(0,arr,k,0);
    }
}

Time Complexity: O(2^n)
Space Complexity: O(n)

Recursion 2:

public class Solution {
    public static boolean isSubSetSumEqualToK(int index, int arr[], int k, int sum) {
        if(sum==k){
            return true;
        }
        if (index == arr.length) {
            return false;
        }
        
        // Include the current element
        if (isSubSetSumEqualToK(index + 1, arr, k, sum + arr[index])) {
            return true;
        }
        
        // Exclude the current element
        if (isSubSetSumEqualToK(index + 1, arr, k, sum)) {
            return true;
        }
        
        return false;
    }
    
    public static boolean subsetSumToK(int n, int k, int arr[]) {
        return isSubSetSumEqualToK(0, arr, k, 0);
    }
}

Time Complexity: O(2^n)
Space Complexity: O(n)

Approach 3 : Memoization

public class Solution {
    public static boolean isSubSetSumEqualToK(int index,int arr[],int k,int sum,int memo[][]){
        if(sum==k){
            return true;
        }
        if (index == arr.length || sum > k) {
            return false;
        }
        if(memo[index][sum]!=-1){
            return memo[index][sum]==0?false:true;
        }
        
        // Include the current element
        boolean take=isSubSetSumEqualToK(index + 1, arr, k, sum + arr[index],memo);
        //  Exclude the current element
        boolean notTake=isSubSetSumEqualToK(index + 1, arr, k, sum,memo);
        
        memo[index][sum]=take || notTake?1:0;
        
        return take || notTake;
    }
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int memo[][]=new int[arr.length][k+1];
        for (int row[] : memo)
            Arrays.fill(row, -1);
        return isSubSetSumEqualToK(0,arr,k,0,memo);
    }                                                                            
}

Time Complexity: O(N*K) 
Space Complexity: O(N*K) + O(N)
