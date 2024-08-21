You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

First approach (Recursion)

Calculate all possible ways 

class Solution {
    public int climbStairs(int n) {
        if(n<0){
            return 0;
        }
        if(n==0){
            return 1;
        }
        return climbStairs(n-1)+climbStairs(n-2);
    }
}

Time complexity - o(N)
Space complexity - o(N)

Second approach (Memoization)

Optimise the recursive solution by caching all the previously visited values.

class Solution {
    public int calculatePossibleClimbs(int n,int memoisation[]){
        if(n<0){
            return 0;
        }
        if(n==0){
            return 1;
        }
        if(memoisation[n]!=0){
            return memoisation[n];
        }
        memoisation[n]=calculatePossibleClimbs(n-1,memoisation)+calculatePossibleClimbs(n-2,memoisation);
        return memoisation[n];
    }
    public int climbStairs(int n) {
        int memoisation[]=new int[n+1];
        return calculatePossibleClimbs(n,memoisation);
    }
}

Time complexity - o(N)
Space complexity - o(N)

Third approach (Tabulation)

start from base case and gradually solve sub problems and club to get the final solution

class Solution {
    public int climbStairs(int n) {
        if(n<=1){
            return n;
        }
        int dp[]=new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}

Time complexity - o(N)
Space complexity - o(N)

Fourth approach (optimised space)

instead of using extra space, we could use variables swap to get result

class Solution {
    public int climbStairs(int n) {
        if(n<=2){
            return n;
        }
        int a=1;
        int b=2;
        int c=0;
        for(int i=3;i<=n;i++){
            c=a+b;
            a=b;
            b=c;
        }
        return c;
    }
}

Time complexity - o(N)
Space complexity - o(1)
