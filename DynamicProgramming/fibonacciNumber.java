The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).

Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.


First approach :(Recursion) 

Recurrence relation : f(n)=f(n-1)+f(n-2)

class Solution {
    public int fib(int n) {
        if(n==0 || n==1){
            return n;
        }
        return fib(n-1)+fib(n-2);    
    }
}

Time complexity - o(N)
Space complexity - o(N)

Second approach :(Memoization) 

From the reccurence relation, we are computing many overlapping sub problems , to avoid that we are using memoization (extra space) to 
store already computed values so that we wont visit them again.

class Solution {
    public int calculateFibonacci(int n, int memoization[]) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (memoization[n] != 0) {
            return memoization[n];
        }
        memoization[n] = calculateFibonacci(n - 1, memoization) + calculateFibonacci(n - 2, memoization);
        return memoization[n];
    }

    public int fib(int n) {
        int memoization[] = new int[n + 1];
        return calculateFibonacci(n, memoization);   
    }
}

Time complexity - o(N)
Space complexity - o(N)

Third approach (Tabulation)
                
Using an extra space lets you compute the previous two values.
  
class Solution {
    public int fib(int n) {
        if(n<=1){
            return n;
        }
        int dp[] = new int[n + 1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];   
    }
}

Time complexity - o(N)
Space complexity - o(N)

Fourth approach (space optimised)

Instead of using extra array for computing previous values , you could just use variables and swap them accordingly.

class Solution {
    public int fib(int n) {
        if(n<=1){
            return n;
        }
        int a=0;
        int b=1;
        int c=0;
        for(int i=2;i<=n;i++){
            c=a+b;
            a=b;
            b=c;
        }
        return c;   
    }
}

Time complexity - o(N)
Space complexity - o(1)


