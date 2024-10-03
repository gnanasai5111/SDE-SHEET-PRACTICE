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

- The first approach uses recursion to calculate the maximum amount of money you can rob.
- Starting from the first house, it considers each house and makes a recursive call to calculate the maximum amount that can be 
    robbed by either skipping or robbing the current house and moving to the next non-adjacent house. 
- The base case checks if the index is out of bounds, in which case it returns 0. The time complexity is exponential since it explores
    all combinations, resulting in O(2^N) time and O(N) space due to the recursion stack.

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

Time complexity - o(2^N)
Space complexity - o(N)

(Recursion - 2)

- In the second recursive approach, the problem is simplified by making two recursive calls: one to skip the current house and 
    another to rob it, adding its value and moving to the next non-adjacent house.
- The result of these two options is compared, and the maximum is returned.

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

Time complexity - o(2^N)
Space complexity - o(N)

Second approach : (Memoization-1) 

- The first memoization approach optimizes the recursive solution by storing the results of subproblems in an array m[]. 
- This way, each subproblem is solved only once, reducing redundant calculations.
- The function checks if the result for a particular index is already computed and stored in m[].
- If not, it computes the result recursively and stores it in the memoization array.
- The time complexity improves to O(N), and the space complexity is also O(N) due to the memo array and recursion stack.

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

- In the second memoization approach, a similar technique is applied, but instead of looping through all houses after the current index,
  it directly compares skipping the current house or robbing it.
- The result is stored in the memo array m[] to avoid recomputation. 
- The time complexity remains O(N), and the space complexity is O(N) due to memoization and recursion.

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

- The tabulation approach eliminates recursion by using dynamic programming with a bottom-up approach.
- It initializes a dp[] array where each element represents the maximum amount that can be robbed up to that house.
- Starting from the first two houses, it iterates through the array, updating the dp[] array with the maximum value either by 
  robbing or skipping each house. 
- The final result is stored in the last element of dp[]. The time complexity is O(N) and the space complexity is O(N).

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

- In the space-optimized approach, instead of using a dp[] array, two variables are used to track the maximum money that can
  be robbed up to the previous house and the house before that. 
- This reduces the space complexity to O(1). 
- The algorithm iterates through the array, updating these variables with the maximum value at each step, similar to the 
  tabulation approach. The time complexity is O(N) with a space complexity of O(1).

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

  
