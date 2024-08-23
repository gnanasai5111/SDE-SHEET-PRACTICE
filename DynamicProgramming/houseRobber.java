You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of 
money you can rob tonight without alerting the police.

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

First approach : (Recursion - 1)

class Solution {
    public int calculateMoney(int nums[], int index) {
        if (index >= nums.length) {
            return 0;
        }   
        int maxSum = 0;
        for (int i = index; i < nums.length; i++) {
            maxSum = Math.max(maxSum, nums[i] + calculateMoney(nums, i + 2));
        }   
        return maxSum;
    }
    public int rob(int[] nums) {
        return calculateMoney(nums,0);
    }
}

Time complexity - o(N)
Space complexity - o(N)

(Recursion - 2)

class Solution {
    public int calculateMoney(int nums[], int index) {
        if (index >= nums.length) {
            return 0;
        }   
        int skipCurrent=calculateMoney(nums,index+1);
        int includeCurrent=nums[index]+calculateMoney(nums,index+2);
        return Math.max(skipCurrent,includeCurrent);
    }
    public int rob(int[] nums) {
        return calculateMoney(nums,0);
    }
}

Time complexity - o(N)
Space complexity - o(N)

Second approach : (Memoization-1) 

class Solution {
    public int calculateMoney(int nums[], int index, int m[]) {
        if (index >= nums.length) {
            return 0;
        }
        
        if (m[index] != 0) {
            return m[index]; 
        }
        
        int maxSum = 0;
        for (int i = index; i < nums.length; i++) {
            maxSum = Math.max(maxSum, nums[i] + calculateMoney(nums, i + 2, m));
        }
        
        return m[index] = maxSum;
    }

    public int rob(int[] nums) {
        int m[] = new int[nums.length];
        return calculateMoney(nums, 0, m);
    }
}


Time complexity - o(N)
Space complexity - o(N)

(Memoization-2) 

class Solution {
    public int calculateMoney(int nums[], int index,int m[]) {
        if (index >= nums.length) {
            return 0;
        }
        if (m[index] != 0) {
            return m[index]; 
        }
        int skipCurrent=calculateMoney(nums,index+1,m);
        int includeCurrent=nums[index]+calculateMoney(nums,index+2,m);
        return m[index]=Math.max(skipCurrent,includeCurrent);
    }
    public int rob(int[] nums) {
         int m[] = new int[nums.length];
        return calculateMoney(nums,0,m);
    }
}

Time complexity - o(N)
Space complexity - o(N)

Third approach : Tabulation

class Solution {
    public int rob(int[] nums) {
        int N = nums.length;
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return nums[0];
        }
        int[] dp = new int[N];
        dp[0] =nums[0];
        dp[1] =Math.max(nums[0],nums[1]);
        for (int i =2; i <N; i++) {
            dp[i] = Math.max(
                dp[i - 1],
                dp[i - 2] + nums[i]
            );
        }
        return dp[N-1];
    }
}


Time complexity - o(N)
Space complexity - o(N)

Fourth approach : Space optimised

class Solution {
    public int rob(int[] nums) {
        int N = nums.length;
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return nums[0];
        }
        int a =nums[0];
        int b =Math.max(nums[0],nums[1]);
        int c =Math.max(nums[0],nums[1]);
        for (int i =2; i <N; i++) {
            c = Math.max(
                b,
                a + nums[i]
            );
            a=b;
            b=c;
        }
        return c;
    }
}



Time complexity - o(N)
Space complexity - o(1)

  
